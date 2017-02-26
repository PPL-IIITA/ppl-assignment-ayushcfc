
import java.io.*;
import java.util.*;
public class createcsv {
	    void make(int nb,int ng,int ngif){
		try{
			FileWriter boy_file = new FileWriter("boys.csv");
			FileWriter girl_file = new FileWriter("girls.csv");
                        FileWriter gift_file = new FileWriter("gifts.csv");
                        int i;
			Random boy_rand = new Random();
			Random girl_rand = new Random();
                        Random gift_rand = new Random();
			for(i=0;i<nb;i++){
				boy_file.write("Boy"+i+","+boy_rand.nextInt(101)+","+boy_rand.nextInt(101)+","+boy_rand.nextInt(5001)+","+boy_rand.nextInt(100)+","+boy_rand.nextInt(3)+"\n");
			}
                        boy_file.close();
			for(i=0;i<ng;i++){
				girl_file.write("Girl"+i+","+girl_rand.nextInt(101)+","+girl_rand.nextInt(5001)+","+girl_rand.nextInt(100)+","+girl_rand.nextInt(3)+"\n");
			}
                        girl_file.close();
                        for(i=0;i<ngif;i++){
				gift_file.write("Gift"+i+","+gift_rand.nextInt(500)+","+gift_rand.nextInt(100)+","+gift_rand.nextInt(3)+"\n");
			}
			gift_file.close();
		}catch(IOException e){		
		
                }	
            }
}