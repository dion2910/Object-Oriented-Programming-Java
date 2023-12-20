package CamApp;

import java.io.*;
import java.util.*;

public class CsvWriteMethods {
    
    CsvFilePaths applicationCsvFilePaths = new CsvFilePaths();

    public CsvWriteMethods() {

    }
    private void createCsvFile(String fileName, String headers) {
        try {
            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getApplicationFolderFilePath()+fileName+"_list.csv", true));

            // Write the headers to the file
            writer.write(headers);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void staffCreateCamp(String campID, String campDateStart, String campDateEnd, String registrationDeadline, 
	String faculty, boolean openToNTU, String location, int totalSlots, int emptySlots, int committeeSlots, String info, String staffIC, boolean isVisible) {

        try{
            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getCampListFilePath(), true));


            // Construct a CSV line by concatenating all the values with a delimiter (e.g., comma)
            String csvLine = String.join(",",campID, campDateStart, campDateEnd, registrationDeadline, 
            faculty, String.valueOf(openToNTU),location,Integer.toString(totalSlots),Integer.toString(emptySlots),Integer.toString(committeeSlots),info, staffIC, String.valueOf(isVisible));

            // Write the constructed CSV line to the file
            writer.append(csvLine);

            // Add a newline character after each line
            writer.newLine();
            writer.close();

            // Create attendee list CSV file
            createCsvFile(campID + "_attendee", "Name,userID,Password,Faculty");

            // Create committee list CSV file
            createCsvFile(campID + "_committee", "Name,userID,Password,Faculty,Points");

