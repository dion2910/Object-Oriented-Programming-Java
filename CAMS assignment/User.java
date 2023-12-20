package CamApp;
public class User {

	/**
	Contains the user details.
	@param name Name of user
	@param userID UserID
	@param password Password of user
	@param faculty Faculty of user
	@param userType UserType of user
	 
	@return User Object
	*/

	private String name;
	private String userID;
	private String password;
	private String faculty;
	private String userType;


	public User(String name, String userID, String password, String faculty, String userType){
		this.name = name;
		this.userID = userID;
		this.password = password;
		this.faculty = faculty;
		this.userType = userType;
	}

	/**
	Description of Logout Method:
	Allows user to log out and terminate the system.
	*/
	public void logOut() {
	
		System.out.println("Logged out successfully");
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getUserID(){
		return this.userID;
	}

	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPassword(String pw) {
		
		this.password = pw;
	}

	public String getFaculty(){
		return this.faculty;
	}

	public void setFaculty(String faculty){
		this.faculty = faculty;
	}

	public String getUserType(){
		return this.userType;
	}

	public void setUserType(String userType){
		this.userType = userType;
	}

}