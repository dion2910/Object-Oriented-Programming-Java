package CamApp;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralEnquiryController implements IView, IEdit, IDelete, ISubmit {
	
	CsvFilePaths pathing = new CsvFilePaths();
	CsvWriteMethods writeCsv = new CsvWriteMethods();
	CsvEditMethods a = new CsvEditMethods();
	
	/**
	 * View enquiry that the student has created
	 * @param camp Camp that student wish to view the enquiry
	 * @param userID Student 
	 */
	public void view(Camp camp, String userID) {
		int i;
		for(i=0;i<camp.getEnquiryList().size();i++){//only one enquiry
			if(camp.getEnquiryList().get(i).getUserID().equals(userID)){
				break;
			}
		}
		if(i==camp.getEnquiryList().size()){ // enquiry does not exist
			System.out.println("No Enquiries");
			return;
		}
		else{//Enquiry exist
			System.out.println("---My Enquiry---");
			System.out.println("Your Enquiry: "+camp.getEnquiryList().get(i).getEnquiryContent() + " | Reply: "+ camp.getEnquiryList().get(i).getReplyContent());
		}
	}

	/**
	 * Edit enquiry of the student
	 * @param camp Camp that student wish to edit the enquiry
	 * @param userID Student  
	 */
	public void edit(Camp camp, String userID) {
		CampEnquiry enquiry=null;
		for(int i = 0; i<camp.getEnquiryList().size();i++){
			if(camp.getEnquiryList().get(i).getUserID().equals(userID))
			{
				enquiry=camp.getEnquiryList().get(i);
				break;
			}
		}
		if(enquiry == null){
			System.out.println("No enquiry!");
			return;
		}
		if(enquiry.getHasReply()){
			System.out.println("Cannot edit replied enquiry!");
			return;
		}
		this.view(camp,userID);
		Scanner sc = new Scanner(System.in);
		try{//assumption is that each student can only submit one enquiry per camp
			System.out.println("Edit enquiry: ");
			String content=sc.nextLine();
			enquiry.setEnquiryContent(content);
			a.editEnquiry(pathing.getCampEnquiryListFilePath(), "enquiryContent", content, enquiry.getUserID(), camp.getCampID());	
		}catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
        } 
		System.out.println("Enquiry editted successfully!");
	}

	/**
	 * Delete enquiry of the student
	 * @param camp Camp that student wish to delete the enquiry
	 * @param userID Student 
	 */
	public boolean delete(Camp camp, String userID) {
		boolean deleteFlag = false;
		CampEnquiry enquiry=null;
		for(int i = 0; i<camp.getEnquiryList().size();i++){
			if(camp.getEnquiryList().get(i).getUserID().equals(userID))
			{
				enquiry=camp.getEnquiryList().get(i);
				break;
			}
		}
		if(enquiry == null){
			System.out.println("No enquiry!");
			return deleteFlag;
		}
		if(enquiry.getHasReply()){
			System.out.println("Cannot delete replied enquiry!");
			return deleteFlag;
		}
		this.view(camp, userID);
		Scanner sc = new Scanner(System.in);
		String choice;
		try{
			System.out.println("Confirm delete?(Y/N): ");
			choice=sc.nextLine();
			if(BooleanParser.parseCustomBoolean(choice)){
				a.deleteMyEnquiry(pathing.getCampEnquiryListFilePath(),enquiry.getCampID(), enquiry.getUserID());
				camp.getEnquiryList().remove(enquiry);
				deleteFlag = true;
				System.out.println("Enquiry deleted successfully");
				return deleteFlag;
			}
			else{
				System.out.println("Returning to main menu...");
				return deleteFlag;
			}
		}catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }	
		return deleteFlag;
	}

	/**
	 * Submit enquiry for student
	 * @param camp Camp that student wish to submit the enquiry
	 * @param userID Student  
	 */
	public void submit(Camp camp, String userID) {
		Scanner sc = new Scanner(System.in);
		for(int i =0;i<camp.getEnquiryList().size();i++){
			if(camp.getEnquiryList().get(i).getUserID().equals(userID)){
				System.out.println("You have an exisiting enquiry!");
				return;
			}
		}
		try{
			System.out.println("Enter your enquiry: ");
			String content=sc.nextLine();
			CampEnquiry enquiry= new CampEnquiry(camp, userID, content);
			camp.addToEnquiryList(enquiry);
			writeCsv.addToEnquiryList(camp.getCampID(), userID, content, null, false);
		}catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
        } 
		System.out.println("Enquiry submitted successfully");
	}
}