package CamApp;

import java.util.*;

public class StaffReportController implements IPerformanceReport {
	

	/**
	* Generate report 
	*/
	public void generate(Camp myCamp) {

		if (myCamp == null)
		{
			System.out.println("No camps created yet!");
			return;
		}

		Scanner sc = new Scanner (System.in);
		CsvWriteMethods a = new CsvWriteMethods();
		
		int choice;
		do
		{
			System.out.println("Select filter: ");
			System.out.println("1. Attendee only\n2. Committee only\n3. All students\n4. Exit");
			
			do{
				if (sc.hasNextInt()){
					choice = sc.nextInt();
					sc.nextLine();
					if(choice >=1 && choice <= 4){
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
					a.generateAttendeeReport(myCamp);
					break;
				case 2:
					a.generateCommitteeReport(myCamp);
					break;
				case 3:
					a.generateStudentsReport(myCamp);
					break;
				case 4:
					return;
				default:
					System.out.println("Please enter valid choices!");
					break;

			}
		}while (choice <1 || choice >4);
	}

	/**
	* Geenrate performance report 
	*/
	public void generatePerfReport(Camp myCamp) {

		if (myCamp == null)
		{
			System.out.println("No camps created yet!");
			return;
		}
		
		CsvWriteMethods a = new CsvWriteMethods();
		
		a.staffGeneratePerfReport(myCamp);
	}

	/**
	* Generate enquiry report 
	*/
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