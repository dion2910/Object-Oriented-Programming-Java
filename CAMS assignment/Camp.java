package CamApp;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;


public class Camp {
	

	//Attributes
	public static final int MAX_COMMITTEE_SLOT=10;
	private String campID;
	private LocalDate campDateStart;
	private LocalDate campDateEnd;
	private LocalDate registrationDeadline;
	private String faculty;
	private boolean openToNTU;
	private String location;
	private int totalSlots;
	private int emptySlots;
	private int committeeSlots;
	private String info;
	private String staffIC;
	private ArrayList<CampCommittee> committeeList = new ArrayList<CampCommittee>();
	private ArrayList<CampAttendee> attendeeList = new ArrayList<CampAttendee>();
	private boolean isVisible;
	private ArrayList<CampEnquiry> enquiryList = new ArrayList<CampEnquiry>();
	private ArrayList<CampSuggestion> suggestionList = new ArrayList<CampSuggestion>();

	/**
	Stores the details for the camp.
	@param campID Camp's name
	@param campDateStart Camp's start date in (YYYY-MM-DD)
	@param campDateEnd Camp's end date in (YYYY-MM-DD)
	@param registrationDeadline Camp's registrationdeadline in (YYYY-MM-DD)
	@param faculty Which faculty this Camp is open to
	@param openToNTU Determines if this Camp will be opened to whole of NTU
	@param location Location of where this Camp will be held at
	@param totalSlots Stores total slots of this Camp
	@param emptySlots Stores total empty slots of this Camp
	@param committeeSlots Stores total Committe slots of this Camp
	@param info Stores description of the Camp
	@param staffIC Stores which Staff creats this camp
	@param isVisible Determines if this Camp will be visible to students
	*/
	//Constructors
	public Camp(String campID, String campDateStart, String campDateEnd, String registrationDeadline, String faculty, boolean openToNTU, 
	String location, int totalSlots, int emptySlots, int committeeSlots, 
	String info,String staffIC, boolean isVisible)
	{
		this.campID = campID;
		this.campDateStart = LocalDate.parse(campDateStart, DateTimeFormatter.ISO_DATE);
		this.campDateEnd = LocalDate.parse(campDateEnd, DateTimeFormatter.ISO_DATE);
		this.registrationDeadline = LocalDate.parse(registrationDeadline, DateTimeFormatter.ISO_DATE);
		this.faculty = faculty;
		this.openToNTU = openToNTU;
		this.location = location;
		this.totalSlots = totalSlots;
		this.emptySlots = emptySlots;
		this.committeeSlots = committeeSlots;
		this.info = info;
		this.staffIC = staffIC; 
		this.isVisible = isVisible;
			
	}
	/**
	Prints out the information of the Camp
	*/
	public void showCampDetails(){
		System.out.println("Camp Name: " + this.campID);
		System.out.println("Start Date(YYYY-MM-DD): " + this.campDateStart);
		System.out.println("End Date(YYYY-MM-DD): " + this.campDateEnd);
		System.out.println("Registration Deadline(YYYY-MM-DD): " + this.registrationDeadline);
		System.out.println("Faculty: " + this.faculty);
		System.out.println("Open to NTU: " + Boolean.toString(this.openToNTU));
		System.out.println("Location: " + this.location);
		System.out.println("Total Slots: " + this.totalSlots);
		// System.out.println("Total Empty slots: " + this.getEmptySlots());
		System.out.println("Empty Committee Slots: " + this.getCommitteeSlots());
		System.out.println("Empty Attendee Slots: " + (this.getEmptySlots()-this.getCommitteeSlots()));
		System.out.println("Camp Description: " + this.info);
		System.out.println("Staff-In-Charge: " + this.staffIC);
	}



	//Getter and Setter Methods
	public String getCampID(){
		return this.campID;
	}

	public void setCampID(String a){
		this.campID = a;	
	}

	public LocalDate getCampDatesStart(){
		return this.campDateStart;
	}

