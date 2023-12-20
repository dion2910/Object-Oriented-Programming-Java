package CamApp;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class StaffCampOperationController implements IViewUnrestricted, IViewCamps, IEditMyCamp, IToggle, IDeleteCamp, ICreateCamp {
	/**
	* Create Camp 
	* @param campList Array list of camp(s)
	* @param campID Camp's name 
	* @param campDateStart Camp's start date in (YYYY-MM-DD)
	* @param campDateEnd Camp's end date in (YYYY-MM-DD)
	* @param registrationDeadline Camp's registrationdeadline in (YYYY-MM-DD)
	* @param faculty Which faculty this Camp is open to
	* @param openToNTU Determines if this Camp will be opened to whole of NTU
	* @param location Location of where this Camp will be held at
	* @param totalSlots Stores total slots of this Camp
	* @param emptySlots Stores total empty slots of this Camp
	* @param committeeSlots Stores total Committe slots of this Camp
	* @param info Stores description of the Camp
	* @param staffIC Stores which Staff creats this camp
	* @param isVisible Determines if this Camp will be visible to students
	* @return Camp object
	*/

	public Camp create(CampList campList, String campID, String campDateStart, String campDateEnd, String registrationDeadline, 
	String faculty, boolean openToNTU, String location, int totalSlots, int emptySlots, int committeeSlots, String info, String staffIC, 
	boolean isVisible) 
	{
		Camp myCamp = new Camp(campID, campDateStart, campDateEnd, registrationDeadline, 
		faculty, openToNTU, location, totalSlots, emptySlots, committeeSlots, info, staffIC, isVisible); 
		campList.setCampList(myCamp);
		CsvWriteMethods a = new CsvWriteMethods();
		a.staffCreateCamp(campID, campDateStart, campDateEnd, registrationDeadline, faculty, openToNTU, location,
		totalSlots, emptySlots, committeeSlots, info, staffIC, isVisible);
		return myCamp;
	}

	/**
	* Edit Camp using StaffCampOperationController
	* @param myCamp Camp to edit
	*/
	public void edit(Camp myCamp) 
	{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate currentDate = LocalDate.now();

		if (myCamp == null)
		{
			System.out.println("No camp created yet!");
			return;
		}

		CsvEditMethods a = new CsvEditMethods();
		CsvFilePaths b = new CsvFilePaths();

		Scanner sc = new Scanner(System.in);
		String update = "a"; //new content to update
		String targetUpdate = "a"; //column index header


		if (myCamp.getAttendeeList().size() == 0 && myCamp.getCommitteeList().size() == 0) //no participants then can edit
		{
			int choice;
			do {
				
				System.out.println("Edit:");
				System.out.println("1. Start Camp Date\n2. End Camp Date\n3. Registration Deadline \n4. Faculty \n5. Open to NTU \n6. Location \n7. Total Slots\n8. committeeSlots\n9. info\n10. Visibility \n11. EXIT");

				do{
					if (sc.hasNextInt()){
						choice = sc.nextInt();
						sc.nextLine();
						if(choice >=1 && choice <= 11){
							break;
						}
						else{
							System.out.println("Please input valid integer");
						}
					}
					else{
						System.out.println("Please input valid integer");
						sc.nextLine();
					}
				}while(true);

				switch (choice)
				{

					case 1:
						String campDateStart;
						LocalDate campDateStartChecker;
						do {
							do{
								try {
									System.out.println("Edit Camp Start Date(YYYY-MM-DD): ");
									campDateStart = sc.nextLine();
									campDateStartChecker = LocalDate.parse(campDateStart, dateFormatter);
									break;//format is ok
								} catch (DateTimeParseException e) {
									System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
								}
							} while (true);

							if(!campDateStartChecker.isBefore(myCamp.getCampDatesEnd()))
								System.out.println("Camp Start Date must be before Camp End Date");
							if (!currentDate.isBefore(campDateStartChecker))
								System.out.println("Camp Start Date must be after Current Date(today)");
							if (!myCamp.getRegistrationDeadline().isBefore(campDateStartChecker))
								System.out.println("Camp Start Date must be after Registration Deadline(today)");
						}while(!campDateStartChecker.isBefore(myCamp.getCampDatesEnd()) || !currentDate.isBefore(campDateStartChecker) || !myCamp.getRegistrationDeadline().isBefore(campDateStartChecker));
							

						update = campDateStart;
						targetUpdate = "campDateStart";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setCampDateStart(campDateStart);
						break;

					case 2:
						String campDateEnd;
						LocalDate campDateEndChecker;
						do {
							do{
								try {
									System.out.println("Edit Camp End Date(YYYY-MM-DD): ");
									campDateEnd = sc.nextLine();
									campDateEndChecker = LocalDate.parse(campDateEnd, dateFormatter);
									break;//format is ok
								} catch (DateTimeParseException e) {
									System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
								}
							} while (true);

							if(!myCamp.getCampDatesStart().isBefore(campDateEndChecker))
								System.out.println("Camp End Date must be after Camp Start Date");

							if (!currentDate.isBefore(campDateEndChecker))
								System.out.println("Camp End Date must be after Current Date(today)");

						}while(!myCamp.getCampDatesStart().isBefore(campDateEndChecker) || !currentDate.isBefore(campDateEndChecker));

						update = campDateEnd;
						targetUpdate = "campDateEnd";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setCampDateEnd(campDateEnd);
						break;

					case 3:
						String registrationDeadline;
						LocalDate registrationDeadlineChecker;
						do {
							do{
								try {
									System.out.println("Edit Registration Deadline(YYYY-MM-DD): ");
									registrationDeadline = sc.nextLine();
									registrationDeadlineChecker = LocalDate.parse(registrationDeadline, dateFormatter);
									break;//format is ok
								} catch (DateTimeParseException e) {
									System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
								}
							} while (true);

							if(!registrationDeadlineChecker.isBefore(myCamp.getCampDatesStart()))
								System.out.println("Registration Deadline must be before Camp Start Date");

							if (!currentDate.isBefore(registrationDeadlineChecker))
								System.out.println("Registration Deadline be after Current Date(today)");

						}while(!registrationDeadlineChecker.isBefore(myCamp.getCampDatesStart()) || !currentDate.isBefore(registrationDeadlineChecker));

						update = registrationDeadline;
						targetUpdate = "registrationDeadline";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setRegistrationDeadline(registrationDeadline);
						break;

					case 4:
						System.out.println("Edit Faculty");
						String faculty = sc.nextLine();
						faculty = faculty.toUpperCase();
						update = faculty;
						targetUpdate = "faculty";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setFaculty(faculty);
						break;

					case 5:
						System.out.println("Open to whole NTU?(Y/N): ");
						String input = sc.nextLine();
						while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"))
						{
							System.out.println("Invalid Option!");
							System.out.println("Open to whole NTU?(Y/N): ");
							input = sc.nextLine();
						}
						boolean openToNTU = BooleanParser.parseCustomBoolean(input);
						update = String.valueOf(openToNTU);
						targetUpdate = "openToNTU";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setOpenToNTU(openToNTU);
						break;

					case 6:
						System.out.println("Edit Location");
						String location = sc.nextLine();
						update = location;
						targetUpdate = "location";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setLocation(location);
						break;

					case 7:
						int totalSlots;
						do{
							try{
								System.out.println("Edit Total Slots:");
								totalSlots = sc.nextInt();
								sc.nextLine();
								break;
							}catch (InputMismatchException e) {
								System.out.println("Please enter a valid integer.");
								sc.nextLine(); // Clear the invalid input from the scanner buffer
							}
						}while(true);

						update = String.valueOf(totalSlots);
						targetUpdate = "totalSlots";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setTotalSlots(totalSlots);
						targetUpdate = "emptySlots";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setEmptySlots(totalSlots);
						
						break;

					case 8:
						int committeeSlots;
						do{
							do{
								try{
									System.out.println("Enter committeeSlots: ");
									committeeSlots = sc.nextInt();
									sc.nextLine();
									break;
								}catch (InputMismatchException e) {
									System.out.println("Please enter a valid integer.");
									sc.nextLine(); // Clear the invalid input from the scanner buffer
								}
							}while(true);
							
							if (committeeSlots > 10)
								System.out.println("Max CommitteeSlots is 10");
						}while(committeeSlots > 10);//to get commmitteeSlots number and checker

						update = String.valueOf(committeeSlots);
						targetUpdate = "committeeSlots";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setCommitteeSlots(committeeSlots);
						break;

					case 9:
						System.out.println("Edit info:");
						String info = sc.nextLine();
						update = info;
						targetUpdate = "info";
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setInfo(info);
						break;

					case 10:
						System.out.println("isVisible?(Y/N): ");
						String input1 = sc.nextLine();
						while(!input1.equalsIgnoreCase("y") && !input1.equalsIgnoreCase("n"))
						{
							System.out.println("Invalid Option!");
							System.out.println("isVisible?(Y/N): ");
							input1 = sc.nextLine();
						}
						boolean isVisible = BooleanParser.parseCustomBoolean(input1);
						update = String.valueOf(isVisible);
						targetUpdate = "isVisible";		
						a.updateCamp(b.getCampListFilePath(), targetUpdate, update, myCamp.getCampID());
						myCamp.setIsVisible(isVisible);	
						break;
					
					case 11:
						System.out.println("Exiting Edit Camp Page...");
						break;

					default:
						System.out.println("Enter valid input!");
				}
			} while (choice != 11);
		}
		else{
			System.out.println("Camp already has participants!");
		}
	}
	/**
	* Delete Camp using StaffCampOperationController
	* @param campList Array list of camp(s) created
	* @param myCamp Camp to delete
	* 
	* @return true if camp is delete and false if camp is not deleted
	*/
	public boolean deleteMyCamp(Camp myCamp, CampList campList) 
	{

		if (myCamp == null)
		{
			System.out.println("No camp created yet!");
			return false;
		}

		CsvEditMethods a = new CsvEditMethods();
		CsvFilePaths b = new CsvFilePaths();
		String staffic = myCamp.getStaffIC();
		
		for (int i = 0; i < campList.getCampList().size(); i++) //if cannot find the camp inside the list of camps
		{
			Camp temp = campList.getCampList().get(i);
			if (staffic.equals(temp.getStaffIC())) //camp is found
			{	

				if (myCamp.getCommitteeList().size() == 0 && myCamp.getAttendeeList().size() == 0) //camp has no participants
				{
					a.deleteCamp(b.getCampListFilePath(), myCamp.getCampID());
					a.deleteAttendeeList(b.getApplicationFolderFilePath()+myCamp.getCampID()+"_attendee_list.csv");
					a.deleteCommitteeList(b.getApplicationFolderFilePath()+myCamp.getCampID()+"_committee_list.csv");
					a.deleteAllEnquiries(b.getCampEnquiryListFilePath(), myCamp.getCampID());
					a.deleteAllSuggestion(b.getCampSuggestionListFilePath(), myCamp.getCampID());
					campList.getCampList().remove(i);
					System.out.println("Camp successfully deleted!");
					return true;
				}
				else{ //camp has participants
					System.out.println("Camp already has partictpants!");
					return false;
				}
			}
		}
		System.out.println("No camp created!");
		return false;
	}

	/**
	* View camp created by Staff with details
	*/
	public void view(Camp myCamp) {
		
		if (myCamp == null)
		{
			System.out.println("No camp created yet!");
			return;
		}
		myCamp.showCampDetails();

		System.out.print(myCamp.getCampID() + "'s attendees: ");
		
		for (int i=0; i<myCamp.getAttendeeList().size();i++)
		{
			System.out.print(myCamp.getAttendeeList().get(i).getName() + " | ");
		}
		System.out.print("\n");
		System.out.print(myCamp.getCampID() + "'s committee members: ");
		for (int i=0; i<myCamp.getCommitteeList().size();i++)
		{
			System.out.print(myCamp.getCommitteeList().get(i).getName() + " | ");
		}
		System.out.print("\n");
	}

	/**
	* Toggle visibility Camp 
	*/
	public void toggle(Camp myCamp) {

		if (myCamp == null)
		{
			System.out.println("No camp created yet!");
			return;
		}
	
		if (myCamp.getCommitteeList().size() == 0 && myCamp.getAttendeeList().size() == 0) //camp has no participants
		{
			CsvEditMethods a = new CsvEditMethods();
			CsvFilePaths b = new CsvFilePaths();
			if (myCamp.getIsVisible() == true)
			{
				myCamp.setIsVisible(false);
				a.updateCamp(b.getCampListFilePath(), "isVisible", String.valueOf(myCamp.getIsVisible()), myCamp.getCampID());
				System.out.println("Visibility is now off");
			}
			else 
			{
				myCamp.setIsVisible(true);
				a.updateCamp(b.getCampListFilePath(), "isVisible", String.valueOf(myCamp.getIsVisible()), myCamp.getCampID());
				System.out.println("Visibility is now on");
			}
		}
		else{ //camp has participants
			System.out.println("Camp already has partictpants!");
		}
	}
	
	/**
	* View all camp 
	*/
	public void viewCamps(CampList campList) {

		if (campList == null)
		{
			System.out.println("No camps created yet!");
			return;
		}
		
		System.out.println("All Camps:");
		for (int i = 0; i <campList.getCampList().size(); i++)
		{
			Camp temp = campList.getCampList().get(i);
			System.out.println(temp.getCampID());
		}
	}

}