            System.out.println("Camp created successfully!");

        } catch (IOException e){
            e.printStackTrace();
        }  
    }

    //generate report
    public void generateAttendeeReport(Camp myCamp)
    {
        // Create report list CSV file for camp
            createCsvFile(myCamp.getCampID() + "_attendeereport", "CampID,campDateStart,campDateEnd,registrationDeadline,faculty,openToNTU,location,totalSlots,emptySlots,committeeSlots,info,staffIC,isVisible");

        try{

            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getApplicationFolderFilePath()+myCamp.getCampID() + "_attendeereport_list.csv", true));


            String csvLine = String.join(",", myCamp.getCampID(),String.valueOf(myCamp.getCampDatesStart()), String.valueOf(myCamp.getCampDatesEnd()), String.valueOf(myCamp.getRegistrationDeadline()),
            myCamp.getFaculty(), String.valueOf(myCamp.getOpenToNTU()), myCamp.getLocation(), String.valueOf(myCamp.getTotalSlots()), String.valueOf(myCamp.getEmptySlots()),
            String.valueOf(myCamp.getCommitteeSlots()), myCamp.getInfo(), myCamp.getStaffIC(), String.valueOf(myCamp.getIsVisible()));
            writer.append(csvLine);
            writer.newLine();

            csvLine = String.join(",", "Name","UserID"); //header
            writer.append(csvLine);
            writer.newLine();

            ArrayList<CampAttendee> sortedAttendeeList = new ArrayList<CampAttendee>(myCamp.getAttendeeList()); //cone into a list
            Collections.sort(sortedAttendeeList, Comparator.comparing(CampAttendee::getName)); //sort the list

            for (int i = 0; i < sortedAttendeeList.size(); i++)
            {
                csvLine = String.join(",", sortedAttendeeList.get(i).getName(), sortedAttendeeList.get(i).getUserID());
                writer.append(csvLine);
                writer.newLine();
            }

            writer.close();
            System.out.println("Attendee Report created successfully!");

        } catch (IOException e){
            e.printStackTrace();
        }  
    }

    public void generateCommitteeReport(Camp myCamp)
    {
        // Create report list CSV file for camp
            createCsvFile(myCamp.getCampID() + "_committeereport", "CampID,campDateStart,campDateEnd,registrationDeadline,faculty,openToNTU,location,totalSlots,emptySlots,committeeSlots,info,staffIC,isVisible");

        try{

            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getApplicationFolderFilePath()+myCamp.getCampID() + "_committeereport_list.csv", true));

            String csvLine = String.join(",", myCamp.getCampID(),String.valueOf(myCamp.getCampDatesStart()), String.valueOf(myCamp.getCampDatesEnd()), String.valueOf(myCamp.getRegistrationDeadline()),
            myCamp.getFaculty(), String.valueOf(myCamp.getOpenToNTU()), myCamp.getLocation(), String.valueOf(myCamp.getTotalSlots()), String.valueOf(myCamp.getEmptySlots()),
            String.valueOf(myCamp.getCommitteeSlots()), myCamp.getInfo(), myCamp.getStaffIC(), String.valueOf(myCamp.getIsVisible()));
            writer.append(csvLine);
            writer.newLine();

            csvLine = String.join(",", "Name","UserID"); //header
            writer.append(csvLine);
            writer.newLine();

            ArrayList<CampCommittee> sortedCommitteeList = new ArrayList<CampCommittee>(myCamp.getCommitteeList()); //cone into a list
            Collections.sort(sortedCommitteeList, Comparator.comparing(CampCommittee::getName)); //sort the list

            for (int i = 0; i < sortedCommitteeList.size(); i++)
            {
                csvLine = String.join(",", sortedCommitteeList.get(i).getName(), sortedCommitteeList.get(i).getUserID());
                writer.append(csvLine);
                writer.newLine();
            }

            writer.close();
            System.out.println("Committee Report created successfully!");

        } catch (IOException e){
            e.printStackTrace();
        }  
    }

    public void generateStudentsReport(Camp myCamp)
    {
        // Create report list CSV file for camp
            createCsvFile(myCamp.getCampID() + "_studentsreport", "CampID,campDateStart,campDateEnd,registrationDeadline,faculty,openToNTU,location,totalSlots,emptySlots,committeeSlots,info,staffIC,isVisible");

        try{

            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getApplicationFolderFilePath()+myCamp.getCampID() + "_studentsreport_list.csv", true));

            String csvLine = String.join(",", myCamp.getCampID(),String.valueOf(myCamp.getCampDatesStart()), String.valueOf(myCamp.getCampDatesEnd()), String.valueOf(myCamp.getRegistrationDeadline()),
            myCamp.getFaculty(), String.valueOf(myCamp.getOpenToNTU()), myCamp.getLocation(), String.valueOf(myCamp.getTotalSlots()), String.valueOf(myCamp.getEmptySlots()),
            String.valueOf(myCamp.getCommitteeSlots()), myCamp.getInfo(), myCamp.getStaffIC(), String.valueOf(myCamp.getIsVisible()));
            writer.append(csvLine);
            writer.newLine();

            csvLine = String.join("," ,"Name", "UserID","Role"); //header
            writer.append(csvLine);
            writer.newLine();

            ArrayList <String[]> studentList = new ArrayList<String[]>();

            for (int i=0; i<myCamp.getAttendeeList().size();i++)
            {
                CampAttendee a = myCamp.getAttendeeList().get(i);
                String[] temp = {a.getName(),a.getUserID(),"Attendee" };
                studentList.add(temp);
            }

            for (int i=0; i<myCamp.getCommitteeList().size();i++)
            {
                CampCommittee a = myCamp.getCommitteeList().get(i);
                String[] temp = {a.getName(),a.getUserID(),"Committee" };
                studentList.add(temp);
            }

            studentList.sort(Comparator.comparing(arr -> arr[0])); //sorting sus

            for (int i = 0; i < studentList.size(); i++)
            {
                csvLine = String.join(",", studentList.get(i)[0],  studentList.get(i)[1], studentList.get(i)[2]);
                writer.append(csvLine);
                writer.newLine();
            }


            
            writer.close();
            System.out.println("Student Report created successfully!");

        } catch (IOException e){
            e.printStackTrace();
        }  
    }
    

    public void staffGeneratePerfReport(Camp myCamp)
    {
        // Create report list CSV file for camp
            createCsvFile(myCamp.getCampID() + "_performancereport", "CampID,campDateStart,campDateEnd,registrationDeadline,faculty,openToNTU,location,totalSlots,emptySlots,committeeSlots,info,staffIC,isVisible");

        try{

            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getApplicationFolderFilePath()+myCamp.getCampID() + "_performancereport_list.csv", true));

            String csvLine = String.join(",", myCamp.getCampID(),String.valueOf(myCamp.getCampDatesStart()), String.valueOf(myCamp.getCampDatesEnd()), String.valueOf(myCamp.getRegistrationDeadline()),
            myCamp.getFaculty(), String.valueOf(myCamp.getOpenToNTU()), myCamp.getLocation(), String.valueOf(myCamp.getTotalSlots()), String.valueOf(myCamp.getEmptySlots()),
            String.valueOf(myCamp.getCommitteeSlots()), myCamp.getInfo(), myCamp.getStaffIC(), String.valueOf(myCamp.getIsVisible()));
            writer.append(csvLine);
            writer.newLine();

            csvLine = String.join(",", "Name","UserID", "Points"); //header
            writer.append(csvLine);
            writer.newLine();

            ArrayList<CampCommittee> sortedCommitteeList = new ArrayList<CampCommittee>(myCamp.getCommitteeList()); //cone into a list
            Collections.sort(sortedCommitteeList, Comparator.comparing(CampCommittee::getName)); //sort the list


            for (int i = 0; i < sortedCommitteeList.size(); i++)
            {
                csvLine = String.join(",", sortedCommitteeList.get(i).getName(), sortedCommitteeList.get(i).getUserID(), String.valueOf(sortedCommitteeList.get(i).getPoints()));
                writer.append(csvLine);
                writer.newLine();
            }

            writer.close();
            System.out.println("Performance Report created successfully!");

        } catch (IOException e){
            e.printStackTrace();
        }  
    }

    public void generateEnquiryReport(Camp myCamp)
    {
        // Create report list CSV file for camp
            createCsvFile(myCamp.getCampID() + "_EnquiryReport", "userID,enquiryContent,replyContent,hasReply");

        try{

            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getApplicationFolderFilePath()+myCamp.getCampID() + "_EnquiryReport_list.csv", true));

            ArrayList<CampEnquiry> sortedEnquiryList = new ArrayList<CampEnquiry>(myCamp.getEnquiryList()); //cone into a list
            Collections.sort(sortedEnquiryList, Comparator.comparing(CampEnquiry::getUserID)); //sort the list

            for (int i =0; i<sortedEnquiryList.size();i++){
                CampEnquiry z = sortedEnquiryList.get(i);
                String csvLine = String.join(",", z.getUserID(),z.getEnquiryContent(),z.getReplyContent(),String.valueOf(z.getHasReply()));
                writer.append(csvLine);
                writer.newLine();
            }
            
            writer.close();
            System.out.println("Student Enquiry Report created successfully!");

        } catch (IOException e){
            e.printStackTrace();
        }  
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    public void addToEnquiryList(String campID, String userID, String enquiryContent, String replyContent, boolean hasReply){
        try {
            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getCampEnquiryListFilePath(), true));
            // Construct a CSV line by concatenating all the values with a delimiter (e.g., comma)
            String reply = String.valueOf(hasReply);
            String csvLine = String.join(",",campID, userID, enquiryContent, replyContent, reply);

            // Write the constructed CSV line to the file
            writer.append(csvLine);

            // Add a newline character after each line
            writer.newLine();
            writer.close();

            //System.out.println("Enquiry added to the file!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToSuggestionList(String campID, String userID, String suggestionContent, boolean isApproved){
       try {
            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getCampSuggestionListFilePath(), true));
            // Construct a CSV line by concatenating all the values with a delimiter (e.g., comma)
            String csvLine = String.join(",",campID, userID, suggestionContent, String.valueOf(isApproved));

            // Write the constructed CSV line to the file
            writer.append(csvLine);

            // Add a newline character after each line
            writer.newLine();
            writer.close();

            //System.out.println("Suggestion added to the file!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToAttendeeList(String name, String userID, String password,String faculty, String campID){//may change String campID to Camp camp = camp.geCampID()
       try {
            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getApplicationFolderFilePath()+campID+"_attendee_list.csv", true));
            // Construct a CSV line by concatenating all the values with a delimiter (e.g., comma)
            String csvLine = String.join(",",name , userID, password, faculty);
            
            // Write the constructed CSV line to the file
            writer.append(csvLine);

            // Add a newline character after each line
            writer.newLine();
            writer.close();

            //System.out.println("Attendee added to the file!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToCommitteeList(String name, String userID, String password,String faculty, String campID, int points){//may change String campID to Camp camp = camp.geCampID()
       try {
            // Use FileWriter in append mode by passing 'true' as the second argument
            BufferedWriter writer = new BufferedWriter(new FileWriter(applicationCsvFilePaths.getApplicationFolderFilePath()+campID+"_committee_list.csv", true));
            // Construct a CSV line by concatenating all the values with a delimiter (e.g., comma)
            String csvLine = String.join(",",name , userID, password, faculty, Integer.toString(points));

            // Write the constructed CSV line to the file
            writer.append(csvLine);

            // Add a newline character after each line
            writer.newLine();
            writer.close();

            //System.out.println("Committee added to the file!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
