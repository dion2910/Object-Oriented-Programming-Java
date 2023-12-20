package CamApp;
import java.util.ArrayList;

public class CampList {

    //Attributes
    private ArrayList <Camp> campList = new ArrayList<Camp>();

    /**
     * Get camplist array which consists of camp(s) created
     * @return camplist array with camp(s) created
     */
    public ArrayList<Camp> getCampList(){
        return this.campList;
    }
    /**
     * Add camp into camplist array
     * 
     */
    public void setCampList(Camp camp){
        this.campList.add(camp);
    }
}
