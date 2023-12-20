package CamApp;
import java.io.*;
import java.util.ArrayList;

public class CsvReadMethods {
    
    public CsvReadMethods() {

    }

    public static void ConstructCampList(CampList campList ,String filepath) {

        String data[];
        String currentLine;
        ArrayList<String> campDetails = new ArrayList<String>();
        int colIndex;

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            //while not the end of csv file
            while( (currentLine = br.readLine()) != null) {

                //data is the current line
                data = currentLine.split(",");

                colIndex = 0;
                int dataLength = data.length;
                while ( dataLength != 0) {

                    campDetails.add(data[colIndex]);
                    colIndex++;
                    dataLength--;

                }

                boolean isOpenToNTU;
                boolean isVisible;
                
        
                if (campDetails.get(5).equalsIgnoreCase("TRUE")) {isOpenToNTU = true;} else {isOpenToNTU = false;}
                if (campDetails.get(12).equalsIgnoreCase("TRUE")) {isVisible = true;} else {isVisible = false;}

                
        
                //Construct thisCamp to add to campList, thisCamp is removed by garbage collector each iteration
                Camp thisCamp = new Camp(campDetails.get(0), campDetails.get(1), campDetails.get(2), campDetails.get(3), campDetails.get(4), isOpenToNTU,
                  campDetails.get(6), Integer.valueOf(campDetails.get(7)), Integer.valueOf(campDetails.get(8)), Integer.valueOf(campDetails.get(9)), campDetails.get(10),
                   campDetails.get(11), isVisible);

                campList.getCampList().add(thisCamp);
                //clear campDetails for next line
                campDetails.clear();
            }
            br.close();
        }
         catch (Exception exception)
        {
            System.out.println(exception);
        }
    }

    public static void ConstructCampCommitteeList(ArrayList<CampCommittee> thisCampCommitteeList,Camp thisCamp, String filepath) {

        String data[];
        String currentLine;
        ArrayList<String> committeeDetails = new ArrayList<String>();
        int colIndex;

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            //while not the end of csv file
            while( (currentLine = br.readLine()) != null) {

                //data is the current line
                data = currentLine.split(",");

                colIndex = 0;
                int dataLength = data.length;
                while ( dataLength != 0) {

                    committeeDetails.add(data[colIndex]);
                    colIndex++;
                    dataLength--;

                }

                CampCommittee thisCommittee = new CampCommittee(committeeDetails.get(0), committeeDetails.get(1), committeeDetails.get(2), committeeDetails.get(3), "student", Integer.valueOf(committeeDetails.get(4)));
                
                thisCampCommitteeList.add(thisCommittee);
                //clear campDetails for next line
                committeeDetails.clear();
            }
            br.close();
        }
         catch (Exception exception)
        {
            System.out.println(exception);
        }
    }

    public static void ConstructCampAttendeeList(ArrayList<CampAttendee> thisCampAttendeeList, Camp thisCamp, String filepath) {

        String data[];
        String currentLine;
        ArrayList<String> attendeeDetails = new ArrayList<String>();
        int colIndex;

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            //while not the end of csv file
            while( (currentLine = br.readLine()) != null) {

                //data is the current line
                data = currentLine.split(",");

                colIndex = 0;
                int dataLength = data.length;
                while ( dataLength != 0) {

                    attendeeDetails.add(data[colIndex]);
                    colIndex++;
                    dataLength--;

                }

                CampAttendee thisAttendee = new CampAttendee(attendeeDetails.get(0), attendeeDetails.get(1), attendeeDetails.get(2), attendeeDetails.get(3), "student");
                
                thisCampAttendeeList.add(thisAttendee);
                //clear campDetails for next line
                attendeeDetails.clear();
            }
            br.close();
        }
         catch (Exception exception)
        {
            System.out.println(exception);
        }
    }

    public static void ConstructThisCampEnquiryList(ArrayList<CampEnquiry> thisCampEnquiries , Camp thisCamp, String filepath) {

        String data[];
        String currentLine;
        ArrayList<String> enquiryDetails = new ArrayList<String>();
        int colIndex;

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            //while not the end of csv file
            while( (currentLine = br.readLine()) != null) {

                //data is the current line
                data = currentLine.split(",");

                if (data[0].equals(thisCamp.getCampID())) {

                    colIndex = 0;
                    int dataLength = data.length;
                    while ( dataLength != 0) {

                        enquiryDetails.add(data[colIndex]);
                        colIndex++;
                        dataLength--;

                    }

                    boolean enquiryHasReply;
                    if (enquiryDetails.get(4).equals("TRUE")) {enquiryHasReply = true;} else {enquiryHasReply = false;}


                    CampEnquiry thisCampEnquiry = new CampEnquiry(thisCamp, enquiryDetails.get(1), enquiryDetails.get(2), enquiryDetails.get(3), enquiryHasReply);

                    thisCampEnquiries.add(thisCampEnquiry);
                    //clear campDetails for next line
                    enquiryDetails.clear();
                }

            }
            br.close();
        }
         catch (Exception exception)
        {
            System.out.println(exception);
        }

    }

    public static void ConstructThisCampSuggestionList(ArrayList<CampSuggestion> thisCampSuggestions , Camp thisCamp, String filepath) {

        String data[];
        String currentLine;
        ArrayList<String> suggestionDetails = new ArrayList<String>();
        int colIndex;

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            br.readLine();
            //while not the end of csv file
            while( (currentLine = br.readLine()) != null) {

                //data is the current line
                data = currentLine.split(",");

                if (data[0].equals(thisCamp.getCampID())) {

                    colIndex = 0;
                    int dataLength = data.length;
                    while ( dataLength != 0) {

                        suggestionDetails.add(data[colIndex]);
                        colIndex++;
                        dataLength--;

                    }

                    boolean isApproved;
                    if (suggestionDetails.get(3).equals("TRUE")) {isApproved = true;} else {isApproved = false;};

                    CampSuggestion thisCampSuggestion = new CampSuggestion(thisCamp, suggestionDetails.get(1), suggestionDetails.get(2), isApproved);
                    
                    thisCampSuggestions.add(thisCampSuggestion);
                    //clear campDetails for next line
                    suggestionDetails.clear();
                }

            }
            br.close();
        }
         catch (Exception exception)
        {
            System.out.println(exception);
        }

    }


    public static Student ConstructStudent(String studentUserID, String filepath) {

        String data[];
        String currentLine;
        ArrayList<String> studentDetails = new ArrayList<String>();
        int colIndex;

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            //while not the end of csv file
            while( (currentLine = br.readLine()) != null) {

                //data is the current line
                data = currentLine.split(",");

                //if data column index is the identified student, add row of data to studentDetails
                if (data[1].equals(studentUserID)) {

                    colIndex = 0;
                    int dataLength = data.length;
                    while ( dataLength != 0) {

                        studentDetails.add(data[colIndex]);
                        colIndex++;
                        dataLength--;

                    }

                }

            }
            br.close();
        }
         catch (Exception exception)
        {
            System.out.println(exception);
            return null;
        }

        //Construct Student Object
        Student thisStudent = new Student(studentDetails.get(0), studentDetails.get(1), studentDetails.get(2), studentDetails.get(3), "student");


        //return student object
        return thisStudent;
    }

    public static CampCommittee ConstructThisStudentCampCommittee(Student thisStudent, String filepath) {

        String data[];
        String currentLine;
        ArrayList<String> committeeDetails = new ArrayList<String>();
        int colIndex;
        String studentUserID = thisStudent.getUserID();

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            //while not the end of csv file
            while( (currentLine = br.readLine()) != null) {

                //data is the current line
                data = currentLine.split(",");

                //if data column index is the identified student, add row of data to studentDetails
                if (data[1].equals(studentUserID)) {

                    colIndex = 0;
                    int dataLength = data.length;
                    while ( dataLength != 0) {

                        committeeDetails.add(data[colIndex]);
                        colIndex++;
                        dataLength--;

                    }

                }

            }
            br.close();
        }
         catch (Exception exception)
        {
            System.out.println(exception);
            return null;
        }

        //Construct CampCommittee object
        CampCommittee thisCampCommittee = new CampCommittee(committeeDetails.get(0), committeeDetails.get(1), committeeDetails.get(2), committeeDetails.get(3), "student", Integer.valueOf(committeeDetails.get(4)));

        //return CampCommittee object
        return thisCampCommittee;

    }

    public static Camp ConstructThisStudentIsCommitteeCamp(Student thisStudent, CampList campList) {
        String studentUserID = thisStudent.getUserID();
        Camp thisStudentsIsCampCommittee = null;
        for (int i=0; i<campList.getCampList().size(); i++) {
            for (int j=0; j<campList.getCampList().get(i).getCommitteeList().size(); j++) {
                if (campList.getCampList().get(i).getCommitteeList().get(j).getUserID().equals(studentUserID)) {
                    thisStudentsIsCampCommittee = campList.getCampList().get(i);
                }
            }
        }

        return thisStudentsIsCampCommittee;
    }

    public static ArrayList<Camp> ConstructThisStudentIsAttendeeCamps(Student thisStudent, CampList campList) {
        String studentUserID = thisStudent.getUserID();
        ArrayList<Camp> thisStudentsisCampAttendeeCamps = new ArrayList<Camp>();
        for (int i=0; i<campList.getCampList().size(); i++) {
            for (int j=0; j<campList.getCampList().get(i).getAttendeeList().size(); j++) {
                if (campList.getCampList().get(i).getAttendeeList().get(j).getUserID().equals(studentUserID)) {
                    thisStudentsisCampAttendeeCamps.add(campList.getCampList().get(i));
                }
            }
        }

        return thisStudentsisCampAttendeeCamps;
    }


    public static Staff constructStaff(String staffUserID, String filepath) {

        String data[];
        String currentLine;
        ArrayList<String> staffDetails = new ArrayList<String>();
        int colIndex;

        try
        {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            //while not the end of csv file
            while( (currentLine = br.readLine()) != null) {

                //data is the current line
                data = currentLine.split(",");

                //if data column index is the identified staff, add row of data to staffDetails
                if (data[1].equals(staffUserID)) {

                    colIndex = 0;
                    int dataLength = data.length;
                    while ( dataLength != 0) {

                        staffDetails.add(data[colIndex]);
                        colIndex++;
                        dataLength--;

                    }

                }

            }
            br.close();
        }
         catch (Exception exception)
        {
            System.out.println(exception);
            return null;
        }

        //Construct Staff Object
        Staff thisStaff = new Staff(staffDetails.get(0), staffDetails.get(1), staffDetails.get(2), staffDetails.get(3), "staff");

        //return Staff object
        return thisStaff;
    }

}


