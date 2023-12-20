package CamApp;
public interface IEdit {

	/** 
	 * Edit enquiry/suggestion of a particular Camp submitted by the user.
	 * 
	 * @param camp Target Camp
	 * @param userID To check if the enquiry/suggestion is created by the user
	 */
	void edit(Camp camp, String userID);

}