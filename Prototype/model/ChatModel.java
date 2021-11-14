package Model;

public class ChatModel {
	private int id;
	private String content;
	private String sender;
	
	public ChatModel() {
		
	}
	
	public ChatModel(int id, String content, String sender) {
		this.id = id;
		this.content = content;
		this.sender = sender;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public int getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getSender() {
		return sender;
	}
}
