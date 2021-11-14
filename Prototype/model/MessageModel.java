package Model;

public class MessageModel {
	private int id;
	private String sender;
	private String content;
	private String receiver;
	
	public MessageModel(){
		
	}
	
	public MessageModel(int id, String sender, String content, String receiver) {
		this.id = id;
		this.sender = sender;
		this.content = content;
		this.receiver = receiver;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setReceiver(String receiver) {
		this.receiver =receiver;
	}
	
	public int getId() {
		return id;
	}
	
	public String getSender() {
		return sender;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getReceriver() {
		return receiver;
	}
}
