package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import Model.ChatModel;
import Model.FeedbackModel;
import Model.LoginModel;
import Model.MessageModel;
import Model.NotificationModel;
import Model.UserModel;
import View.HomeView;
import View.LoginView;



public class Controller {
	private static LoginView view;
	private static HomeView homeView;
	
	public Controller() {
		
	}
	
	public static void login() {
		view = new LoginView();
		view.getUserInfo();
	}
	
	public static void login(LoginModel user) {
		ResultSet rs = selectUserByName(user.getUsername());
		try {
			if(rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String pass = rs.getString("password");
				String email = rs.getString("email");
				Date dob = rs.getDate("dob");
				String country = rs.getString("country");
				
				if(pass.equals(user.getPassword())) {
					//view.showMessage("Login success!");
					
					UserModel userM = new UserModel(id,username,pass,email,dob,country);
					homeView = new HomeView(userM);
					homeView.main(null);
				}else {
					view.showMessage("Wrong password!");
					view.getUserInfo();
				}
			}else {
				view.showMessage("Wrong username!");
				view.getUserInfo();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public static ResultSet selectUserById(String id) {
		String query = "select * from users where id = ?";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static ResultSet selectUserByName(String username) {
		String query = "select * from users where username = ?";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public boolean updatePass(String pass, String username) {
		String query = "UPDATE `users` SET `password`=? WHERE `username` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, pass);
			pst.setString(2, username);
			
			if(pst.executeUpdate()!=0) {
				b =true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public boolean updateInfo(String email, String dob, String country, String user) {
		String query = "UPDATE `users` SET `email`=?,`dob`=?,`country`=? WHERE `user` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, dob);
			pst.setString(3, country);
			pst.setString(4, user);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean registerUser(String username, String pass1) {
		PreparedStatement pst;
		String registerQuery = "INSERT INTO `users`( `username`, `password`) VALUES (?,?)";
		boolean b = false;
		
		try {
			pst = My_CNX.getConnection().prepareStatement(registerQuery);
			pst.setString(1, username);
			pst.setString(2, pass1);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}else {
				
			}
			
		} catch (SQLException e1) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, e1);
		}
		return b;
	}
	
	public static ResultSet selectLikeImage() {
		String query = "SELECT * FROM `images` ORDER BY liked DESC LIMIT 3";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static ResultSet selectRecentImage() {
		String query = "SELECT * FROM `images` ORDER BY id DESC LIMIT 3";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static ResultSet selectImageById(String id) {
		String query = "select * from images where id = ?";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, id);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static ResultSet selectImageByAuthor(String author) {
		String query = "SELECT * FROM `images` WHERE `author` = ?";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, author);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static ResultSet searchImage(String keywords) {
		String[] keys = keywords.split(" ");
		
		String query = "SELECT * FROM `images` WHERE ";
		query += "`categories` LIKE '%"+keys[0]+"%' ";
		for(int i=1; i<keys.length;i++) {
			query += "AND `categories` LIKE '%"+keys[i]+"%' ";
		}
		
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static boolean updateLikedImage(String liked, String id) {
		String query = "UPDATE `images` SET `liked`=? WHERE `id` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, liked);
			pst.setString(2, id);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean updateCommentImage(String comment, String id) {
		String query = "UPDATE `images` SET `comment`=? WHERE `id` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, comment);
			pst.setString(2, id);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean uploadImage(String title,String cate,String des,String user, String image_path) {
		
		PreparedStatement pst;
		boolean b = false;
		String uploadQuery = "INSERT INTO `images`(`image`, `title`, `categories`, `description`, `author`) VALUES (?,?,?,?,?)";
		
		try {
			pst = My_CNX.getConnection().prepareStatement(uploadQuery);
			pst.setString(2, title);
			pst.setString(3, cate);
			pst.setString(4, des);
			pst.setString(5, user);
			
			InputStream image;
			try {
				image = new FileInputStream(new File(image_path));
				pst.setBlob(1, image);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean insertFeedback(FeedbackModel feedback) {
		String query = "INSERT INTO `feedback`(`content`, `sender`) VALUES (?,?)";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, feedback.getContent());
			pst.setString(2, feedback.getSender());
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean updatePassword(String newPass, String user) {
		String query = "UPDATE `users` SET `password`=? WHERE `username` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, newPass);
			pst.setString(2, user);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean updateEmail(String newEmail, String user) {
		String query = "UPDATE `users` SET `email`=? WHERE `username` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, newEmail);
			pst.setString(2, user);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean updateDob(String newDob, String user) {
		String query = "UPDATE `users` SET `dob`=? WHERE `username` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, newDob);
			pst.setString(2, user);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean updateCountry(String newCountry, String user) {
		String query = "UPDATE `users` SET `country`=? WHERE `username` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, newCountry);
			pst.setString(2, user);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static ResultSet selectMessageByReceiver(String username) {
		String query = "select * from message where receiver = ?";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static boolean insertMessage(MessageModel message) {
		String query = "INSERT INTO `message`( `sender`, `content`, `receiver`) VALUES (?,?,?)";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, message.getSender());
			pst.setString(2, message.getContent());
			pst.setString(3, message.getReceriver());
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static ResultSet selectNotiByReceiver(String username) {
		String query = "select * from notification where receiver = ?";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static boolean insertNoti(NotificationModel notification) {
		String query = "INSERT INTO `notification`( `content`, `receiver`, `sender`) VALUES (?,?,?)";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, notification.getContent());
			pst.setString(2, notification.getReceiver());
			pst.setString(3, notification.getSender());
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean deleteAccount(UserModel user) {
		String query = "DELETE FROM `users` WHERE `username`=?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, user.getUsername());
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean deleteImageById(String id) {
		String query = "DELETE FROM `images` WHERE `id`=?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, id);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static boolean updateImage(String title, String cate, String description, String id) {
		String query = "UPDATE `images` SET `title`=?,`categories`=?,`description`=? WHERE `id` = ?";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, title);
			pst.setString(2, cate);
			pst.setString(3, description);
			pst.setString(4, id);
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
	
	public static ResultSet selectAllChat() {
		String query = "select * from chat ORDER BY `id`";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			rs = pst.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return rs;
	}
	
	public static boolean insertChat(ChatModel chat) {
		String query = "INSERT INTO `chat`(`content`, `sender`) VALUES (?,?)";
		
		PreparedStatement pst;
		boolean b = false;
		
		
		try {
			pst = My_CNX.getConnection().prepareStatement(query);
			pst.setString(1, chat.getContent());
			pst.setString(2, chat.getSender());
			
			if(pst.executeUpdate()!=0) {
				b = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger("Get Connection -> "+ My_CNX.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return b;
	}
}