	public void setCampDateStart(String campDates)
	{
		this.campDateStart = LocalDate.parse(campDates, DateTimeFormatter.ISO_DATE);
	}

	public LocalDate getCampDatesEnd(){
		return this.campDateEnd;
	}

	public void setCampDateEnd(String campDates)
	{
		this.campDateEnd = LocalDate.parse(campDates, DateTimeFormatter.ISO_DATE);
	}

	public LocalDate getRegistrationDeadline()
	{
		return this.registrationDeadline;
	}

	public void setRegistrationDeadline(String registrationDeadline)
	{
		this.registrationDeadline = LocalDate.parse(registrationDeadline, DateTimeFormatter.ISO_DATE);
	}

	public String getFaculty(){
		return this.faculty;
	}

	public void setFaculty(String a){
		this.faculty = a;
	}

	public boolean getOpenToNTU(){
		return this.openToNTU;
	}

	public void setOpenToNTU(boolean a){
		this.openToNTU = a;
	}

	public String getLocation(){
		return this.location;
	}

	public void setLocation(String a){
		this.location = a;
	}

	public int getTotalSlots(){
		return this.totalSlots;
	}

	public void setTotalSlots(int a){
		this.totalSlots = a;
	}

	public int getEmptySlots(){
		int attendeeListSize=0;
		if(this.attendeeList != null){
			attendeeListSize = attendeeList.size();}
		int committeeListSize=0;
		if(this.committeeList != null) {
			committeeListSize = committeeList.size();
		}
		this.emptySlots = totalSlots-committeeListSize-attendeeListSize;
		return this.emptySlots;
	}

	public void setEmptySlots(int a){
		this.emptySlots = a;
	}
	
	public int getCommitteeSlots(){
		int committeeListSize=0;
		if(this.committeeList != null) {
			committeeListSize = committeeList.size();
		}
		return this.committeeSlots-committeeListSize;
	}

	public void setCommitteeSlots(int committeeSlots){
		if(committeeSlots<=MAX_COMMITTEE_SLOT){
			this.committeeSlots=committeeSlots;
		}
		else{
			System.out.println("Committee slots cannot exceed 10.");
		}
	}

	public String getInfo(){
		return this.info;
	}

	public void setInfo(String a){
		this.info = a;
	}

	public String getStaffIC(){
		return this.staffIC;
	}

	public void setStaffIC(String a){
		this.staffIC = a;
	}

	public ArrayList<CampCommittee> getCommitteeList(){
		return this.committeeList;
	}

	public void setCommitteeList(ArrayList <CampCommittee> committeeList){
		this.committeeList = committeeList;
	}

	public void addToCommitteeList(CampCommittee student){
		this.committeeList.add(student);
	}

	public ArrayList<CampAttendee> getAttendeeList() {
		return this.attendeeList;
	}

	public void setAttendeeList(ArrayList <CampAttendee> attendeeList) {
		this.attendeeList = attendeeList;
	}

	public void addToAttendeeList(CampAttendee student) {
		this.attendeeList.add(student);
	}

	public boolean getIsVisible(){
		return this.isVisible;
	}

	public void setIsVisible(boolean a){
		this.isVisible = a;
	}

	public ArrayList<CampEnquiry> getEnquiryList(){
		return this.enquiryList;
	}

	public void setEnquiryList(ArrayList <CampEnquiry> enquiryList){
		this.enquiryList = enquiryList;
	}

	public void addToEnquiryList(CampEnquiry campEnquiry){
		this.enquiryList.add(campEnquiry);
	}

	public ArrayList<CampSuggestion> getSuggestionList(){
		return this.suggestionList;
	}

	public void setSuggestionList(ArrayList <CampSuggestion> suggestionList){
		this.suggestionList = suggestionList;
	} 

	public void addToSuggestionList(CampSuggestion suggestion){
		this.suggestionList.add(suggestion);
	}
	
}