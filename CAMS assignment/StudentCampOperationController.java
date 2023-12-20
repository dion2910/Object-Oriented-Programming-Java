package CamApp;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentCampOperationController implements IRegister, IViewRegistered, IViewVisibleCamps {
	/**
	Contains the camp operations for Student.
	 
	@return StudentCampOperationController Object
	*/

	CsvWriteMethods writeCsv = new CsvWriteMethods();

	/**
	* @param campList aray list of camp(s) created
	* @param student student that is viewing the camp
	*
	* @return all visible camp(s) 
	*/
	public ArrayList<Camp> viewVisibleCamps(CampList campList, Student student) {
		ArrayList<Camp> list = new ArrayList<Camp>();
		int count =1;
		 for(int i=0;i<campList.getCampList().size();i++){
			Camp camp=campList.getCampList().get(i); //camp stores camp object
			if(camp.getIsVisible()==true){ //check visibility
				if(camp.getFaculty().equals(student.getFaculty())||camp.getOpenToNTU()){ //check either open to NTU or faculty
					list.add(camp);
					System.out.println((count)+ ".Camp Name: " + camp.getCampID()+" | Faculty/OpenToNTU: "  + camp.getFaculty() +"/" + String.valueOf(camp.getOpenToNTU()) + " | Total empty slots: "+ camp.getEmptySlots() + " | Total empty committee slots: " + camp.getCommitteeSlots() + " | Total empty attendee slots: "+ (camp.getEmptySlots()-camp.getCommitteeSlots()) + "\n" + " |Start Date: " + camp.getCampDatesStart() + " |End Date: " + camp.getCampDatesEnd() + " |Registration Deadline: " + camp.getRegistrationDeadline() + " |Location: " + camp.getLocation()); //will display camp name and empty slots
					count++;
				}
			}
		}
		return list;
	}

	/** 
	* @param student student that is viewing the camp 
	*
	* @return Camp(s) that the student registered for
	*/
	public ArrayList<Camp> viewRegistered(Student student){
		Camp camp=null;
		ArrayList<Camp> registeredList = new ArrayList<Camp>();
		int count = 1;
		
		// print out the camps registered as Committee
		System.out.println("Registered as Committee:");
		if(student.getIsCommitteeCamp()!=null){
			System.out.println(count + ". " + student.getIsCommitteeCamp().getCampID());
			registeredList.add(student.getIsCommitteeCamp());
			count++;
		}
		else{
			System.out.println("No camps registered as Committee.");
		}
		System.out.println("-----------");
		// print out the camps registered as Attendee
		System.out.println("Registered as Attendee:");
		if(student.getIsAttendeeCamps().size()!=0){
			for(int i=0;i<student.getIsAttendeeCamps().size();i++){ 
				camp=student.getIsAttendeeCamps().get(i);
				System.out.println(count + ". " + camp.getCampID());
				registeredList.add(camp);
				count++;
			}
		}
		else{
			System.out.println("No camps registered as Attendee.");
		}
		return registeredList;
	}

	/**
	* Check if the the dates of two camps clash
	* @param startDate1 frsrt camp start date
	* @param endDate1 first camp end date
	* @param startDate2 second camp start date
	* @param endDate2 second camp end date
	* @return true if the dates clashes and false if does not
	*/
	private boolean checkForClash(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2){
		// Check for clashes
		return !(endDate1.isBefore(startDate2) || startDate1.isAfter(endDate2));
	}

	/**
	* Operation for student to regsiter to a camp 
	* @param camp Camp that the student want to register
	* @param asCommittee If the student want to register as commmittee
	* @param student Student
	*/
	public void register(Camp camp, boolean asCommittee,Student student){
		
		
		LocalDate deadlineDate = camp.getRegistrationDeadline();
		LocalDate currentDate = LocalDate.now();

		int availableCommitteeSlots;
		int availableAttendeeSlots;
		boolean studentHasCommittee;
		boolean studentDoesNotHaveAttendeeCamp;
		boolean isBeforeDeadline;

		if(currentDate.isBefore(deadlineDate) || currentDate.isEqual(deadlineDate)) {//check for deadline
			isBeforeDeadline = true;
		}else{
			isBeforeDeadline = false;
		}

		if (student.getIsAttendeeCamps() == null) {
			studentDoesNotHaveAttendeeCamp = true;
		} else {
			studentDoesNotHaveAttendeeCamp = false;
		}

		if (student.getIsCommitteeCamp() == null) {
			studentHasCommittee = false;
		} else {
			studentHasCommittee = true;
		}

		if(camp.getCommitteeList() == null) {
			availableCommitteeSlots = camp.getCommitteeSlots();
		} else {
			availableCommitteeSlots = camp.getCommitteeSlots() - camp.getCommitteeList().size();
		}

		if (camp.getAttendeeList() == null) {
			availableAttendeeSlots = camp.getEmptySlots();
		} else {
			availableAttendeeSlots = camp.getEmptySlots() - camp.getAttendeeList().size();
		}
		
		if (!isBeforeDeadline) {
			System.out.println("Registration deadline over!");
			return;
		}
		
		if (!studentDoesNotHaveAttendeeCamp) {
			for(int i = 0; i<student.getIsAttendeeCamps().size();i++){//check if registered as attendee already
				if(student.getIsAttendeeCamps().get(i).getCampID().equals(camp.getCampID())){
					System.out.println("You have already registered for this camp as an Attendee!");
					return;
				}
			}
		}


		if(studentHasCommittee && student.getIsCommitteeCamp().getCampID().equals(camp.getCampID())){//check if you registered as a committee before
			System.out.println("You have already registered for this camp as a Committee!");
			return;
		}

		


		/////COMMITTEE CHECKS

		//if registered as committee but wants to register as committee
		if(studentHasCommittee && asCommittee){
			System.out.println("You are a commmittee of another camp: " + student.getIsCommitteeCamp().getCampID());
			return;
		}

		//if not registered as any attendee and committee but wants to register as committee
		if(studentDoesNotHaveAttendeeCamp && !studentHasCommittee && asCommittee){

			if(availableCommitteeSlots>0){ //if committee slots > 0
				student.setIsCommitteeCamp(camp);
				CampCommittee committee = new CampCommittee(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), student.getUserType());
				camp.addToCommitteeList(committee);
				writeCsv.addToCommitteeList(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), camp.getCampID(), 0);
				System.out.println("Successfully registered as committee!");
			}
			else { //if committe slots <= 0
				System.out.println("Committee slots are filled");
			}
			return;
		}

		//if not registered as committee but registerd as attendee but wants to register as committee
		if(!studentDoesNotHaveAttendeeCamp && !studentHasCommittee && asCommittee){

			for( int i = 0; i < student.getIsAttendeeCamps().size();i++) {//other attendee camp date clash
				if(checkForClash(student.getIsAttendeeCamps().get(i).getCampDatesStart(), student.getIsAttendeeCamps().get(i).getCampDatesEnd(), camp.getCampDatesStart(), camp.getCampDatesEnd())){
					System.out.println("Dates clash with other Attendee Camp!");
					return;
				}
			}
		
			if(availableCommitteeSlots>0){ //if committee slots > 0
				student.setIsCommitteeCamp(camp);
				CampCommittee committee = new CampCommittee(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), student.getUserType());
				camp.addToCommitteeList(committee);
				writeCsv.addToCommitteeList(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), camp.getCampID(), 0);
				System.out.println("Successfully registered as committee!");
				return;
			}
			else { //if committe slots <= 0
				System.out.println("Committee slots are filled");
				return;
			}
		}
	


		

		////ATTENDEE CHECKS

		//if not registered as any attendee and committee but wants to register as attendee
		if(studentDoesNotHaveAttendeeCamp && !studentHasCommittee && !asCommittee){

			if(availableAttendeeSlots>0){ //if attendee slots > 0
				student.addToIsAttendeeCamps(camp);
				CampAttendee attendee = new CampAttendee(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), student.getUserType());
				camp.addToAttendeeList(attendee);
				writeCsv.addToAttendeeList(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), camp.getCampID());
				System.out.println("Successfully Registered as attendee");
				return;
			}
			else { //if attendee slots <= 0
				System.out.println("Attendee slots are filled");
				return;
			}
		}

		//if registered as attendee and committee but wants to register as attendee
		if(!studentDoesNotHaveAttendeeCamp && studentHasCommittee && !asCommittee){

			for(int i = 0; i<student.getIsAttendeeCamps().size();i++){//check if registered as attendee already
				if(student.getIsAttendeeCamps().get(i).getCampID().equals(camp.getCampID())){
					System.out.println("You have already registered for this camp as an Attendee!");
					return;
				}
			}

			for( int i = 0; i < student.getIsAttendeeCamps().size();i++) { // other attendee camp date clash
				if(checkForClash(student.getIsAttendeeCamps().get(i).getCampDatesStart(), student.getIsAttendeeCamps().get(i).getCampDatesEnd(), camp.getCampDatesStart(), camp.getCampDatesEnd())){
					System.out.println("Date clash with other Attendee Camp!");
					return;
				}
			}

			//committe date clash
			if(checkForClash(student.getIsCommitteeCamp().getCampDatesStart(), student.getIsCommitteeCamp().getCampDatesEnd(), camp.getCampDatesStart(), camp.getCampDatesEnd())){
				System.out.println("Date clash with Committee Camp!");
				return;
			}
			
			if(availableAttendeeSlots>0){ //if attendee slots > 0
				student.addToIsAttendeeCamps(camp);
				CampAttendee attendee = new CampAttendee(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), student.getUserType());
				camp.addToAttendeeList(attendee);
				writeCsv.addToAttendeeList(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), camp.getCampID());
				System.out.println("Successfully Registered as attendee");
				return;
			}
			else { //if attendee slots <= 0
				System.out.println("Attendee slots are filled");
				return;
			}
		}

		//if registered as attendee but not committee but wants to register as attendee
		if(!studentDoesNotHaveAttendeeCamp && !studentHasCommittee && !asCommittee){
			for(int i = 0; i<student.getIsAttendeeCamps().size();i++){//check if registered as attendee already
				if(student.getIsAttendeeCamps().get(i).getCampID().equals(camp.getCampID())){
					System.out.println("You have already registered for this camp as an Attendee!");
					return;
				}
			}

			for( int i = 0; i < student.getIsAttendeeCamps().size();i++) {// other attendee camp date clash
				if(checkForClash(student.getIsAttendeeCamps().get(i).getCampDatesStart(), student.getIsAttendeeCamps().get(i).getCampDatesEnd(), camp.getCampDatesStart(), camp.getCampDatesEnd())){
					System.out.println("Date clash with other Attendee Camp!");
					return;
				}
			}

			if(availableAttendeeSlots>0){ //if attendee slots > 0
				student.addToIsAttendeeCamps(camp);
				CampAttendee attendee = new CampAttendee(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), student.getUserType());
				camp.addToAttendeeList(attendee);
				writeCsv.addToAttendeeList(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), camp.getCampID());
				System.out.println("Successfully Registered as attendee");
				return;
			}
			else { //if attendee slots <= 0
				System.out.println("Attendee slots are filled");
				return;
			}
		}

		//if registered as committee but not any attendee but wants to register as attendee
		if(studentDoesNotHaveAttendeeCamp && studentHasCommittee && !asCommittee){
			for(int i = 0;i<camp.getCommitteeList().size();i++){
				if(camp.getCommitteeList().get(i).getUserID().equals(student.getUserID())){
					System.out.println("Registered as Committee for this camp!");
					return;
				}
			}
			//Committee Date Clash
			if(checkForClash(student.getIsCommitteeCamp().getCampDatesStart(), student.getIsCommitteeCamp().getCampDatesEnd(), camp.getCampDatesStart(), camp.getCampDatesEnd())){
				System.out.println("Date clash with Committee Camp!");
				return;
			}

			if(availableAttendeeSlots>0){ //if attendee slots > 0
				student.addToIsAttendeeCamps(camp);
				CampAttendee attendee = new CampAttendee(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), student.getUserType());
				camp.addToAttendeeList(attendee);
				writeCsv.addToAttendeeList(student.getName(), student.getUserID(),student.getPassword(), student.getFaculty(), camp.getCampID());
				System.out.println("Successfully Registered as attendee");
				return;
			}
			else { //if attendee slots <= 0
				System.out.println("Attendee slots are filled");
				return;
			}
		}
	}
}