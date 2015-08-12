
public class Game {

	private boolean bluewin;
	private Champion[] blue;
	private Champion[] red;
	private String server;
	private String type;
	
	public Game(String server, String type, Champion[] blue, Champion[] red, boolean bluewin) {
		this.bluewin = bluewin;
		this.red = red;
		this.blue = blue;
		this.server = server;
		this.type = type;
	}
	
	public boolean blueWin() {
		return bluewin;
	}
	
	public boolean redWin() {
		return !bluewin;
	}
	
	public Champion getChamp(int team, int slot) {
		if (team == 0) return getBlueChamp(slot);
		return getRedChamp(slot);
	}
	
	public Champion getBlueChamp(int slot) {
		return blue[slot];
	}
	
	public Champion getRedChamp(int slot) {
		return red[slot];
	}
	
	public Champion[] getBlueChamps(){
		return blue;
	}
	
	public Champion[] getRedChamps() {
		return red;
	}
	
	public Champion[] getChamps(int team) {
		if (team == 0) return getBlueChamps();
		return getRedChamps();
	}
	
	public String toString() {
		String ret = "["+ server + ", " + type + ", " + "[";
		ret += blueWin();
		for (int i=0;i<blue.length;i++) {
		ret += ", [" + blue[i] + "]";
		}
		ret += "], [";
		ret += redWin();
		for (int i=0;i<red.length;i++) {
			ret += ", [" + red[i] + "]";
			}
		ret += "]]";
		
		return ret;
		
	}
	
	
}
