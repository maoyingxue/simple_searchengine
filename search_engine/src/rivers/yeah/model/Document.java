package rivers.yeah.model;

public class Document {
	
	String id;
	String url;
	String title;
	String content;
	String abstract1;
	
	public Document(String id, String url, String title, String content,String abstract1) {
		super();
		this.id = id;
		this.url = url;
		this.title = title;
		this.content = content;
		this.abstract1=abstract1;
	}
	
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    public String getContent() {  
        return content;  
    }  
    public void setContent(String abstract1) {  
        this.abstract1 = abstract1;  }
    public String getAbstract1() {  
        return abstract1;  
    }  
    public void setAbstract1(String abstract1) {  
        this.abstract1 = abstract1;  }
}
