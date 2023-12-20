package CamApp;

public class CampSuggestion {
	
	
	//Attributes
	private String campID;
	private String userID;
	private String suggestionContent;
	private boolean isApproved;

	/**
	Stores the suggestions for the camp.
	@param camp Camp object to get camp's name
	@param userID Student's Name
	@param suggestionContent Suggestion Content

	*/
	//used when committee creates suggestion
	public CampSuggestion(Camp camp, String userID, String suggestionContent){
		this.campID=camp.getCampID();
		this.userID=userID;
		this.suggestionContent=suggestionContent;
		this.isApproved=false;
	}
	/**
	Stores the suggestions for the camp.
	@param camp Camp object to get camp's name
	@param userID Student's Name
	@param suggestionContent Suggestion Content
	@param isApproved To check if the suggestion created has been approved
	*/
	// used when reading from csv
	public CampSuggestion(Camp camp, String userID, String suggestionContent, boolean isApproved){
	this.campID=camp.getCampID();
	this.userID=userID;
	this.suggestionContent=suggestionContent;
	this.isApproved=isApproved;
}
	//Getter and Setter methods
	public String getCampID(){
		return this.campID;
	}

	public void setCampID(String a){
		this.campID = a;	
	}

	public String getUserID(){
		return this.userID;
	}

	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getSuggestionContent() {
		return this.suggestionContent;
	}
	
	public void setSuggestionContent(String content) {
		this.suggestionContent=content;
	}
	
	public boolean getIsApproved(){
		return this.isApproved;
	}

	public void setIsApproved(boolean isApproved){
		this.isApproved = isApproved;
	}
}