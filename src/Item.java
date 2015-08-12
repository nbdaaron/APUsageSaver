
public class Item {

	private long id;
	private boolean isAp = false;
	public static int[] AP_ITEMS = {3303, 1056, 2139, 1052, 3108, 3113, 1026, 3098, 3191, 3057, 3145, 1058, 3041, 3136, 3504, 3092, 3708, 3716, 3720, 3724, 3165, 3023, 3050, 3001, 3152, 3135, 3125, 3170, 3027, 3174, 3025, 3115, 3151, 3003, 3157, 3060, 3100, 3285, 3116, 3146, 3089, 3078};
	
	
	public Item(long itemid) {
		id = itemid;
		for (int a : AP_ITEMS) {
			if (id == a)
				isAp = true;
		}
		
	}
	
	public long getId() {
		return id;
	}
	
	public boolean isApItem() {
		return isAp;
	}
	
	public String toString() {
		return ""+getId();
	}
	
	
}
