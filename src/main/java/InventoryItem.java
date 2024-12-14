
public class InventoryItem {
	//declare attributes
	protected String item;
	protected String quantity;
	
	public InventoryItem(String item, String quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
