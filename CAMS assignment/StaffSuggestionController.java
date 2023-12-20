package CamApp;
import java.util.Scanner;

public class StaffSuggestionController implements IViewUnrestricted, IApprove {
	/**
	Contains the suggestion operations for Staff.
	*/
	
	public void approve(Camp myCamp) {

		if (myCamp == null)
		{
			System.out.println("No camps created yet!");
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		CampSuggestion temp;
		CsvFilePaths a = new CsvFilePaths();
		CsvEditMethods b = new CsvEditMethods();
		int choice;
		do {
			System.out.println("Choose suggestion to approve: ");
			this.view(myCamp);
			System.out.println("0. Exit");

			do{
				if (sc.hasNextInt()){
					choice = sc.nextInt();
					sc.nextLine();
					if(choice >=0 && choice <= myCamp.getSuggestionList().size()){
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

			if (choice == 0)//prevent index out of bound error
				break;
			
			temp = myCamp.getSuggestionList().get(choice-1);

			if (temp.getIsApproved() == false)
			{
				System.out.println("Suggestion approved!");
				temp.setIsApproved(true);
				b.approveSuggestion(a.getCampSuggestionListFilePath(), "isApproved", temp.getUserID(), temp.getCampID());
				for (int i = 0; i < myCamp.getCommitteeList().size(); i++)
				{
					if(temp.getUserID().equals(myCamp.getCommitteeList().get(i).getUserID()))
					{
						myCamp.getCommitteeList().get(i).addPoints();
						b.addPoints(a.getApplicationFolderFilePath()+temp.getCampID()+"_committee_list.csv", "Points", String.valueOf(myCamp.getCommitteeList().get(i).getPoints()),myCamp.getCommitteeList().get(i).getUserID());
					}
				}
			}
			else System.out.println("Suggestion has already been approved");

		} while (choice != 0);


	}

	public void view(Camp myCamp) {

		if (myCamp == null)
		{
			System.out.println("No camps created yet!");
			return;
		}

		System.out.println("-----Suggestion List------");
		if(myCamp.getSuggestionList().size() == 0){
			System.out.println("No Suggestions available");
		}
		else{
			for (int i = 0; i < myCamp.getSuggestionList().size(); i++){
				System.out.println(i+1 + ". " + myCamp.getSuggestionList().get(i).getSuggestionContent());
			}
		}
		System.out.println("-----------");
	}
}