import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.*;
import org.json.simple.parser.*;



//Overall: Array of Games
// Each Game: [[bluevictory, [champ1id, [item0-6]], [champ2id]], [redvictory, [champ1id, [item0-6]], [champ2id]]] 


public class APUsageSaver {

	
	public static void write (String filename, ArrayList x) throws IOException{
		  BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(filename));
		  outputWriter.write(x.toString());
		  outputWriter.flush();  
		  outputWriter.close();  
		}
	
	public static void main(String args[]) {
		
		 JSONParser parser=new JSONParser();
		 ArrayList<Game> arr = new ArrayList<Game>();
		 
		 FileReader s;
		try {
			s = new FileReader("C:\\Users\\Aaron\\Desktop\\AP_DATASET\\NORMAL_5X5\\NA.json");
		
         JSONArray gameList = (JSONArray) parser.parse(s);
		
		
		System.out.println("Game List Size: " + gameList.size() + ".");
		 for (int counter=0;counter<gameList.size();counter++) {
		 //START OF WHERE FILE IS SELECTED
		 try {

	         
	         //START OF WHERE MATCH DATA IS DRAWN                                                MATCH ID SELECTION
	         URL matchurl = new URL("https://na.api.pvp.net/api/lol/na/v2.2/match/" + gameList.get(counter).toString() + "?api_key=bbc7c25e-06d9-4806-8d1d-02bcd54d05ff");
	         BufferedReader reader = new BufferedReader(new InputStreamReader(matchurl.openStream()));
	         StringBuffer buffer = new StringBuffer();
	         int read;
	         char[] chars = new char[1024];
	         while ((read = reader.read(chars)) != -1)
	             buffer.append(chars, 0, read); 

	         JSONObject match = (JSONObject) parser.parse(buffer.toString());
	         //System.out.println(match);
	         
	         Champion[] bchampions = new Champion[5];
	         Champion[] rchampions = new Champion[5];
	         
	         
	         for (int h=0;h<((JSONArray)match.get("participants")).size();h++) {
	        	 Object ch = ((JSONArray)match.get("participants")).get(h);
	        	 JSONObject champ = (JSONObject) ch;
	        	 JSONObject champstats = (JSONObject) champ.get("stats");
	        	 Item[] items = new Item[7];
	        	 for (int i=0;i<items.length;i++) {
	        		 Object itemid = champstats.get("item"+i);
	        		 if (itemid!= null) {
	        			 items[i] = new Item((long) itemid);
	        		 }
	        	 }
	        	 if (h<5)	        	 
	        	 bchampions[h] = new Champion(((Long)champ.get("championId")).intValue(), items, (long) champstats.get("kills"), (long) champstats.get("deaths"), (long) champstats.get("assists"));
	        	 else
	        	 rchampions[h-5] = new Champion(((Long)champ.get("championId")).intValue(), items, (long) champstats.get("kills"), (long) champstats.get("deaths"), (long) champstats.get("assists"));
	         }
	         
	         Game game = new Game((String) match.get("region"), "NORMAL", bchampions, rchampions, ((Boolean)((JSONObject)((JSONObject)((JSONArray)match.get("participants")).get(0)).get("stats")).get("winner")).booleanValue() );
	         System.out.println(game);
	         arr.add(game);
	         
	       // for (Object x : gameList) {
	       // 	System.out.println(x);
	       // }
	         //System.out.println(obj.toString());
		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			counter--;
			e.printStackTrace();
			System.out.println("Retrying");
			continue;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
			Thread.sleep(2000);
			
			
		 } //END OF LOOP
		 
		 
		 
		  write("array.txt", arr);
		  
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Done");
		
	}
	
}
