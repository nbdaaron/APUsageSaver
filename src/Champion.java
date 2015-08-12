import java.util.Arrays;


public class Champion {

	private int id;
	private Item[] items;
	private long kills, deaths, assists;
	
	
	public Champion(int id, Item[] items, long kills, long deaths, long assists) {
		this.id = id;
		this.items = items;
		this.kills = kills;
		this.deaths = deaths;
		this.assists = assists;
	}
	
	public int getId() {
		return id;
	}
	
	public Item getItem(int slot) {
		return items[slot];
	}
	
	public Item[] getItems() {
		return items;
	}
	
	public long getKills() {
		return kills;
	}
	
	public long getDeaths() {
		return deaths;
	}
	
	public long getAssists() {
		return assists;
	}
	
	public String toString() {
		return id+", "+Arrays.toString(items);
	}
	
	
}
