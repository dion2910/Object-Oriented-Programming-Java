package CamApp;
import java.util.ArrayList;

public class Student extends User {
	

	//Attributes
	private Camp isCommitteeCamp; // because Student can only join 1 camp as committee
	private ArrayList <Camp> isAttendeeCamps = new ArrayList<Camp>();
	
	/**
	* Create Student object
	* @param name Student's name
	* @param userID Student's user
	* @param password Student's login password
	* @param faculty Student's Faculty
	* @param userType To identify user as Student
	*/

	//Constructor
	public Student(String name, String userID, String password,String faculty, String userType){
		super(name,userID,password,faculty,userType);
	}

	/**
	* Create Student object
	* @param name Student's name
	* @param userID Student's user
	* @param password Student's login password
	* @param faculty Student's Faculty
	* @param userType To identify user as Student
	* @param isCommitteeCamp Stores the camp that the student is registered as committee
	*/
	public Student(String name, String userID, String password,String faculty, String userType, Camp isCommitteeCamp){
		super(name,userID,password,faculty,userType);
		this.isCommitteeCamp=isCommitteeCamp;
	}

	/**
	* Create Student object
	* @param name Student's name
	* @param userID Student's user
	* @param password Student's login password
	* @param faculty Student's Faculty
	* @param userType To identify user as Student
	* @param isAttendeeCamps Store the camp(s) that the student is registered as attendee 
	*/
	public Student(String name, String userID, String password,String faculty, String userType,  ArrayList <Camp> isAttendeeCamps){
		super(name,userID,password,faculty,userType);
		this.isAttendeeCamps=isAttendeeCamps;
	}

	/**
	* View all visible camps
	* @param campList Array list of camp(s) created
	*
	* @return list of camp(s) created
	*/
	public ArrayList<Camp> viewAllCamp(CampList campList){
		if(campList.getCampList().size() == 0){
			System.out.println("No camps have been created.");
			return campList.getCampList();
		}
		StudentCampOperationController controller = new StudentCampOperationController();
		return controller.viewVisibleCamps(campList, this);
	}

	/**
	 * Student to regsiter to a camp using StudentCampOperationController
	 * @param camp Camp that the student want to register
	 * @param asCommittee If the student want to register as committee
	 */
	public void registerForCamp(Camp camp, boolean asCommittee) {
		StudentCampOperationController controller = new StudentCampOperationController();
		controller.register(camp, asCommittee, this);
	}

	/**
	 * List all the camp(s) student registered
	 * @return Camp(s) that the student registered for
	 */
	public ArrayList<Camp> viewRegistered(){
		StudentCampOperationController controller = new StudentCampOperationController();
		return controller.viewRegistered(this);
	}

	/**
	 * Submit enquiry for student using GeneralEnquiryController
	 * @param camp  that student wish to submit the enquiry
	 */
	public void createEnquiry(Camp camp) {
		if((this.getIsCommitteeCamp()!=null) && (camp.getCampID().equals(this.getIsCommitteeCamp().getCampID()))){
			System.out.println("You are a committee, you cannot submit enquiries to this camp: "+camp.getCampID()+" !!");
			return;
		}
		GeneralEnquiryController enquiryController = new GeneralEnquiryController();
		enquiryController.submit(camp,this.getUserID());
	}

	/**
	 * View enquiry that the student has created using GeneralEnquiryController
	 * @param camp Camp that student wish to view the enquiry
	 */
	public void viewEnquiry(Camp camp) {
		GeneralEnquiryController enquiryController = new GeneralEnquiryController();
		enquiryController.view(camp,this.getUserID());
	}

	/**
	 * Edit enquiry of the student using GeneralEnquiryController
	 * @param camp Camp that the student wants to edit an enquiry from
	 */
	public void editMyEnquiry(Camp camp) {
		GeneralEnquiryController enquiryController = new GeneralEnquiryController();
		enquiryController.edit(camp,this.getUserID());
	}

	/**
	 * Delete enquiry of the student using GeneralEnquiryController
	 * @param camp Camp that the student wants to delete an enquiry from
	 */
	public void deleteMyEnquiry(Camp camp) {
		GeneralEnquiryController enquiryController = new GeneralEnquiryController();
		enquiryController.delete(camp,this.getUserID());
	}


	//Get and Set methods
	public Camp getIsCommitteeCamp(){
		return this.isCommitteeCamp;
	}

	public void setIsCommitteeCamp(Camp camp){
		this.isCommitteeCamp = camp;
	}

	public ArrayList<Camp> getIsAttendeeCamps(){
		return this.isAttendeeCamps;
	}

	public void setIsAttendeeCamps(ArrayList<Camp> thisCampArrayList){
		this.isAttendeeCamps = thisCampArrayList;
	}

	public void addToIsAttendeeCamps(Camp camp) {
		this.isAttendeeCamps.add(camp);
	}

}