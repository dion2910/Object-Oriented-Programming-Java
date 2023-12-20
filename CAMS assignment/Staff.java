package CamApp;


public class Staff extends User {

	//Attributes
	private Camp myCamp;

	/**
	* Contains details and operations of Staff.
	* @param name Name of user
	* @param userID UserID
	* @param password Password of user
	* @param faculty Faculty of user
	* @param userType UserType of user
	*/

	public Staff(String name, String userID, String password, String faculty, String userType){
		
		super(name, userID, password, faculty, userType);
		this.myCamp = null;
		
	}
	
	/**
	* Create Camp using StaffCampOperationController
	* @param campID Camp's name
	* @param campDateStart Camp's start date in (YYYY-MM-DD)
	* @param campDateEnd Camp's end date in (YYYY-MM-DD)
	* @param registrationDeadline Camp's registrationdeadline in (YYYY-MM-DD)
	* @param faculty Which faculty this Camp is open to
	* @param openToNTU Determines if this Camp will be opened to whole of NTU
	* @param location Location of where this Camp will be held at
	* @param totalSlots Stores total slots of this Camp
	* @param emptySlots Stores total empty slots of this Camp
	* @param committeeSlots Stores total Committe slots of this Camp
	* @param info Stores description of the Camp
	* @param staffIC Stores which Staff creats this camp
	* @param isVisible Determines if this Camp will be visible to students
	*/

	public void createCamp(CampList campList, String campID, String campDateStart, String campDateEnd, String registrationDeadline, 
	String faculty, boolean openToNTU, String location, int totalSlots, int emptySlots, int committeeSlots, String info, String staffIC, boolean isVisible) {
		
		StaffCampOperationController c = new StaffCampOperationController();
		this.myCamp = c.create(campList, campID, campDateStart, campDateEnd, registrationDeadline, 
		faculty, openToNTU, location, totalSlots, emptySlots, committeeSlots, info, staffIC, isVisible);
	}
	/**
	* Edit Camp using StaffCampOperationController
	*/
	public void editCamp() {
		
		StaffCampOperationController c = new StaffCampOperationController();
		c.edit(this.myCamp);
	}
	
	/**
	* Delete Camp using StaffCampOperationController
	* @param campList Array list of camp(s) created
	*/
	public void deleteCamp(CampList campList) {
		
		StaffCampOperationController c = new StaffCampOperationController();
		if(c.deleteMyCamp(this.myCamp, campList))
			this.setMyCamp(null);
		
	}
	
	/**
	* Toggle visibility Camp using StaffCampOperationController
	*/
	public void toggleCampVisibility() {
		
		StaffCampOperationController c = new StaffCampOperationController();
		c.toggle(this.myCamp);
	}

	/**
	* View all camp using StaffCampOperationController
	*/
	public void viewAllCamp(CampList campList) {

		StaffCampOperationController c = new StaffCampOperationController();
		c.viewCamps(campList);
	}
	
	/**
	* View the camp that the staff created using StaffCampOperationController
	*/
	public void viewMyCreatedCamp() {

		StaffCampOperationController c = new StaffCampOperationController();
		c.view(this.myCamp);
	}
	
	/**
	* View enquiry StaffCampOperationController
	*/
	public void viewEnquiry() {

		ManagerEnquiryController c = new ManagerEnquiryController();
		c.view(this.myCamp);
	}
	
	/**
	* Reply enquiry using StaffCampOperationController
	*/
	public void replyEnquiry() {

		ManagerEnquiryController c = new ManagerEnquiryController();
		c.reply(this.myCamp);
	}
	
	/**
	* View suggestions using StaffCampOperationController
	*/
	public void viewSuggestion() {

		StaffSuggestionController c = new StaffSuggestionController();
		c.view(this.myCamp);
	}
	
	/**
	* Approve suggestions using StaffCampOperationController
	*/
	public void approveSuggestions() {
	
		StaffSuggestionController c = new StaffSuggestionController();
		c.approve(this.myCamp);
	}
	
	/**
	* Generate report using StaffCampOperationController
	*/
	public void generateReport() {
		StaffReportController c = new StaffReportController();
		c.generate(this.myCamp);
	}
	
	/**
	* Geenrate performance report using StaffCampOperationController
	*/
	public void generatePerfReport() {
		StaffReportController c = new StaffReportController();
		c.generatePerfReport(this.myCamp);
	}

	/**
	* Generate enquiry report using StaffCampOperationController
	*/
	public void generateEnquiryReport() {
		StaffReportController c = new StaffReportController();
		c.generateEnquiryReport(this.myCamp);
	}
	
	//Getter and Setter
	public Camp getMyCamp(){
		return this.myCamp;
	}

	public void setMyCamp(Camp myCamp){
		this.myCamp = myCamp;
	}
}