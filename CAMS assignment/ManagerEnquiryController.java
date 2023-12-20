package CamApp;
import java.util.Scanner;

public class ManagerEnquiryController implements IViewUnrestricted, IReply {

	public boolean reply(Camp myCamp) {

		boolean replyFlag = false;
		if (myCamp == null)
		{
			System.out.println("No camps created yet!");
			return replyFlag;
		}
	
		int choice;
		Scanner sc = new Scanner(System.in);
		CampEnquiry temp;

		do {
			System.out.println("Choose enquiry to reply: ");
			this.view(myCamp);
			System.out.println("0. Exit"); 
			
			do{
				if (sc.hasNextInt()){
					choice = sc.nextInt();
					sc.nextLine();
					if(choice >=0 && choice <= myCamp.getEnquiryList().size()){
						break;
					}
					else{
						System.out.println("Please input valid integer");
					}
				}
				else{
					System.out.println("Please input valid integer");
					sc.nextLine();
				}
			}while(true);

			if (choice == 0) //prevent index out of bound error
				break;

			temp = myCamp.getEnquiryList().get(choice-1);
		
			if (temp.getHasReply() == false)
			{
				CsvFilePaths a = new CsvFilePaths();
				CsvEditMethods b = new CsvEditMethods();
				System.out.println("Enter Reply: ");
				String reply = sc.nextLine();
				temp.setReplyContent(reply);
				temp.setHasReply(true);
				b.replyEnquiry(a.getCampEnquiryListFilePath(), "replyContent", reply, temp.getUserID(), temp.getCampID());
				b.replyEnquiry(a.getCampEnquiryListFilePath(), "hasReply", "TRUE", temp.getUserID(), temp.getCampID());
				replyFlag = true;
			}
			else System.out.println("Enquiry has been answered!");

		} while (choice != 0);
		return replyFlag;

		// System.out.println("Exiting Enquiry Page...");
	}

	public void view(Camp myCamp) {

		if (myCamp == null)
		{
			System.out.println("No camps created yet!");
			return;
		}

		System.out.println("---Enquiry List----");
		if(myCamp.getEnquiryList().size() == 0){
			System.out.println("No Enquiries available.");
		}
		else{
			for (int i = 0; i < myCamp.getEnquiryList().size(); i++){
				System.out.println(i+1 + ". " + myCamp.getEnquiryList().get(i).getEnquiryContent() + "     | Reply: "+myCamp.getEnquiryList().get(i).getReplyContent());
			}
		}
		System.out.println("-----------");
	
	}

}