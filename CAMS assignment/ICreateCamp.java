package CamApp;

public interface ICreateCamp {

	/** 
	 * Create a camp.
	 * @param campList Stores the list of created camps
	 * @param campID Camp's name
	 * @param campDateStart Camp's start date in (YYYY-MM-DD)
	 * @param campDateEnd Camp's end date in (YYYY-MM-DD)
	 * @param registrationDeadLine Camp's registrationdeadline in (YYYY-MM-DD)
	 * @param faculty Which faculty this Camp is open to
	 * @param openToNTU Determines if this Camp will be opened to whole of NTU
	 * @param location Location of where this Camp will be held at
	 * @param totalSlots Stores total slots of this Camp
	 * @param emptySlots Stores total empty slots of this Camp
	 * @param committeeSlots Stores total Committe slots of this Camp
	 * @param info Stores description of the Camp
	 * @param staffIC Stores which Staff creats this camp
	 * @param isVisible Store the visibility of the camp
	 */
	
	Camp create(CampList campList, String campID, String campDateStart, String campDateEnd, String registrationDeadLine, String faculty, boolean openToNTU,
	 String location, int totalSlots, int emptySlots, int committeeSlots, String info, String staffIC, boolean isVisible);

}