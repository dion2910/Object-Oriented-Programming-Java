package CamApp;
public interface IDelete {

	/** 
	 * Delete enquiry/suggestion submitted by the user from the particular Camp.
	 * 
	 * @param camp Target camp
	 * @param userID To check if the enquiry/suggestion is created by the user
	 */
	boolean delete(Camp camp, String userID);

}