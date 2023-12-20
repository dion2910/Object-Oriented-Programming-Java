package CamApp;
public interface ISubmit {

	/** 
	 * Submit enquiry/suggestion to a particular Camp by the user.
	 * 
	 * @param camp Target Camp
	 * @param userID To check if the enquiry/suggestion is created by the user
	 */
	void submit(Camp camp, String userID);

}