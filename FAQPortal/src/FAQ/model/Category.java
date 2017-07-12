package FAQ.model;

public class Category {
	private String catName;
	private String CID;
	
	
	public Category(){
		
	}
	
	public Category(String catName, String cID) {
		this.catName = catName;
		this.CID = cID;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	
}
