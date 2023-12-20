package CamApp;

import java.util.ArrayList;

public interface IViewRegistered {

	/**
	 * This interface method will show all the Camps registered by the Student.
	 * @param student To get list of Camps registered by the student
	 */
	ArrayList<Camp> viewRegistered(Student student);

}