import java.io.*;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class pplq1{
	public static void main(String args[]) throws IOException{
		Boys b[] = new Boys[10000];
                createcsv csv= new createcsv();
                int nb=Integer.parseInt(args[0]);
                int ng=Integer.parseInt(args[1]);
                int ngif=Integer.parseInt(args[2]);
                csv.make(nb,ng,ngif);
		String csvFile = "boys.csv";
		String line = "";
		String csvSplitBy = ",";
                int i=0;
                File out= new File("OutputQues1.txt");
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
		Girls g[] = new Girls[10000];
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
		Gifts gif[] = new Gifts[10000];
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
                for(n=0;n<j;n++)
                    for(m=0;m<i;m++)
                    {
                        int x=b[m].iseligible(g[n].mainbudget,g[n].attract);
                        int y=(g[n].iseligible(b[m].budget));
                        if (x==1 && y==1 && b[m].status==0 && g[n].status==0)
                        {
                            b[m].gfname=g[n].name;
                            g[n].bfname=b[m].name;
                            b[m].status=1;
                            g[n].status=1;
                            DateFormat df= new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                            Date dateobj= new Date();
                            String ab="Commitment :"+df.format(dateobj)+" "+g[n].name+ " is committed to "+b[m].name+" "+'\r'+'\n';
                            try(BufferedWriter bw=new BufferedWriter(new FileWriter(out,true))){
                                bw.write(ab);
                            }
                            System.out.println(g[n].name + " is committed to " + b[m].name);
                            //b[m].giftbasket(g,n,gif,k);
                        }
                    }
             
        }
}

        