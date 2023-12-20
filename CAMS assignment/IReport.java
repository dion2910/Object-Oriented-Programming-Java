package CamApp;
public interface IReport {

	/**
	 * Generate report(s) for camp with filters.
	 * 
	 * @param camp Target camp
	 */
	void generate(Camp camp);

}