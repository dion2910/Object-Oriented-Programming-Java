package CamApp;
public interface IRegister {

	/**
	 * Allow the Student to register for the Camp.
	 * @param camp Stores the name of the Camp that they want to register
	 * @param asCommittee Determines if the student want to register as a committee
	 * @param student Student that is registering
	 */
	void register(Camp camp, boolean asCommittee,Student student);

}