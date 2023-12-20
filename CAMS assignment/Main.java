package CamApp;

import java.util.ArrayList;
import java.util.Scanner;

/* DO READ THE README.MD FILE BEFORE RUNNING THE PROGRAM
 * 
 * THERE IS A NECESSARY STEP TO CHANGE THE APPLICATION CSV FOLDER FILE PATH FOR THE APPLICATION TO RUN AS INTENDED.
 * 
 */
 
public class Main {
    public static void main(String[] args) {

        // //START MAIN APP BLOCK
        Student thisStudent = null;  
        Staff thisStaff = null;

        CsvFilePaths applicationCsvFilePaths = new CsvFilePaths();
        Scanner sc = new Scanner(System.in);
        
        //Authenticate and create user
        Authenticator appAuthenticator = new Authenticator();
        User thisUser = appAuthenticator.authenticate(applicationCsvFilePaths.getStudentListFilePath(), applicationCsvFilePaths.getStaffListFilePath());
        //System.out.println("thisUser created: " + thisUser.getUserID());
        if (thisUser.getPassword().equals("password")) {
            System.out.println("Welcome to CAMs! We realise you have yet to change your default password, do remember to update your password for security reasons.");
        }


        //Create Camplist
        CampList campList = new CampList();
        //ArrayList<Camp> CampList = new ArrayList<Camp>();
        CsvReadMethods.ConstructCampList(campList, applicationCsvFilePaths.getCampListFilePath());
        // System.out.print("CampList created: ");
        // for (int i=0;i<campList.getCampList().size();i++) { System.out.print(campList.getCampList().get(i).getCampID() + ", "); };
        // System.out.print("\n");

        //Create all camps committeeList
        for (int i=0; i<campList.getCampList().size(); i++) {

            String thisCampCommitteeListFilepath = applicationCsvFilePaths.getApplicationFolderFilePath() + campList.getCampList().get(i).getCampID() + "_committee_list.csv";

            ArrayList<CampCommittee> thisCampCommitteeList = new ArrayList<CampCommittee>();
            CsvReadMethods.ConstructCampCommitteeList(thisCampCommitteeList, campList.getCampList().get(i), thisCampCommitteeListFilepath);

            campList.getCampList().get(i).setCommitteeList(thisCampCommitteeList);
        }
        
        // for (int i=0; i<campList.getCampList().size(); i++) {
        //     ArrayList<CampCommittee> currentCampCommitteeList = campList.getCampList().get(i).getCommitteeList();

        //     System.out.print(campList.getCampList().get(i).getCampID() + "'s committeeList Created: ");
        //     for (int j=0; j<currentCampCommitteeList.size(); j++) {
        //         System.out.print(currentCampCommitteeList.get(j).getUserID() + ", ");
        //     }
        //     System.out.print("\n");
        // }

        //Create all camps attendeeList
        for (int i=0; i<campList.getCampList().size(); i++) {

            String thisCampAttendeeListFilepath = applicationCsvFilePaths.getApplicationFolderFilePath() + campList.getCampList().get(i).getCampID() + "_attendee_list.csv";

            ArrayList<CampAttendee> thisCampAttendeeList = new ArrayList<CampAttendee>();
            CsvReadMethods.ConstructCampAttendeeList(thisCampAttendeeList, campList.getCampList().get(i), thisCampAttendeeListFilepath);

            campList.getCampList().get(i).setAttendeeList(thisCampAttendeeList);
        }
        
        // for (int i=0; i<campList.getCampList().size(); i++) {
        //     ArrayList<CampAttendee> currentCampAttendeeList = campList.getCampList().get(i).getAttendeeList();

        //     System.out.print(campList.getCampList().get(i).getCampID() + "'s attendeeList Created: ");
        //     for (int j=0; j<currentCampAttendeeList.size(); j++) {
        //         System.out.print(currentCampAttendeeList.get(j).getUserID() + ",");
        //     }
        //     System.out.print("\n");
        // }
        
        //Create all camps enquiryList
        for (int i=0; i<campList.getCampList().size(); i++) {
            ArrayList<CampEnquiry> currentCampEnquiryList = new ArrayList<CampEnquiry>();
            CsvReadMethods.ConstructThisCampEnquiryList(currentCampEnquiryList ,campList.getCampList().get(i), applicationCsvFilePaths.getCampEnquiryListFilePath());
            campList.getCampList().get(i).setEnquiryList(currentCampEnquiryList);
        }

        // for (int i=0; i<campList.getCampList().size(); i++) {

        //     System.out.print(campList.getCampList().get(i).getCampID() + "'s enquiryList Created: ");
        //     for (int j=0; j<campList.getCampList().get(i).getEnquiryList().size(); j++) {
        //         System.out.print(campList.getCampList().get(i).getEnquiryList().get(j).getHasReply() + ",");
        //     }
        //     System.out.print("\n");
        // }


        //Create all camps suggestionList
        for (int i=0; i<campList.getCampList().size(); i++) {
            ArrayList<CampSuggestion> currentCampSuggestionList = new ArrayList<CampSuggestion>();
            CsvReadMethods.ConstructThisCampSuggestionList(currentCampSuggestionList ,campList.getCampList().get(i), applicationCsvFilePaths.getCampSuggestionListFilePath());
            campList.getCampList().get(i).setSuggestionList(currentCampSuggestionList);
        }

        // for (int i=0; i<campList.getCampList().size(); i++) {

        //     System.out.print(campList.getCampList().get(i).getCampID() + "'s suggestionList Created: ");
        //     for (int j=0; j<campList.getCampList().get(i).getSuggestionList().size(); j++) {
        //         System.out.print(campList.getCampList().get(i).getSuggestionList().get(j).getSuggestionContent() + ", ");
        //     }
        //     System.out.print("\n");
        // }

        //Create Student or Staff object depending on UserType
        if (thisUser.getUserType().equals("student")) {

            //Create Student
            thisStudent = CsvReadMethods.ConstructStudent(thisUser.getUserID(), applicationCsvFilePaths.getStudentListFilePath());
            // System.out.println("Student object created: " + thisStudent.getName());

            //Set Student isCommitteeCamp and isAttendeeCamps
            thisStudent.setIsCommitteeCamp(CsvReadMethods.ConstructThisStudentIsCommitteeCamp(thisStudent, campList));
            thisStudent.setIsAttendeeCamps(CsvReadMethods.ConstructThisStudentIsAttendeeCamps(thisStudent, campList));

        } else if (thisUser.getUserType().equals("staff")) {

            //Create Staff
            thisStaff = CsvReadMethods.constructStaff(thisUser.getUserID(), applicationCsvFilePaths.getStaffListFilePath());
            // System.out.println("Staff object created: " + thisStaff.getName());

            //Create and set Staff MyCamp
            for (int i=0; i<campList.getCampList().size(); i++) {
                if (campList.getCampList().get(i).getStaffIC().equals(thisStaff.getUserID())) {
                    Camp thisStaffCamp = campList.getCampList().get(i);
                    thisStaff.setMyCamp(thisStaffCamp);
                }
            }

            //System.out.println("Staff MyCamp created: " + thisStaff.getMyCamp().getCampID());

        } else {
            System.out.println("User does not exist");
        }

        //User Interface
           
        if (thisUser.getUserType().equals("student")) {
            StudentInterface.showStudentInterface(thisStudent, campList);
        } else {
            StaffInterface.showStaffInterface(campList, thisStaff);
        }

        sc.close();
        
        // // END MAIN APP BLOCK 
 
    }

}
