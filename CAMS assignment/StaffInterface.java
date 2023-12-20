package CamApp;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class StaffInterface 
{
    public static void showStaffInterface(CampList campList, Staff staff) //port over to a new class showStaffInterface
	{

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate currentDate = LocalDate.now();
		int choice;

		Scanner sc = new Scanner(System.in);
		
		try{
			do{
				System.out.println("-------------------");
				System.out.println("Select an option: ");
				System.out.println("1. Create Camp");
				System.out.println("2. Edit Camp");
				System.out.println("3. Delete Camp");
				System.out.println("4. Toggle Camp Visibility");
				System.out.println("5. View All Camps");
				System.out.println("6. View My Created Camp Details");
				System.out.println("7. View My Camp's Enquiry");
				System.out.println("8. Reply My Camp's Enquiry");
				System.out.println("9. View My Camp's Suggestion");
				System.out.println("10. Approve My Camp's Suggestion");
				System.out.println("11. Generate My Camp's Report(Namelist)");
				System.out.println("12. Generate My Camp's Performance Report");
				System.out.println("13. Generate My Camp's Enquiry Report");
				System.out.println("14. Change Password");
				System.out.println("15. Log Out");
				System.out.println("-------------------");

				//checking for invalid input
				do{
					if (sc.hasNextInt()){
						choice = sc.nextInt();
						sc.nextLine();
						if(choice >=1 && choice <= 15){
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
				
				switch(choice)
				{
					case 1:
						if (staff.getMyCamp() != null){
							System.out.println("Already created a camp!");
							break;
						}
						System.out.println("Create Camp.");
						System.out.println("Enter Camp Name: ");
						String campID = sc.nextLine();

						//campstart
						String campDateStart;
						LocalDate campDateStartChecker;
						do {
							do{
								try {
									System.out.println("Enter Camp Start Date(YYYY-MM-DD): ");
									campDateStart = sc.nextLine();
									campDateStartChecker = LocalDate.parse(campDateStart, dateFormatter);
									break;//format is ok
								} catch (DateTimeParseException e) {
									System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
								}
							} while (true);

							if (!currentDate.isBefore(campDateStartChecker))
								System.out.println("Camp Start Date must be after Current Date(today)");
						}while(!currentDate.isBefore(campDateStartChecker));
						
						//campend
						String campDateEnd;
						LocalDate campDateEndChecker;
						do {
							do{
								try {
									System.out.println("Enter Camp End Date(YYYY-MM-DD): ");
									campDateEnd = sc.nextLine();
									campDateEndChecker = LocalDate.parse(campDateEnd, dateFormatter);
									break;//format is ok
								} catch (DateTimeParseException e) {
									System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
								}
							} while (true);

							if(!campDateStartChecker.isBefore(campDateEndChecker))
								System.out.println("Camp End Date must be after Camp Start Date");

							if (!currentDate.isBefore(campDateEndChecker))
								System.out.println("Camp End Date must be after Current Date(today)");

						}while(!campDateStartChecker.isBefore(campDateEndChecker) || !currentDate.isBefore(campDateEndChecker));

						//daeadline
						String registrationDeadline;
						LocalDate registrationDeadlineChecker;
						do {
							do{
								try {
									System.out.println("Enter Registration Deadline(YYYY-MM-DD): ");
									registrationDeadline = sc.nextLine();
									registrationDeadlineChecker = LocalDate.parse(registrationDeadline, dateFormatter);
									break;//format is ok
								} catch (DateTimeParseException e) {
									System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
								}
							} while (true);

							if(!registrationDeadlineChecker.isBefore(campDateStartChecker))
								System.out.println("Registration Deadline must be before Camp Start Date");

							if (!currentDate.isBefore(registrationDeadlineChecker))
								System.out.println("Registration Deadline be after Current Date(today)");

						}while(!registrationDeadlineChecker.isBefore(campDateStartChecker) || !currentDate.isBefore(registrationDeadlineChecker));

						//faculty
						System.out.println("Open to which faculty?: ");
						String faculty = sc.nextLine();
						faculty = faculty.toUpperCase();
						//opentoNTU
						System.out.println("Open to whole NTU?(Y/N): ");
						String input = sc.nextLine();
						
						while(!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"))
						{
							System.out.println("Invalid Option!");
							System.out.println("Open to whole NTU?(Y/N): ");
							input = sc.nextLine();
						}
						boolean openToNTU = BooleanParser.parseCustomBoolean(input);//return True or False based on the input
						//location
						System.out.println("Enter location: ");
						String location = sc.nextLine();
						//TotalSlots
						int totalSlots;
						do {
							try {
								System.out.println("Enter totalSlots: ");
								totalSlots = sc.nextInt();
								sc.nextLine(); // Consume the newline character after reading the integer
								break; // If an integer is successfully read, exit the loop
							} catch (InputMismatchException e) {
								System.out.println("Please enter a valid integer.");
								sc.nextLine(); // Clear the invalid input from the scanner buffer
							}
						} while (true);

						//empty slots == totalSlots when camp first created
						int emptySlots = totalSlots;
						//committe slots
						int committeeSlots;
						do{
							do{
								try{
									System.out.println("Enter committeeSlots: ");
									committeeSlots = sc.nextInt();
									sc.nextLine();
									break;
								} catch (InputMismatchException e) {
									System.out.println("Please enter a valid integer.");
									sc.nextLine(); // Clear the invalid input from the scanner buffer
								}
							}while(true); //exit only if committeeSlots is an integer

							if (committeeSlots > 10)
								System.out.println("Max CommitteeSlots is 10");
						}while(committeeSlots > 10);

						//info
						System.out.println("Enter info: ");
						String info = sc.nextLine();
						//isvisible
						System.out.println("isVisible?(Y/N): ");
						String input1 = sc.nextLine();
						while(!input1.equalsIgnoreCase("y") && !input1.equalsIgnoreCase("n"))
						{
							System.out.println("Invalid Option!");
							System.out.println("isVisible?(Y/N): ");
							input1 = sc.nextLine();
						}
						boolean isVisible = BooleanParser.parseCustomBoolean(input1);
						//create camp object and write to csv is in controller
						staff.createCamp(campList, campID, campDateStart, campDateEnd, registrationDeadline, faculty, 
						openToNTU, location, totalSlots, emptySlots, committeeSlots, info, staff.getUserID(), isVisible);
						break;
					case 2:
						staff.editCamp();
						break;
					case 3:
						staff.deleteCamp(campList);
						break;
					case 4:
						staff.toggleCampVisibility();
						break;
					case 5:
						staff.viewAllCamp(campList);
						break;
					case 6:
						staff.viewMyCreatedCamp();
						break;
					case 7:
						staff.viewEnquiry();
						break;
					case 8:
						staff.replyEnquiry();
						break;
					case 9:
						staff.viewSuggestion();
						break;
					case 10:
						staff.approveSuggestions();
						break;
					case 11:
						staff.generateReport();
						break;
					case 12:
						staff.generatePerfReport();
						break;
					case 13:
						staff.generateEnquiryReport();
						break;
					case 14:
						CsvEditMethods a = new CsvEditMethods();
						CsvFilePaths b = new CsvFilePaths();
						System.out.println("Enter new password: ");
						String newPassword = sc.nextLine();
						staff.setPassword(newPassword);
						a.updatePassword(b.getStaffListFilePath(), "Password", newPassword, staff.getUserID());
						System.out.println("Password changed successfully!");
						break;
					case 15:
						System.out.println("Logging Out...");
						break;
					default:
						System.out.println("Please enter a valid choice!");
						break;
				}
			}while(choice != 15);
		} catch (InputMismatchException e){
			System.out.println("Invalid input. Please enter an integer.");
			sc.nextLine();
		} 
}
}
