package CamApp;
import java.util.ArrayList;
public interface IViewVisibleCamps {

	/**
	 * Displays all the camps visible to the student.
	 * 
	 * @param campList Stores all the camps created
	 * @param student To check if the camps are visible to the particular student
	 */
	ArrayList<Camp> viewVisibleCamps(CampList campList, Student student);

}