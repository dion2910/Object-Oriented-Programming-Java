package CamApp;
public interface IPerformanceReport extends IEnquiryReport {

	/**
	 * Extension of IReport to generate performance report of particular camp's committee members.
	 * 
	 * @param camp Target camp
	 */
	void generatePerfReport(Camp camp);

}