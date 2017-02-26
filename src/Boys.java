import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Boys {
    String name;
    int attract;
    int intelli;
    int budget;
    int status;
    String gfname;
    int gfindex;
    int minattract;
    int type;
    double happiness;
    double total;
    double totalcomp;
    File out=new File("OutputQues2.txt");
    DateFormat df= new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    Date dateobj= new Date();
    //type 0=miser 1=generous 2=geeks
    
    int iseligible(int mainbudget,int attract){
        if((this.budget>= mainbudget) && (this.minattract<= attract))
            return 1;
        else
            return 0;
    }
    
    void giftbasket(Girls g[],int n,Gifts gif[],int k) throws IOException{
        if(type==0)
        {
            int balance=g[n].mainbudget;
            int i=0;
            while(balance>0 && i<k){
                balance=balance-gif[i].price;
                System.out.println(name + " gave a gift to " + g[n].name);
                String ab="Gift Exchange :"+df.format(dateobj)+" "+name+ " gave a gift to "+g[n].name+" "+'\r'+'\n';
                try(BufferedWriter bw=new BufferedWriter(new FileWriter(out,true))){
                    bw.write(ab);
                }
                g[n].arraygirls.add(gif[i]);
                i++;
            }
            if(balance<0){
                if(budget-(g[n].mainbudget + balance)<gif[i].price)
                    budget=budget-balance;
            }
        }
        if(type==1)
        {
            int balance=budget;
            int i=0;
            while(balance>0 && i<k){
                balance=balance-gif[i].price;
                g[n].arraygirls.add(gif[i]);
                if(balance>0){
                    String ab="Gift Exchange :"+df.format(dateobj)+" "+name+ " gave a gift to "+g[n].name+" "+'\r'+'\n';
                    try(BufferedWriter bw=new BufferedWriter(new FileWriter(out,true))){
                        bw.write(ab);
                    }
                    System.out.println(name + " gave a gift to " + g[n].name);
                }
                i++;
            }
            if(balance<0){
                balance=balance+gif[i-1].price;
                g[n].arraygirls.remove(gif[i-1]);
                i--;
            }
            g[n].status=1;
        }
        if(type==2)
        {
            int balance=g[n].mainbudget;
            int i=0;
            while(balance>0 && i<k){
                balance=balance-gif[i].price;
                g[n].arraygirls.add(gif[i]);
                String ab="Gift Exchange :"+df.format(dateobj)+" "+name+ " gave a gift to "+g[n].name+" "+'\r'+'\n';
                try(BufferedWriter bw=new BufferedWriter(new FileWriter(out,true))){
                    bw.write(ab);
                }
                System.out.println(name + " gave a gift to " + g[n].name);
                i++;
            }
            if(balance<0){
                if(budget-(g[n].mainbudget + balance)<gif[i].price){
                    budget=budget-balance;
            }  else{
                int j;
                for(j=i;j<k;j++)
                {
                    if((gif[j].type==1) && ((budget-(g[n].mainbudget+balance))>0)){
                        g[n].arraygirls.add(gif[j]);
                        String ab="Gift Exchange :"+df.format(dateobj)+" "+name+ " gave a gift to "+g[n].name+" "+'\r'+'\n';
                        try(BufferedWriter bw=new BufferedWriter(new FileWriter(out,true))){
                            bw.write(ab);
                        }
                        System.out.println(name + " gave a gift to " + g[n].name);
                        break;
                    }
                }
                }
            }
        }
    }
}