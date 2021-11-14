package Model;

import java.awt.Image;

public class ImageModel {
	private int id;
	private Image image;
	private String title;
	private String categories;
	private String description;
	private String comment;
	private String author;
	private String liked;
	
	public ImageModel() {
		
	}
	public ImageModel(int id, Image image, String title, String categories,
			String description, String comment, String author, String liked) {
		this.id = id;
		this.image = image;
		this.title = title;
		this.categories = categories;
		this.description = description;
		this.comment = comment;
		this.author = author;
		this.liked = liked;
	}
	
	public void setImage(Image image) {
		this.image =image;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setCategories(String categories) {
		this.categories = categories;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setLiked(String liked) {
		this.liked = liked;
	}
	
	public int getId() {
		return id;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getCategories() {
		return categories;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getComment() {
		return comment;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getLiked() {
		return liked;
	}
}
