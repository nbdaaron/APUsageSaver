import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.*;



//Overall: Array of Games
// Each Game: [[blue victory, [champ1id, [item0-6]], [champ2id]], [red victory, [champ1id, [item0-6]], [champ2id]]] 


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
		 
		 String[] servers = {"BR", "EUNE", "EUW", "KR", "LAN", "LAS", "NA", "OCE", "RU", "TR"};
		 for (String serv : servers) {
		 	
			 		 	 
			 arr = new ArrayList<Game>();
		 
		 
		try {
			 s = new FileReader("C:\\Users\\Aaron\\Desktop\\5.11\\RANKED_SOLO\\" + serv + ".json");
		
         JSONArray gameList = (JSONArray) parser.parse(s);
		
		
		System.out.println("Game List Size: " + gameList.size() + ".");
		 for (int counter=0;counter<gameList.size();counter++) {
		 //START OF WHERE FILE IS SELECTED
		 try {

	         
	         //START OF WHERE MATCH DATA IS DRAWN                                                MATCH ID SELECTION
			 URL matchurl = new URL("https://" + serv + ".api.pvp.net/api/lol/" + serv.toLowerCase() + "/v2.2/match/" + gameList.get(counter).toString() + "?api_key=6c1f7b39-81e8-4ad9-b58f-2597012a9704");
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
	         
	         Game game = new Game((String) match.get("region"), "RANKED", bchampions, rchampions, ((Boolean)((JSONObject)((JSONObject)((JSONArray)match.get("participants")).get(0)).get("stats")).get("winner")).booleanValue() );
	         System.out.print((counter+1)+"/" +gameList.size()+": "); System.out.println(game);
	         arr.add(game);
	         write("OLD"+serv+"RANKED.txt", arr);
	         
	       // for (Object x : gameList) {
	       // 	System.out.println(x);
	       // }
	         //System.out.println(obj.toString());
		 } catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			counter--;
			e.printStackTrace();
			System.out.println("Retrying");
			continue;
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	      
			
			
		 } //END OF LOOP
		 
		 
		 
		  
		  
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
		catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		
		System.out.println("Done");
		 }
	}
	
}
