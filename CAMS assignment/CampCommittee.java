package CamApp;
public class CampCommittee extends Student {

	//Attributes
	private int points;
	CsvEditMethods editCsv = new CsvEditMethods();
	CsvFilePaths pathing = new CsvFilePaths();


	/**
	Stores details and opeations of the committee.
	@param name Student's name
	@param userID Student's user
	@param password Student's login password
	@param faculty Student's Faculty
	@param userType To identify user as Student
	@param points Store the points of this Camp Committee object
	
	*/
	//Constructors
	public CampCommittee(String name, String userID, String password,String faculty, String userType, int points){
		super(name,userID,password,faculty, userType);
		this.points=points;
	}

	/**
	Stores details and opeations of the committee.
	@param name Student's name
	@param userID Student's user
	@param password Student's login password
	@param faculty Student's Faculty
	@param userType To identify user as Student
	*/

	public CampCommittee(String name, String userID, String password,String faculty, String userType){
		super(name,userID,password,faculty, userType);
		this.points=0;
	}
	/**
	Stores details and opeations of the committee.
	@param name Student's name
	@param userID Student's user
	@param password Student's login password
	@param faculty Student's Faculty
	@param userType To identify user as Student
	@param isCommitteeCamp Stores the camp that the student is registered as committee
	*/

	public CampCommittee(String name, String userID, String password,String faculty, String userType, Camp isCommitteeCamp){
		super(name,userID,password,faculty, userType, isCommitteeCamp);
		this.points=0;
	}

	/**
	Stores details and opeations of the committee.
	@param name Student's name
	@param userID Student's user
	@param password Student's login password
	@param faculty Student's Faculty
	@param userType To identify user as Student
	@param points Store the points of this Camp Committee object
	@param isCommitteeCamp Stores the camp that the student is registered as committee
	*/
	public CampCommittee(String name, String userID, String password,String faculty, String userType, Camp isCommitteeCamp, int points){
		super(name,userID,password,faculty, userType, isCommitteeCamp);
		this.points=points;
	}

	//Methods
	/** 
	 * Retrieve which camp the committee is in
	 */
	public Camp getIsCommitteeCamp(Student student) {
		return student.getIsCommitteeCamp();
	}

	/** 
	 * View Camp details as committee
	 */
	public void viewCampDetails(){
		CommitteeCampOperationController campOperationController = new CommitteeCampOperationController();
		campOperationController.view(this.getIsCommitteeCamp(), this.getUserID());
	}
	/** 
	 * View Camp enquiries as committee
	 */
	public void viewEnquiry() {
		ManagerEnquiryController enquiryController = new ManagerEnquiryController();
		enquiryController.view(this.getIsCommitteeCamp());
	}
	/** 
	 * Reply Camp enquiries as committee
	 */
	public void replyEnquiry() {
		ManagerEnquiryController enquiryController = new ManagerEnquiryController();
		//enquiryController.reply(this.getIsCommitteeCamp());
		if(enquiryController.reply(this.getIsCommitteeCamp())){
			addPoints();
			editCsv.addPoints(pathing.getApplicationFolderFilePath()+this.getIsCommitteeCamp().getCampID()+"_committee_list.csv", "Points", String.valueOf(points), this.getUserID());
		}
	}
	/** 
	 * View committee's submitted suggestion(s).
	 */
	public void viewSuggestion() {
		CommitteeSuggestionController suggestionController = new CommitteeSuggestionController();
		suggestionController.view(this.getIsCommitteeCamp(), this.getUserID());
	}
	/** 
	 * View submit committee's suggestion.
	 */
	public void submitMySuggestion() {
		CommitteeSuggestionController suggestionController = new CommitteeSuggestionController();
		suggestionController.submit(this.getIsCommitteeCamp(), this.getUserID());
		addPoints();
		editCsv.addPoints(pathing.getApplicationFolderFilePath()+this.getIsCommitteeCamp().getCampID()+"_committee_list.csv", "Points", String.valueOf(points), this.getUserID());
	}
	/** 
	 * Edit committee's suggestion(s).
	 */
	public void editMySuggestions() {
		CommitteeSuggestionController suggestionController = new CommitteeSuggestionController();
		suggestionController.edit(this.getIsCommitteeCamp(),this.getUserID());
	}
	/** 
	 * Delete committee's suggestion(s).
	 */

	public void deleteMySuggestions() {
		CommitteeSuggestionController suggestionController = new CommitteeSuggestionController();
		if(suggestionController.delete(this.getIsCommitteeCamp(),this.getUserID())){
			//this.points--;
			editCsv.addPoints(pathing.getApplicationFolderFilePath()+this.getIsCommitteeCamp().getCampID()+"_committee_list.csv", "Points", String.valueOf(points), this.getUserID());
		}		
	}
	/** 
	 * Generate report as committee.
	 */
	public void generateReport() {
		CommitteeReportController reportController = new CommitteeReportController();
		reportController.generate(this.getIsCommitteeCamp());
	}
	/** 
	 * Generate enquiry report as committee.
	 */
	public void generateEnquiryReport() {
		CommitteeReportController c = new CommitteeReportController();
		c.generateEnquiryReport(this.getIsCommitteeCamp());
	}
	
	//Getter & Setter Methods
	public int getPoints() {
		return this.points;
	}
	
	public void setPoints(int points){
		this.points = points;
	}

	public void addPoints(){
		this.points++;
	}
}