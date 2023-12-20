package CamApp;
public interface IDeleteCamp {

	/** 
	 * Allows staff to delete the camp he/she created.
	 * 
	 * @param myCamp Staff's Camp
	 * @param campList Stores all the created camps
	 */
	boolean deleteMyCamp(Camp myCamp, CampList campList);

}