package CamApp;

public interface IEnquiryReport extends IReport{
    /**
	 * Generate  eqnuiry report for the camp.
	 * 
	 * @param camp Target camp
	 */
	void generateEnquiryReport(Camp camp);
}
