package CamApp;
public interface IView {

	/** 
	 * View the desired camp details.
	 * @param userID View the camp of the student
	 * @param camp Camp object that consist
	 */
	void view(Camp camp, String userID);

}