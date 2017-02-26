import java.io.*;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class pplq2{
	public static void main(String args[]) throws IOException{
                createcsv csv= new createcsv();
                int nb=Integer.parseInt(args[0]);
                int ng=Integer.parseInt(args[1]);
                int ngif=Integer.parseInt(args[2]);
                int abc=Integer.parseInt(args[3]);
                csv.make(nb,ng,ngif);
                Boys b[] = new Boys[nb+1];
		String csvFile = "boys.csv";
		String line = "";
		String csvSplitBy = ",";
                File out=new File("OutputQues2.txt");
                int i=0;
		try{
			BufferedReader buffer = new BufferedReader(new FileReader(csvFile));
			while((line = buffer.readLine()) !=null){
				String[] boy = line.split(csvSplitBy);
				b[i] = new Boys();
                                b[i].name = boy[0];
				b[i].attract = Integer.parseInt(boy[1]);
                                b[i].intelli = Integer.parseInt(boy[2]);
				b[i].budget = Integer.parseInt(boy[3]);
                                b[i].minattract= Integer.parseInt(boy[4]);
                                b[i].type=Integer.parseInt(boy[5]);
                                b[i].status =0;
                                b[i].gfname="";
                                b[i].gfindex=-1;
                                b[i].total=0;
                                b[i].happiness=0;
				i++;
			}
		}		
		catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
                
                csvFile = "girls.csv";
		Girls g[] = new Girls[ng+1];
                int j=0;
		try{
			BufferedReader buffer = new BufferedReader(new FileReader(csvFile));
			while((line = buffer.readLine()) !=null){
				String[] girl = line.split(csvSplitBy);
				g[j] = new Girls();
                                g[j].name = girl[0];
				g[j].attract = Integer.parseInt(girl[1]);
				g[j].mainbudget = Integer.parseInt(girl[2]);
				g[j].intelli = Integer.parseInt(girl[3]);
                                g[j].type=Integer.parseInt(girl[4]);
				g[j].status = 0;
				g[j].bfname="";   
                                g[j].giftprice=0;
                                g[j].origgiftprice=0;
                                g[j].happiness=0;
				j++;
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
                }
                
                csvFile = "gifts.csv";
		Gifts gif[] = new Gifts[ngif+1];
                int k=0;
		try{
			BufferedReader buffer = new BufferedReader(new FileReader(csvFile));
			while((line = buffer.readLine()) !=null){
				String[] gift = line.split(csvSplitBy);
                                gif[k]=new Gifts();
                                gif[k].name=gift[0];
                                gif[k].price=Integer.parseInt(gift[1]);
                                gif[k].value=Integer.parseInt(gift[2]);
                                gif[k].type=Integer.parseInt(gift[3]);
                                k++;
                        }
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
                }
   
                Gifts tempg=new Gifts();
                int m,n;
                for(m=0;m<(k-1);m++){
                    for(n=m+1;n<k;n++){
                        if(gif[m].price>(gif[n].price)){
                            tempg=gif[m];
                            gif[m]=gif[n];
                            gif[n]=tempg;
                        }
                    }
                }                
                try(BufferedWriter bw=new BufferedWriter(new FileWriter(out,false))){
                                bw.write("");
                            }
                //Find Committed Couples;
                for(n=0;n<j;n++){
                    for(m=0;m<i;m++)
                    {
                        int x=b[m].iseligible(g[n].mainbudget,g[n].attract);
                        int y=(g[n].iseligible(b[m].budget));
                        if (x==1 && y==1 && b[m].status==0 && g[n].status==0)
                        {
                            b[m].gfname=g[n].name;
                            g[n].bfname=b[m].name;
                            b[m].gfindex=n;
                            g[n].bfindex=m;
                            b[m].status=1;
                            g[n].status=1;
                            DateFormat df= new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                            Date dateobj= new Date();
                            String ab="Commitment :"+df.format(dateobj)+" "+g[n].name+ " is committed to "+b[m].name+" "+'\r'+'\n';
                            try(BufferedWriter bw=new BufferedWriter(new FileWriter(out,true))){
                                bw.write(ab);
                            }
                            b[m].giftbasket(g,n,gif,k);
                        }
                    }
                }
                //Find Happiness of girls
                int p=0,q;
                for(p=0;p<j;p++){
                    if(g[p].status==1){
                        int size=g[p].arraygirls.size();
                        for(q=0;q<size;q++)
                        {
                            if(g[p].type==0){
                                if(g[p].arraygirls.get(q).type==1)
                                {
                                    g[p].origgiftprice+=g[p].arraygirls.get(q).price;
                                    g[p].giftprice+=2*g[p].arraygirls.get(q).price;
                                }
                                else 
                                {
                                    g[p].origgiftprice+=g[p].arraygirls.get(q).price;
                                    g[p].giftprice+=g[p].arraygirls.get(q).price;
                                }
                            }
                            if(g[p].type==1){    
                                g[p].origgiftprice+=g[p].arraygirls.get(q).price;
                                g[p].giftprice+=g[p].arraygirls.get(q).price+g[p].arraygirls.get(q).value;
                            }
                            if(g[p].type==2){
                                g[p].origgiftprice+=g[p].arraygirls.get(q).price;
                                g[p].giftprice+=g[p].arraygirls.get(q).price;
                            }   
                        }
                        if(g[p].type==0)
                            g[p].happiness=log(g[p].giftprice);//log((g[p].giftprice/g[p].mainbudget));
                        if(g[p].type==1)
                            g[p].happiness=g[p].giftprice;//g[p].giftprice/g[p].mainbudget;
                        if(g[p].type==2){
                            g[p].happiness=exp((g[p].giftprice));//exp((g[p].giftprice/g[p].mainbudget));
                        }
                    }
                }
                //Find Happiness of Boys
                for(p=0;p<i;p++){
                    if(b[p].status==1){
                        if(b[p].type==0)
                            b[p].happiness=b[p].budget-g[(b[p].gfindex)].origgiftprice;
                        if(b[p].type==1)
                            b[p].happiness=g[(b[p].gfindex)].happiness;
                        if(b[p].type==2)
                            b[p].happiness=g[(b[p].gfindex)].intelli;
                    b[p].total=b[p].happiness+g[b[p].gfindex].happiness;
                    }
                }
                //Sort According to happiness
                Boys temp=new Boys();
                for(p=0;p<(i-1);p++){
                    if(b[p].status==1){
                        for(q=p+1;q<i ;q++){
                            if(b[q].status==1){
                                if(b[p].total<(b[q].total)){
                                    temp=b[p];
                                    b[p]=b[q];
                                    b[q]=temp;
                                }
                            }
                        }
                    }
                }
                //Find Compatability
                for(p=0;p<i;p++){
                    if(b[p].status==1){
                        b[p].totalcomp=(b[p].budget-g[b[p].gfindex].mainbudget)+abs(b[p].attract-g[b[p].gfindex].attract)+abs(b[p].intelli-g[b[p].gfindex].intelli);
                    }
                }
                //Print happiest couples
                int c=0;
                System.out.println("Happiest Couples are:");
                for(p=0;p<i && c<abc;p++){
                    if(b[p].status==1){
                    System.out.println(b[p].name + " and " + g[b[p].gfindex].name );
                    c++;
                    }
                }
                
                // Sort by Compatability
                for(p=0;p<(i-1);p++){
                    if(b[p].status==1){
                        for(q=p+1;q<i;q++){
                            if(b[q].status==1){
                                if(b[p].totalcomp<(b[q].totalcomp)){
                                    temp=b[p];
                                    b[p]=b[q];
                                    b[q]=temp;
                                }
                            }
                        }
                    }
                }
                
                // Print Compatible Couples.
                c=0;
                System.out.println("Most Compatible Couples are:");
                for(p=0;p<i && c<abc;p++){
                    if(b[p].status==1){
                    System.out.println(b[p].name + " and " + g[b[p].gfindex].name);
                    c++;
                    }
                }
            }
}
