
import java.util.ArrayList;

public class Girls {
    String name;
    int attract;
    int mainbudget;
    int intelli;
    int status;
    String bfname;
    int bfindex;
    int origgiftprice;
    int giftprice;
    int type;
    ArrayList<Gifts> arraygirls = new ArrayList<Gifts>();
    //type 0=choosy 1=normal 2=desperate
    double happiness;
    
    int iseligible(int budget){
        if(budget>=this.mainbudget)
            return 1;
        else 
            return 0;
    }
}
