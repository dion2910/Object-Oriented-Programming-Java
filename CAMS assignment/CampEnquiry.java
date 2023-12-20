package CamApp;
public class CampEnquiry {
	
	
	private String campID;
	private String userID;
	private String enquiryContent;
	private String replyContent=null;
	private boolean hasReply=false;

	/**
	Stores the enquiries of the camp.
	@param camp Camp object to retrieve camp name
	@param userID Student's Name
	@param enquiryContent Enquiry Content
	*/
	public CampEnquiry(Camp camp, String userID, String enquiryContent){
		this.campID=camp.getCampID();
		this.userID=userID;
		this.enquiryContent=enquiryContent;
	}
	/**
	Stores the enquiries of the camp.
	@param camp Camp object to retrieve camp name
	@param userID Student's Name
	@param enquiryContent Enquiry Content
	@param replyContent Reply Content
	@param hasReply To check if the enquiry created has been replied

	*/
	public CampEnquiry(Camp camp, String userID, String enquiryContent, String replyContent, boolean hasReply){
		this.campID=camp.getCampID();
		this.userID=userID;
		this.enquiryContent=enquiryContent;
		this.replyContent=replyContent;
		this.hasReply=hasReply;
	}


	//Getter and Setter methods
	public String getCampID(){
		return this.campID;
	}

	public void setCampID(String campID){
		this.campID = campID;
	}

	public String getUserID(){
		return this.userID;
	}

	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getEnquiryContent(){
		return this.enquiryContent;
	}

	public void setEnquiryContent(String content){
		this.enquiryContent=content;
	}

	public String getReplyContent(){
		return this.replyContent;
	}

	public void setReplyContent(String replyContent){
		this.replyContent=replyContent;
	}

	public boolean getHasReply(){
		return this.hasReply;
	}

	public void setHasReply(boolean hasReply){
		this.hasReply = hasReply;
	}

}