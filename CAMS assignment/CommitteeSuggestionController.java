package CamApp;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CommitteeSuggestionController implements IView, IEdit, ISubmit, IDelete {

	CsvWriteMethods writeCsv = new CsvWriteMethods();
	CsvEditMethods editCsv = new CsvEditMethods();
	CsvFilePaths pathing = new CsvFilePaths();

	public void view(Camp camp, String userID) {
		System.out.println("-----My Suggestion-----");
		int i;
		for(i=0;i<camp.getSuggestionList().size();i++){
			if(camp.getSuggestionList().get(i).getUserID().equals(userID)){
				System.out.println("Your Suggestion: " +camp.getSuggestionList().get(i).getSuggestionContent());
			}
		}
		if(i==0){
			System.out.println("No Suggestions.");
		}
	}

	public void submit(Camp camp, String userID) {
		Scanner sc = new Scanner(System.in);
		for(int i =0;i<camp.getSuggestionList().size();i++){
			if(camp.getSuggestionList().get(i).getUserID().equals(userID)){
				System.out.println("You have an exisiting suggestion!");
				return;
			}
		}
		try{
			System.out.println("Enter your suggestion: ");
			String content=sc.nextLine();
			CampSuggestion suggestion= new CampSuggestion(camp, userID, content);
			camp.addToSuggestionList(suggestion);
			writeCsv.addToSuggestionList(camp.getCampID(), userID, content, false);
		}catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
        } 
		

	}

	public void edit(Camp camp,String userID) {
		CampSuggestion suggestion=null;
		for(int i = 0; i<camp.getSuggestionList().size();i++){
			if(camp.getSuggestionList().get(i).getUserID().equals(userID))
			{
				suggestion=camp.getSuggestionList().get(i);
				break;
			}
		}
		if(suggestion == null){
			System.out.println("No Suggestions!");
			return;
		}
		if(suggestion.getIsApproved()){
			System.out.println("Cannot edit approved suggestions!");
			return;
		}
		this.view(camp,userID);
		Scanner sc = new Scanner(System.in);
		try{
			System.out.println("Edit suggestion: ");
			String content=sc.nextLine();
			editCsv.editSuggestion(pathing.getCampSuggestionListFilePath(), "suggestionContent", content, suggestion.getUserID(), suggestion.getCampID());
			suggestion.setSuggestionContent(content);

		}catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
        } 
		System.out.println("Suggestion editted successfully!");
	}

	public boolean delete(Camp camp,String userID) {
		boolean deleteFlag = false;
		CampSuggestion suggestion=null;
		for(int i = 0; i<camp.getSuggestionList().size();i++){
			if(camp.getSuggestionList().get(i).getUserID().equals(userID))
			{
				suggestion=camp.getSuggestionList().get(i);
				break;
			}
		}
		if(suggestion == null){
			System.out.println("No Suggestions!");
			return deleteFlag;
		}
		if(suggestion.getIsApproved()){
			System.out.println("Cannot delete approved suggestions!");
			return deleteFlag;
		}
		this.view(camp, userID);
		Scanner sc = new Scanner(System.in);
		boolean isValidInput = false;
		do {
			try {
				System.out.println("Confirm delete? (Y/N): ");
				String choice = sc.nextLine();
		
				if (choice.equalsIgnoreCase("Y")) {
					editCsv.deleteMySuggestion(pathing.getCampSuggestionListFilePath(), suggestion.getCampID(), suggestion.getUserID());
					camp.getSuggestionList().remove(suggestion);
					deleteFlag = true;
					System.out.println("Suggestion deleted successfully");
					isValidInput = true;
				} else if (choice.equalsIgnoreCase("N")) {
					System.out.println("Returning to the main menu...");
					isValidInput = true;
				} else {
					System.out.println("Invalid input. Please enter 'Y' or 'N'.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter 'Y' or 'N'.");
			} 
		} while (!isValidInput);
		return deleteFlag;			
	}
}