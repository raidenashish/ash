package FAQ.model;

public class Query {
	String query,answer;
	String QID,assignedUID;
	String emailUser;
	Category category;
	subCategory subCategory;
	int publishFlag;
	
	
	
	
	public Query() {
		
	}
	public Query(String query, String answer, String qID, String assignedUID, String emailUser, Category category,
			subCategory subCategory, int publishFlag) {
		
		this.query = query;
		this.answer = answer;
		this.QID = qID;
		this.assignedUID = assignedUID;
		this.emailUser = emailUser;
		this.category = category;
		this.subCategory = subCategory;
		this.publishFlag = publishFlag;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQID() {
		return QID;
	}
	public void setQID(String qID) {
		QID = qID;
	}
	public String getAssignedUID() {
		return assignedUID;
	}
	public void setAssignedUID(String assignedUID) {
		this.assignedUID = assignedUID;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public subCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(subCategory subCategory) {
		this.subCategory = subCategory;
	}
	public int isPublishFlag() {
		return publishFlag;
	}
	public void setPublishFlag(int publishFlag) {
		this.publishFlag = publishFlag;
	}
	
}
