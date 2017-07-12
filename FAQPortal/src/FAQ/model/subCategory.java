package FAQ.model;

public class subCategory {
	private String subCatName;
	private String CID,UID;
	
	
	public subCategory(String subCatName, String cID, String uID) {
		this.subCatName = subCatName;
		this.CID = cID;
		this.UID = uID;
	}
	public subCategory(){
		
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	
}
