package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.Controller;
import Model.ImageModel;
import Model.NotificationModel;

public class ImageView {
	private static int id;
	private static String user;
	private static int countlike = 0;
	private static String slike;
	private static String scomment;
	private static JButton delete = new JButton("Delete");
	private static JButton edit = new JButton("Edit");
	
	private static JTextArea commentLabel = new JTextArea(scomment);
	
	private static ImageModel imgModel;
	
	public ImageView(int id, String user) {
		this.id = id;
		this.user = user;
	}
	
	public static void advance() {
		delete.setVisible(true);
		edit.setVisible(true);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.lightGray);
		
		
		try {
			ResultSet res = Controller.selectImageById(Integer.toString(id));
			
			while(res.next()) {
				
				String title = res.getString("title");
				String cate = res.getString("categories");
				String desc = res.getString("description");
				String comment = res.getString("comment");
				String author = res.getString("author");
				String liked = res.getString("liked");
				
				
				
				slike = liked;
				if(liked==null) slike = "";
				
				scomment = comment;
				
				if(comment == null) scomment="";
				
				String[] likeList;
				
				if(liked!=null) {
					likeList = liked.split(" ");
					countlike = likeList.length;
				}else {
					countlike = 0;
				}
				
				try {
					BufferedImage im = ImageIO.read(res.getBinaryStream("image"));
					Image img = im.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
					
					imgModel = new ImageModel(id,img,title,cate,desc,comment,author,liked);
					
					JLabel addimg = new JLabel();
					addimg.setBounds(150, 10, 300, 300);
					panel.add(addimg);
					addimg.setIcon(new ImageIcon(img));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				JLabel authorLabel = new JLabel("by "+author);
				authorLabel.setBounds(150, 320, 80, 25);
				panel.add(authorLabel);
				
				JButton likeButton = new JButton("Like "+countlike);
				likeButton.setBounds(350, 320, 100, 25);
				likeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(slike.contains(user+" ")) return;
						countlike++;
						likeButton.setText("Like "+countlike);
						slike = slike+user+" ";
						
						NotificationModel notification = new NotificationModel();
						notification.setContent("like your post!");
						notification.setReceiver(author);
						notification.setSender(user);
						Controller.insertNoti(notification);
						

						if(Controller.updateLikedImage(slike, Integer.toString(id))) {
							JOptionPane.showMessageDialog(null, "Liked!");
						}else {
							JOptionPane.showMessageDialog(null, "Error!");
						}

					}
				});
				panel.add(likeButton);
				
				JLabel titleLabel = new JLabel("Title: "+title);
				titleLabel.setBounds(150, 345, 300, 25);
				panel.add(titleLabel);
				
				JLabel cateLabel = new JLabel("Categories: "+cate);
				cateLabel.setBounds(150, 370, 300, 25);
				panel.add(cateLabel);
				
				JLabel descLabel = new JLabel("Description: "+desc);
				descLabel.setBounds(150, 395, 300, 25);
				panel.add(descLabel);
				
				JTextArea commentField = new JTextArea();
				commentField.setBounds(150, 420, 300, 50);
				commentField.setLineWrap(true);
				panel.add(commentField);
				
				JButton commentButton = new JButton("Comment");
				commentButton.setBounds(350, 470, 100, 25);
				commentButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String comment = commentField.getText();
						if(comment.equals("")) {
							JOptionPane.showMessageDialog(null, "Empty content!");
							return;
						}
						scomment = scomment+user+": "+comment+"\n";
						commentLabel.setText(scomment);
						

						if(Controller.updateCommentImage(scomment, Integer.toString(id))) {
							NotificationModel notification = new NotificationModel();
							notification.setContent("commented on your post!");
							notification.setReceiver(author);
							notification.setSender(user);
							Controller.insertNoti(notification);
							JOptionPane.showMessageDialog(null, "Commented!");
						}else {
							JOptionPane.showMessageDialog(null, "Error!");
						}
					}
				});
				panel.add(commentButton);
				
				commentLabel.setText(scomment);
				commentLabel.setLineWrap(true);
				commentLabel.setBounds(150, 505, 300, 50);
				panel.add(commentLabel);

			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		delete.setBounds(500, 495, 80, 25);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controller.deleteImageById(Integer.toString(id))) {
					JOptionPane.showMessageDialog(null, "Image deleted!");
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		panel.add(delete);
		delete.setVisible(false);
		
		
		edit.setBounds(500, 460, 80, 25);
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditImageView editImage = new EditImageView(imgModel);
				editImage.getData();
			}
		});
		panel.add(edit);
		edit.setVisible(false);
		
		JButton close = new JButton("Close");
		close.setBounds(500, 530, 80, 25);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(close);
		
		
		frame.setVisible(true);
	}
}
