package CamApp;

import java.util.Scanner;

public class CommitteeReportController implements IEnquiryReport {
	
	public void generate(Camp myCamp) {
		Scanner sc = new Scanner (System.in);
		CsvWriteMethods a = new CsvWriteMethods();
		
		System.out.println("Select filter: ");
		System.out.println("1. Attendee only\n2. Committee only\n3. All students");
		int choice = sc.nextInt();
		sc.nextLine();

		do
		{
			switch(choice)
			{
				case 1:
					a.generateAttendeeReport(myCamp);
					break;
				case 2:
					a.generateCommitteeReport(myCamp);
					break;
				case 3:
					a.generateStudentsReport(myCamp);
					break;
				default:
					System.out.println("Please enter valid choices!");
					break;

			}
		}while (choice <1 || choice >3);
		
	}

	public void generateEnquiryReport (Camp myCamp){
		if (myCamp == null)
		{
			System.out.println("No camps created yet!");
			return;
		}
		
		CsvWriteMethods a = new CsvWriteMethods();
		
		a.generateEnquiryReport(myCamp);
	}

}