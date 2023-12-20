package CamApp;
import java.util.*;

public class CampAttendee extends Student {
	/**
	@param name Student's name
	@param userID Student's user
	@param password Student's login password
	@param faculty Student's Faculty
	@param userType To identify user as Student
	
	*/
	//Constructor
	public CampAttendee(String name, String userID, String password,String faculty, String userType){
		super(name,userID,password,faculty,userType);
	}

	/**
	@param name Student's name
	@param userID Student's user
	@param password Student's login password
	@param faculty Student's Faculty
	@param userType To identify user as Student
	@param isAttendeeCamps Stores the camp that the student is registered as attendee 

	*/
	public CampAttendee(String name, String userID, String password,String faculty, String userType, ArrayList<Camp> isAttendeeCamps){
		super(name,userID,password,faculty,userType, isAttendeeCamps);
	}

	//Method
	/**
	 * Withdraws the Attendee from the camp.
	 */
	public void withdrawFromCamp() {
		AttendeeCampOperationController operationController = new AttendeeCampOperationController();
		operationController.withdraw(this);
	}

}