package CamApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentInterface {

    private static boolean checkForClash(LocalDate startDate1, LocalDate endDate1, LocalDate startDate2, LocalDate endDate2){
		// Check for clashes
		return !(endDate1.isBefore(startDate2) || startDate1.isAfter(endDate2));
	}

    public static void showStudentInterface(Student student,CampList campList)
    {
        Scanner sc = new Scanner(System.in);
        CsvEditMethods editCsv = new CsvEditMethods();
        CsvFilePaths filepath = new CsvFilePaths();

        System.out.println("You have logged in as a Student.");
        boolean logout = true;
        do{
            System.out.println("------------");
            System.out.println("1. View available camps.");
            System.out.println("2. View registered camps.");
            System.out.println("3. Enquire on a camp.");
            System.out.println("4. Change password.");
            System.out.println("5. Log out.");
            System.out.println("Select an option: ");
            System.out.println("------------");
            int choice = 0;

            //checking for invalid input
            do{
                if (sc.hasNextInt()){
                    choice = sc.nextInt();
                    sc.nextLine();
                    if(choice >=1 && choice <= 5){
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

            switch(choice){ //First Page after selectiion
            case 1://View ALL camps
                System.out.println("------------");
                System.out.println("Camps available");
                System.out.println("------------");
                student.viewAllCamp(campList);
                if(campList.getCampList().size()==0){
                    break;
                }
                System.out.println("------------");
                System.out.println("1. Register for camp.");
                System.out.println("2. Back to menu.");
                int subchoice = sc.nextInt();
                sc.nextLine();

                switch(subchoice){//Second page - Camp registration
                    case 1://Register for camp
                        System.out.println("Which camp do you want to register for?");
                        System.out.println("------------");
                        ArrayList<Camp> viewableCamps =student.viewAllCamp(campList);
                        System.out.println("------------");
                        System.out.println("Select the camp you want to register for: ");
                        int subchoice2 = 0;
                        do {
                            if(sc.hasNextInt()){
                                subchoice2 = sc.nextInt();
                                sc.nextLine();
                                if(subchoice2 < 1 || subchoice2 > viewableCamps.size()){
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
                        
                        String asCommittee;
                        

                        //student and camp list
                        int flagger = 0;
                        for(int i=0;i<student.getIsAttendeeCamps().size();i++){
                            if(student.getIsAttendeeCamps().get(i).getCampID().equals(viewableCamps.get(subchoice2-1).getCampID())){
                                System.out.println("You are already registered as an attendee for this camp");
                                flagger =1;
                                break;  
                            }
                        }
                        if(flagger == 1){break;}
                
                        
                        if((student.getIsCommitteeCamp() != null) && ((student.getIsCommitteeCamp().getCampID().equals(viewableCamps.get(subchoice2-1).getCampID())))){
                            System.out.println("You are already registered as a committee for this camp");
                            break;
                        }
                         
                        //check for committee date clash
                        if((student.getIsCommitteeCamp() != null) && (checkForClash(student.getIsCommitteeCamp().getCampDatesStart(), student.getIsCommitteeCamp().getCampDatesEnd(), viewableCamps.get(subchoice2-1).getCampDatesStart(), viewableCamps.get(subchoice2-1).getCampDatesEnd()))){
                            System.out.println("Date clash with Committee Camp!");
                            break;
                        }
                        //check for attendee date clash
                        for( int i = 0; i < student.getIsAttendeeCamps().size();i++) {//other attendee camp date clash
                            if(checkForClash(student.getIsAttendeeCamps().get(i).getCampDatesStart(), student.getIsAttendeeCamps().get(i).getCampDatesEnd(), viewableCamps.get(subchoice2-1).getCampDatesStart(), viewableCamps.get(subchoice2-1).getCampDatesEnd())){
                                System.out.println("Dates clash with other Attendee Camp!");
                                flagger=1;
                                break;
                            }
                        } 
                        if(flagger==1){break;}
                        
                        do{
                            System.out.println("Do you wish to register as committee?(Y/N): ");
                            asCommittee = sc.nextLine();
                        }while(!((asCommittee.toLowerCase().equalsIgnoreCase("Y")) || (asCommittee.toLowerCase().equalsIgnoreCase("N"))));
                        boolean asCommitteebool = BooleanParser.parseCustomBoolean(asCommittee);
                        Camp temp = viewableCamps.get(subchoice2-1);
                        student.registerForCamp(temp, asCommitteebool);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }   
                break;
            case 2:// View Registered Camps
                System.out.println("------------");
                if(student.getIsAttendeeCamps().size()==0 && student.getIsCommitteeCamp()==null){
                    System.out.println("You are not registered for any camps.");
                    break;
                }
                student.viewRegistered();
                System.out.println("------------");
                System.out.println("1. View and act on a camp.");
                System.out.println("2. Back to menu.");
                int case2Choice = 0;
                do {
                    if(sc.hasNextInt()){
                        case2Choice = sc.nextInt();
                        sc.nextLine();
                        if(case2Choice < 1 || case2Choice > 2){
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

                switch(case2Choice){
                    case 1://Camp operations
                        System.out.println("Select a camp to view");
                        System.out.println("------------");
                        ArrayList<Camp> registeredCamps = student.viewRegistered();
                        System.out.println("------------");
                        int campToView = 0;
                        do {
                            if(sc.hasNextInt()){
                                campToView = sc.nextInt();
                                sc.nextLine();
                                if(campToView < 1 || campToView > registeredCamps.size()){
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
                        Camp temp = registeredCamps.get(campToView-1);
                        System.out.println("------------");
                        temp.showCampDetails();
                        boolean act = true;
                        do{
                            if ( student.getIsCommitteeCamp() != null && (student.getIsCommitteeCamp().getCampID().equals(temp.getCampID())) ) {
                                int thisCommitteePoints = 0;
                                for (int i=0; i<student.getIsCommitteeCamp().getCommitteeList().size(); i++) {
                                    if (student.getUserID().equals(student.getIsCommitteeCamp().getCommitteeList().get(i).getUserID())) {
                                        thisCommitteePoints = student.getIsCommitteeCamp().getCommitteeList().get(i).getPoints();
                                        break;
                                    }
                                }
                                CampCommittee thisCommittee = new CampCommittee(student.getName(), student.getUserID(), student.getPassword(), student.getFaculty(), "student", student.getIsCommitteeCamp(), thisCommitteePoints);
                                System.out.println("------------");
                                System.out.println("1. View camp details.");
                                System.out.println("2. Submit a suggestion.");
                                System.out.println("3. View my suggestion.");
                                System.out.println("4. Edit my suggestion.");
                                System.out.println("5. Delete my suggestion.");
                                System.out.println("6. View enquiries.");
                                System.out.println("7. Reply enquiries.");
                                System.out.println("8. Generate report.");
                                System.out.println("9. Generate enquiry report.");
                                System.out.println("10. Go back to main menu.");
                                System.out.println("------------");
                                int action = 0;
                                do {
                                    if(sc.hasNextInt()){
                                        action = sc.nextInt();
                                        sc.nextLine();
                                        if(action < 1 || action >10){
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
                                switch(action){//committee operations
                                    case 1:
                                        System.out.println("------------");
                                        temp.showCampDetails();
                                        break;
                                    case 2:
                                        thisCommittee.submitMySuggestion();
                                        break;
                                    case 3:
                                        thisCommittee.viewSuggestion();
                                        break;
                                    case 4:
                                        thisCommittee.editMySuggestions();
                                        break;
                                    case 5:
                                        thisCommittee.deleteMySuggestions();
                                        break;
                                    case 6:
                                        thisCommittee.viewEnquiry();
                                        break;
                                    case 7:
                                        thisCommittee.replyEnquiry();
                                        break;
                                    case 8:
                                        thisCommittee.generateReport();
                                        break;
                                    case 9:
                                        thisCommittee.generateEnquiryReport();
                                        break;
                                    case 10:
                                        act = false;
                                    default:
                                        break;
                                }
                                
                            } else {
                                CampAttendee thisAttendee = new CampAttendee(student.getName(), student.getUserID(), student.getPassword(), student.getFaculty(), "student",student.getIsAttendeeCamps());
                                System.out.println("------------");
                                System.out.println("1. Withdraw from camp.");
                                System.out.println("2. Go back to main menu.");
                                System.out.println("------------");
                                int action = 0;
                                do {
                                    if(sc.hasNextInt()){
                                        action = sc.nextInt();
                                        sc.nextLine();
                                        if(action < 1 || action >2){
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
                                
                                switch(action){//attendee operations
                                    case 1:
                                        thisAttendee.withdrawFromCamp();
                                        act = false;
                                        break;
                                    case 2:
                                        act = false;
                                    default:
                                        break;
                                }
                            }
                            
                        }while(act);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }   
                break;
            case 3:
                System.out.println("------------");
                System.out.println("Camps available");
                System.out.println("------------");
                ArrayList<Camp> allcamps = student.viewAllCamp(campList);
                if(allcamps.size()==0){
                    break;
                }
                System.out.println("------------");
                System.out.println("Select a camp to enquire: ");
                int campselect = 0;
                do {
                    if(sc.hasNextInt()){
                        campselect = sc.nextInt();
                        sc.nextLine();
                        if(campselect < 1 || campselect > allcamps.size()){
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
                Camp temp = allcamps.get(campselect-1);//select the camp
                boolean notRegisteredAsCommittee = false;
                for(int i =0;i<temp.getCommitteeList().size();i++){
                    if(temp.getCommitteeList().get(i).getUserID().equals(student.getUserID())){
                        System.out.println("You are a committee.");
                        notRegisteredAsCommittee = true;
                        break;
                    }
                }
                if(notRegisteredAsCommittee){
                    break;
                }
                
                boolean istrue = true;
                do{
                    System.out.println("------------");
                    System.out.println("1. Submit an enquiry.");
                    System.out.println("2. View my enquiry.");
                    System.out.println("3. Edit my enquiry.");
                    System.out.println("4. Delete my enquiry.");
                    System.out.println("5. Go back. ");
                    System.out.println("Select an option: ");
                    System.out.println("------------");
                    int action3 = 0;
                    do {
                        if(sc.hasNextInt()){
                            action3 = sc.nextInt();
                            sc.nextLine();
                            if(action3 < 1 || action3 >6){
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
                    
                    switch(action3){
                        
                        case 1:
                            student.createEnquiry(temp);
                            break;
                        case 2:
                            student.viewEnquiry(temp);
                            break;
                        case 3:
                            student.editMyEnquiry(temp);
                            break;
                        case 4:
                            student.deleteMyEnquiry(temp);
                            break;
                        case 5:
                            istrue = false;
                        default:
                            break;
                    }
                }while(istrue);
                break;

            case 4:
                System.out.println("Enter new password: ");
                String newPassword = sc.nextLine();
                student.setPassword(newPassword);
                //student list
                editCsv.updatePassword(filepath.getStudentListFilePath(), "Password", newPassword, student.getUserID().toUpperCase());
                if(student.getIsAttendeeCamps().size() != 0){//attendee camp list
                    for(int i = 0; i < student.getIsAttendeeCamps().size();i++){
                        editCsv.updatePassword(filepath.getApplicationFolderFilePath()+student.getIsAttendeeCamps().get(i).getCampID()+"_attendee_list.csv", "Password", newPassword, student.getUserID());
                    }
                }
                if(student.getIsCommitteeCamp()!=null){//committee camp
                    editCsv.updatePassword(filepath.getApplicationFolderFilePath()+student.getIsCommitteeCamp().getCampID()+"_committee_list.csv", "Password", newPassword, student.getUserID());
                }
                System.out.println("New password set successfully. ");
                break;
            case 5:
                System.out.println("Logging out....");
                logout = false;
                break;
            default:
                System.out.println("Invalid option!");
                break;
            }
        }while(logout);
    }
}
