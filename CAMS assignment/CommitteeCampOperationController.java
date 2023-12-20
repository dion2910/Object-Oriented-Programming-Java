package CamApp;
public class CommitteeCampOperationController implements IView {

	/**
	Display details of the target Camp.
	
	@param camp Target Camp
	@param userID UserID of App user
	 
	*/
	public void view(Camp camp,String userID) {
		System.out.println("Camp Name:" + camp.getCampID());
		System.out.println("Camp Duration:" + camp.getCampDatesStart() + " to " + camp.getCampDatesEnd());
		System.out.println("Camp Registration Deadline: "+ camp.getRegistrationDeadline());
		System.out.println("Faculty: " + camp.getFaculty());
		System.out.println("Open to NTU: " + Boolean.toString(camp.getOpenToNTU()));
		System.out.println("Location: " + camp.getLocation());
		System.out.println("Total Slots: " + Integer.toString(camp.getTotalSlots()));
		System.out.println("Empty Slots: " + Integer.toString(camp.getEmptySlots()));
		System.out.println("Committee Slots: "+ Integer.toString(camp.getCommitteeSlots()));
		System.out.println("Description: " + camp.getInfo());
		System.out.println("Staff in charge: " + camp.getStaffIC());
	}

}