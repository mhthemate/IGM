package Model;

public class NotificationModel {
	private int id;
	private String content;
	private String receiver;
	private String sender;
	
	public NotificationModel() {
		
	}
	
	public NotificationModel(int id, String content, String receiver, String sender) {
		this.id = id;
		this.content = content;
		this.receiver = receiver;
		this.sender = sender;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	
	public String getReceiver() {
		return receiver;
	}
	
	public String getSender() {
		return sender;
	}
}
