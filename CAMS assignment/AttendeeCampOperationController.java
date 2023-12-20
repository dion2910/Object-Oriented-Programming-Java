package CamApp;
import java.util.Scanner;

public class AttendeeCampOperationController implements IWithdraw {
	
	CsvEditMethods a = new CsvEditMethods();
	CsvFilePaths pathing = new CsvFilePaths();
	public void withdraw(CampAttendee campAttendee){
		System.out.println("-----Camp List------");
		for(int i=0;i<campAttendee.getIsAttendeeCamps().size();i++){
			System.out.println((i+1)+". "+campAttendee.getIsAttendeeCamps().get(i).getCampID());
		}
		System.out.println("Select camp to withdraw: ");
		Scanner sc = new Scanner(System.in);
		int choice = 0;;
		do {
			if(sc.hasNextInt()){
				choice = sc.nextInt();
				sc.nextLine();
				if(choice < 1 || choice >campAttendee.getIsAttendeeCamps().size()){
					System.out.println("Please input valid integer");
				}else{
					break;
				}
			}
			else{
				System.out.println("Please input valid integer");
				sc.nextLine();
			}
		} while (true);
		Camp temp = campAttendee.getIsAttendeeCamps().remove(choice-1);
		//temp = null;
		for(int j=0;j<temp.getAttendeeList().size();j++){
			CampAttendee attendee=temp.getAttendeeList().get(j);
			if(campAttendee.getUserID().equals(attendee.getUserID())){
				temp.getAttendeeList().remove(j);
				a.withdrawFromCamp((pathing.getApplicationFolderFilePath()+temp.getCampID()+"_attendee_list.csv"), temp.getCampID(), campAttendee.getUserID());
			}
		}

	}
}