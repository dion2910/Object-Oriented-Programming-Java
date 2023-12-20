package CamApp;


public class CsvFilePaths {

    private String applicationFolderFilePath = "..\\SC2002 Project Application Folder\\csv files\\";

    private String studentListFilePath = applicationFolderFilePath + "student_list.csv";
    private String staffListFilePath = applicationFolderFilePath + "staff_list.csv";
    private String campListFilePath = applicationFolderFilePath + "camp_list.csv";
    private String campEnquiryListFilePath = applicationFolderFilePath + "camp_enquiry_list.csv";
    private String campSuggestionListFilePath = applicationFolderFilePath + "camp_suggestion_list.csv";
    
    public CsvFilePaths() {

    }

    public String getApplicationFolderFilePath() {
        return this.applicationFolderFilePath;
    }

    public String getStudentListFilePath() {
        return this.studentListFilePath;
    }

    public String getStaffListFilePath() {
        return this.staffListFilePath;
    }

    public String getCampListFilePath() {
        return this.campListFilePath;
    }

    public String getCampEnquiryListFilePath() {
        return this.campEnquiryListFilePath;
    }

    public String getCampSuggestionListFilePath() {
        return this.campSuggestionListFilePath;
    }


}
