package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.Controller;
import Model.ChatModel;
import Model.FeedbackModel;
import Model.MessageModel;
import Model.UserModel;

public class HomeView {
	private static JFrame frame = new JFrame();
	private static JPanel panel = new JPanel();
	private static JPanel panel1 = new JPanel();
	
	//properties of panel2 a.k.a Home Page
	private static JPanel panel2 = new JPanel();
	private static JLabel list1 = new JLabel("Most liked images>");
	private static JLabel img1 = new JLabel();
	private static JLabel img2 = new JLabel();
	private static JLabel img3 = new JLabel();
	private static JLabel list2 = new JLabel("Most recent images>");
	private static JLabel img4 = new JLabel();
	private static JLabel img5 = new JLabel();
	private static JLabel img6 = new JLabel();
	
	private static JPanel panel3 = new JPanel();
	private static JPanel panel4 = new JPanel();
	private static JTextArea boxChat = new JTextArea();
	
	private static JPanel panel5 = new JPanel();
	private static JPanel panel5s = new JPanel();
	private static JTextField keyword = new JTextField();
	
	private static JPanel panel6 = new JPanel();
	private static JLabel pathLabel = new JLabel("image path");
	private static String image_path=null;
	
	private static JPanel panel7 = new JPanel();
	private static JLabel message = new JLabel();
	
	private static JPanel panel8 = new JPanel();
	private static JLabel notiLabel = new JLabel();
	
	private static UserModel user;
	
	public HomeView(UserModel user) {
		this.user = user;
	}
	
	public static String getUser() {
		return user.getUsername();
	}
	
	private static JPanel updateHome() {
		
		try {
			ResultSet rs = Controller.selectLikeImage();
			int i=1;
			while(rs.next()) {
				try {
					String id = rs.getString("id");
					BufferedImage im = ImageIO.read(rs.getBinaryStream("image"));
					Image img = im.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
					if(i==1) {
						img1.setIcon(new ImageIcon(img));
						img1.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent arg0) {
								ImageView viewimg = new ImageView(Integer.parseInt(id) ,getUser());
								viewimg.main(null);
							}

							@Override
							public void mouseEntered(MouseEvent arg0) {
							}

							@Override
							public void mouseExited(MouseEvent arg0) {
							}

							@Override
							public void mousePressed(MouseEvent arg0) {
							}

							@Override
							public void mouseReleased(MouseEvent arg0) {
							}
							
						});
						i++;
					}else if(i==2) {
						img2.setIcon(new ImageIcon(img));
						img2.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent arg0) {
								ImageView viewimg = new ImageView(Integer.parseInt(id) ,getUser());
								viewimg.main(null);
							}

							@Override
							public void mouseEntered(MouseEvent arg0) {
							}

							@Override
							public void mouseExited(MouseEvent arg0) {
							}

							@Override
							public void mousePressed(MouseEvent arg0) {	
							}

							@Override
							public void mouseReleased(MouseEvent arg0) {
							}
							
						});
						i++;
					}else if(i==3) {
						img3.setIcon(new ImageIcon(img));
						img3.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent arg0) {
								ImageView viewimg = new ImageView(Integer.parseInt(id) ,getUser());
								viewimg.main(null);
							}

							@Override
							public void mouseEntered(MouseEvent arg0) {
							}

							@Override
							public void mouseExited(MouseEvent arg0) {	
							}

							@Override
							public void mousePressed(MouseEvent arg0) {	
							}

							@Override
							public void mouseReleased(MouseEvent arg0) {
							}
							
						});
						i++;
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		try {
			ResultSet res = Controller.selectRecentImage();
			int i=1;
			while(res.next()) {
				try {
					String id = res.getString("id");
					BufferedImage im = ImageIO.read(res.getBinaryStream("image"));
					Image img = im.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
					if(i==1) {
						img4.setIcon(new ImageIcon(img));
						img4.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent arg0) {
								ImageView viewimg = new ImageView(Integer.parseInt(id) ,getUser());
								viewimg.main(null);
							}

							@Override
							public void mouseEntered(MouseEvent arg0) {
							}

							@Override
							public void mouseExited(MouseEvent arg0) {
							}

							@Override
							public void mousePressed(MouseEvent arg0) {
							}

							@Override
							public void mouseReleased(MouseEvent arg0) {
							}
							
						});
						i++;
					}else if(i==2) {
						img5.setIcon(new ImageIcon(img));
						img5.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent arg0) {
								ImageView viewimg = new ImageView(Integer.parseInt(id) ,getUser());
								viewimg.main(null);
							}

							@Override
							public void mouseEntered(MouseEvent arg0) {
							}

							@Override
							public void mouseExited(MouseEvent arg0) {
							}

							@Override
							public void mousePressed(MouseEvent arg0) {
							}

							@Override
							public void mouseReleased(MouseEvent arg0) {
							}
							
						});
						i++;
					}else if(i==3) {
						img6.setIcon(new ImageIcon(img));
						img6.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent arg0) {
								ImageView viewimg = new ImageView(Integer.parseInt(id) ,getUser());
								viewimg.main(null);
							}

							@Override
							public void mouseEntered(MouseEvent arg0) {
							}

							@Override
							public void mouseExited(MouseEvent arg0) {
							}

							@Override
							public void mousePressed(MouseEvent arg0) {
							}

							@Override
							public void mouseReleased(MouseEvent arg0) {
							}
							
						});
						i++;
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return panel2;
	}
	
	private static JPanel updateMyGallery() {
		panel3.removeAll();
		
		try {
			ResultSet res = Controller.selectImageByAuthor(getUser());

			while(res.next()) {
				try {
					String id = res.getString("id");
					BufferedImage im = ImageIO.read(res.getBinaryStream("image"));
					Image img = im.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
					
					JLabel addimg = new JLabel();
					addimg.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent arg0) {
							ImageView viewimg = new ImageView(Integer.parseInt(id) ,getUser());
							viewimg.main(null);
							viewimg.advance();
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
						}

						@Override
						public void mousePressed(MouseEvent arg0) {
						}

						@Override
						public void mouseReleased(MouseEvent arg0) {
						}
						
					});
					panel3.add(addimg);
					addimg.setIcon(new ImageIcon(img));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return panel3;
	}
	
	private static JPanel updateSearch() {
		panel5s.removeAll();
		panel5s.repaint();
		
		
		String keywords = keyword.getText();
		if(keywords.equals("") || keywords == null) {
			JOptionPane.showMessageDialog(null, "Enter keyword(s) please!");
			return panel5s;
		}
		
		
		try {
			ResultSet res = Controller.searchImage(keywords);

			while(res.next()) {
				try {
					String id = res.getString("id");
					BufferedImage im = ImageIO.read(res.getBinaryStream("image"));
					Image img = im.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
					
					JLabel addimg = new JLabel();
					addimg.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent arg0) {
							ImageView viewimg = new ImageView(Integer.parseInt(id) ,getUser());
							viewimg.main(null);
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
						}

						@Override
						public void mousePressed(MouseEvent arg0) {
						}

						@Override
						public void mouseReleased(MouseEvent arg0) {
						}
						
					});
					panel5s.add(addimg);
					addimg.setIcon(new ImageIcon(img));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return panel5s;
	}
	
	
	public static void main(String[] args) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		
		
//		panel.setPreferredSize(new Dimension(250, 250));
		panel.setBackground(Color.gray);
		
		// Menu bar
		JButton home = new JButton("Home");
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
				panel6.setVisible(false);
				panel7.setVisible(false);
				panel8.setVisible(false);
				frame.add(updateHome());
				frame.setVisible(true);
			}
		});
		panel.add(home);
		//---------------------
		JButton profile = new JButton("Profile");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
				panel6.setVisible(true);
				panel7.setVisible(false);
				panel8.setVisible(false);
				frame.add(panel6);
				frame.setVisible(true);
			}
		});
		panel.add(profile);
		//--------------------
		JButton post = new JButton("Post");
		post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
				panel6.setVisible(false);
				panel7.setVisible(false);
				panel8.setVisible(false);
				frame.add(panel1);
				frame.setVisible(true);
			}
		});
		panel.add(post);
		//------------------
		JButton myGallery = new JButton("Gallery");
		myGallery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				panel4.setVisible(false);
				panel5.setVisible(false);
				panel6.setVisible(false);
				panel7.setVisible(false);
				panel8.setVisible(false);
				frame.add(updateMyGallery());
				frame.setVisible(true);
			}
		});
		panel.add(myGallery);
		//-------------------
		JButton sendMessage = new JButton("Message");
		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
				panel6.setVisible(false);
				panel7.setVisible(true);
				panel8.setVisible(false);
				frame.add(panel7);
				frame.setVisible(true);
			}
		});
		panel.add(sendMessage);
		//------------------
		JButton sendFeedback = new JButton("fb");
		sendFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(true);
				panel5.setVisible(false);
				panel6.setVisible(false);
				panel7.setVisible(false);
				panel8.setVisible(false);
				frame.add(panel4);
				updateChat();
				frame.setVisible(true);
			}
		});
		panel.add(sendFeedback);
		//---------------------------
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(true);
				panel6.setVisible(false);
				panel7.setVisible(false);
				panel8.setVisible(false);
				frame.add(panel5);
				frame.setVisible(true);
			}
		});
		panel.add(search);
		//--------------------------------
		JButton noti = new JButton("Noti");
		noti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
				panel6.setVisible(false);
				panel7.setVisible(false);
				panel8.setVisible(true);
				updateNoti();
				frame.add(panel8);
				frame.setVisible(true);
			}
		});
		panel.add(noti);
		//--------------------------------
		frame.add(panel);
		
		
		
		//###################### Post image #########################
		//Note: fix user when connect to account
		
		
		panel1.setPreferredSize(new Dimension(550, 500));
		panel1.setLayout(null);
		panel1.setBackground(Color.lightGray);
//		frame.add(panel1);
		
		JLabel imageLabel = new JLabel("Image");
		imageLabel.setBounds(20, 10, 40, 25);
		panel1.add(imageLabel);
		
		JButton selectImageButton = new JButton("Select image");
		selectImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = null;
				JFileChooser chooser = new JFileChooser();
				
				chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				
				FileNameExtensionFilter extension = new FileNameExtensionFilter("*Images",".jpg",".png",".jpeg");
				chooser.addChoosableFileFilter(extension);
				
				int filestate = chooser.showSaveDialog(null);
				
				//check if user select an image
				if(filestate == JFileChooser.APPROVE_OPTION) {
					
					File selectedImage = chooser.getSelectedFile();
					path = selectedImage.getAbsolutePath();
					pathLabel.setText(path);
					image_path = path;
				}
			}
		});
		selectImageButton.setBounds(70, 10, 160, 25);
		panel1.add(selectImageButton);
		
		pathLabel.setBounds(70, 30, 400, 25);
		panel1.add(pathLabel);
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(20, 50, 50, 25);
		panel1.add(titleLabel);
		
		JTextField titleField = new JTextField();
		titleField.setBounds(70, 50, 450, 25);
		panel1.add(titleField);
		
		JLabel cateLabel = new JLabel("Categories");
		cateLabel.setBounds(5, 80, 80, 25);
		panel1.add(cateLabel);
		
		JTextField cateField = new JTextField();
		cateField.setBounds(70, 80, 450, 25);
		panel1.add(cateField);
		
		JLabel desLabel = new JLabel("Description");
		desLabel.setBounds(3, 110, 80, 25);
		panel1.add(desLabel);
		
		JTextArea desArea = new JTextArea();
		desArea.setBounds(70, 110, 450, 200);
		desArea.setLineWrap(true);
		panel1.add(desArea);
		
		JButton upload = new JButton("Upload");
		upload.setBounds(70, 330, 80, 25);
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = titleField.getText();
				String cate = cateField.getText();
				String des = desArea.getText();
				String user = getUser();
				
				if(image_path == null) {
					JOptionPane.showMessageDialog(null, "No image selected!");
					return;
				}
				
				if(Controller.uploadImage(title, cate, des, user, image_path)) {
					JOptionPane.showMessageDialog(null, "Upload success!");
					titleField.setText("");
					cateField.setText("");
					desArea.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Check Content!");
				}
				
			}
		});
		panel1.add(upload);
		
		//############################# HOME ############################
		
		panel2.setPreferredSize(new Dimension(550, 500));
		panel2.setLayout(null);
		panel2.setBackground(Color.lightGray);
		frame.add(panel2);
		
		
		list1.setBounds(20, 10, 200, 25);
		panel2.add(list1);
		
		
		img1.setBounds(10, 40, 170, 170);
		panel2.add(img1);
		

		img2.setBounds(190, 40, 170, 170);
		panel2.add(img2);
		

		img3.setBounds(370, 40, 170, 170);
		panel2.add(img3);
		
		
		list2.setBounds(20, 250, 200, 25);
		panel2.add(list2);
		

		img4.setBounds(10, 280, 170, 170);
		panel2.add(img4);
		

		img5.setBounds(190, 280, 170, 170);
		panel2.add(img5);
		

		img6.setBounds(370, 280, 170, 170);
		panel2.add(img6);

		updateHome();
		
		
		//####################### My Gallery #####################
		
		panel3.setPreferredSize(new Dimension(550, 500));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		panel3.setBackground(Color.lightGray);
//		frame.add(panel3);
		
		//####################### Send Feedback ###################
		
		panel4.setPreferredSize(new Dimension(550, 500));
		panel4.setLayout(null);
		panel4.setBackground(Color.lightGray);
		//frame.add(panel4);
		
		JLabel feedback = new JLabel("This feedback will be send to developer:");
		feedback.setBounds(20, 10, 400, 25);
		panel4.add(feedback);
		
		JTextArea feedbackArea = new JTextArea();
		feedbackArea.setBounds(20, 50, 500, 100);
		feedbackArea.setLineWrap(true);
		panel4.add(feedbackArea);
		
		JButton sendfb = new JButton("Send>>");
		sendfb.setBounds(440, 160, 80, 25);
		sendfb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fb = feedbackArea.getText();
				String user = getUser();
				
				if(fb.equals("") || fb == null) {
					JOptionPane.showMessageDialog(null, "Empty content!");
					return;
				}
				
				FeedbackModel feedback = new FeedbackModel();
				feedback.setContent(fb);
				feedback.setSender(user);
				
				if(Controller.insertFeedback(feedback)) {
					JOptionPane.showMessageDialog(null, "Your feedback has been sent!");
				}else {
					JOptionPane.showMessageDialog(null, "Error: Check your content!");
				}
				
			}
		});
		panel4.add(sendfb);
		
		JLabel globalChat = new JLabel("GLOBAL CHAT");
		globalChat.setBounds(200, 195, 100, 25);
		panel4.add(globalChat);
		
		
		boxChat.setBounds(20, 230, 500, 230);
		boxChat.setAutoscrolls(true);
		boxChat.setEditable(false);
		panel4.add(boxChat);
		updateChat();
		
		JTextField chatField = new JTextField();
		chatField.setBounds(20, 470, 410, 25);
		panel4.add(chatField);
		
		JButton sendChat = new JButton("Send");
		sendChat.setBounds(440, 470, 80, 25);
		sendChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content = chatField.getText();
				String sender = getUser();
				
				ChatModel chat = new ChatModel();
				chat.setContent(content);
				chat.setSender(sender);
				
				if(Controller.insertChat(chat)) {
					updateChat();
					frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Error: Check your content!");
				}
			}
		});
		panel4.add(sendChat);
		
		
		//##################### Search ##############################
		
		panel5.setPreferredSize(new Dimension(550, 500));
		panel5.setLayout(null);
		panel5.setBackground(Color.lightGray);
		//frame.add(panel5);
		
		JLabel searchLabel = new JLabel("Enter category keywords separated by ' ': ");
		searchLabel.setBounds(10, 10, 530, 25);
		panel5.add(searchLabel);
		
		keyword.setBounds(10, 45, 420, 25);
		panel5.add(keyword);
		
		JButton searchKey = new JButton("Search");
		searchKey.setBounds(450, 45, 80, 25);
		searchKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel5.add(updateSearch());
				frame.setVisible(true);
			}
		});
		panel5.add(searchKey);
		
		panel5s.setBounds(10, 90, 530, 400);
		panel5s.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel5s.setBackground(Color.lightGray);
		
		//############################ Profile #####################
		
		panel6.setPreferredSize(new Dimension(550, 500));
		panel6.setLayout(null);
		panel6.setBackground(Color.lightGray);
		//frame.add(panel6);
		
		JButton changePass = new JButton("Change Password");
		changePass.setBounds(60, 10, 200, 25);
		changePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassView cp = new ChangePassView(getUser());
				cp.main(args);
			}
		});
		panel6.add(changePass);
		
		JButton deleteAccount = new JButton("Delete account");
		deleteAccount.setBounds(300, 10, 230, 25);
		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controller.deleteAccount(user)) {
					JOptionPane.showMessageDialog(null, "Deleted!");
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		panel6.add(deleteAccount);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(10, 45, 50, 25);
		panel6.add(emailLabel);
		
		JTextField emailField = new JTextField();
		emailField.setBounds(60, 45, 380, 25);
		panel6.add(emailField);
		
		JButton udEmail = new JButton("Update");
		udEmail.setBounds(450, 45, 80, 25);
		udEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newEmail = emailField.getText();
				if(!newEmail.contains("@")) {
					JOptionPane.showMessageDialog(null, "Invalid email!");
					return;
				}
				
				if(Controller.updateEmail(newEmail, getUser())) {
					JOptionPane.showMessageDialog(null, "Email updated!");
				}else {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		panel6.add(udEmail);
		
		JLabel dobLabel = new JLabel("Dob");
		dobLabel.setBounds(10, 80, 50, 25);
		panel6.add(dobLabel);
		
//		JTextField dobField = new JTextField();
//		dobField.setBounds(60, 80, 380, 25);
//		panel6.add(dobField);
		
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		JFormattedTextField dobField = new JFormattedTextField(format);
		dobField.setBounds(60, 80, 380, 25);
		panel6.add(dobField);
		
		JButton udDob = new JButton("Update");
		udDob.setBounds(450, 80, 80, 25);
		udDob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newDob = dobField.getText();
				
				if(!isValid(newDob)) {
					JOptionPane.showMessageDialog(null, "Enter type 'yyyy-mm-dd' please!");
					return;
				}
				
				

				if(Controller.updateDob(newDob, getUser())) {
					JOptionPane.showMessageDialog(null, "Dob updated!");
				}else {
					JOptionPane.showMessageDialog(null, "Error!");
				}

			}
			
			public boolean isValid(String dateStr) {
		        DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		        sdf.setLenient(false);
		        try {
		            sdf.parse(dateStr);
		        } catch (ParseException e) {
		            return false;
		        }
		        return true;
		    }
		});
		panel6.add(udDob);
		
		JLabel countryLabel = new JLabel("Country");
		countryLabel.setBounds(10, 115, 50, 25);
		panel6.add(countryLabel);
		
		JTextField countryField = new JTextField();
		countryField.setBounds(60, 115, 380, 25);
		panel6.add(countryField);
		
		JButton udCountry = new JButton("Update");
		udCountry.setBounds(450, 115, 80, 25);
		udCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String newCountry = countryField.getText();
				if(newCountry.equals("")) {
					JOptionPane.showMessageDialog(null, "Empty field!");
					return;
				}
				
				if(Controller.updateCountry(newCountry, getUser())) {
					JOptionPane.showMessageDialog(null, "Country updated!");
				}else {
					JOptionPane.showMessageDialog(null, "Error!");
				}
					
			}
		});
		panel6.add(udCountry);
		
		JButton white = new JButton("White Theme");
		white.setBounds(60, 150, 180, 25);
		white.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setBackground(Color.white);
				panel2.setBackground(Color.white);
				panel3.setBackground(Color.white);
				panel4.setBackground(Color.white);
				panel5.setBackground(Color.white);
				panel5s.setBackground(Color.white);
				panel6.setBackground(Color.white);
				panel7.setBackground(Color.white);
				panel8.setBackground(Color.white);
			}
		});
		panel6.add(white);
		
		JButton lightGray = new JButton("LightGray Theme");
		lightGray.setBounds(260, 150, 180, 25);
		lightGray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setBackground(Color.lightGray);
				panel2.setBackground(Color.lightGray);
				panel3.setBackground(Color.lightGray);
				panel4.setBackground(Color.lightGray);
				panel5.setBackground(Color.lightGray);
				panel5s.setBackground(Color.lightGray);
				panel6.setBackground(Color.lightGray);
				panel7.setBackground(Color.lightGray);
				panel8.setBackground(Color.lightGray);
			}
		});
		panel6.add(lightGray);
		
		JLabel oUser = new JLabel("User");
		oUser.setBounds(10, 200, 80, 25);
		panel6.add(oUser);
		
		JTextField userField = new JTextField();
		userField.setBounds(60, 200, 180, 25);
		panel6.add(userField);
		
		JLabel xemail = new JLabel();
		xemail.setBounds(10, 235, 400, 25);
		panel6.add(xemail);
		
		JLabel xdob = new JLabel();
		xdob.setBounds(10, 270, 400, 25);
		panel6.add(xdob);
						
		JLabel xcountry = new JLabel();
		xcountry.setBounds(10, 305, 400, 25);
		panel6.add(xcountry);
		
		JButton userButton = new JButton("View information");
		userButton.setBounds(260, 200, 180, 25);
		userButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet res;
				
				try {
					res = Controller.selectUserByName(userField.getText());
					
					if(res.next()) {
						String email = res.getString("email");
						Date dob = res.getDate("dob");
						String country = res.getString("country");
						
						
						xemail.setText("Email: "+email);
						xdob.setText("Dob: "+dob);
						xcountry.setText("Country: "+country);
						
						//frame.add(panel6);
						frame.setVisible(true);

					}else {
						JOptionPane.showMessageDialog(null, "Not exist user!");
					}
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		panel6.add(userButton);
		
		ResultSet res;
		
		try {
			res = Controller.selectUserByName(getUser());
			
			while(res.next()) {
				String email = res.getString("email");
				Date dob = res.getDate("dob");
				String country = res.getString("country");
				
				
				
				if(email!=null) emailField.setText(email);
//				dobField.setValue(dob);
				if(dob!=null) dobField.setText(dob.toString());
				if(country!=null) countryField.setText(country);

			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		//######################## Message##########################
		
		panel7.setPreferredSize(new Dimension(550, 500));
		panel7.setLayout(null);
		panel7.setBackground(Color.lightGray);
		
		JLabel receiverLabel = new JLabel("Receiver");
		receiverLabel.setBounds(10, 10, 80, 25);
		panel7.add(receiverLabel);
		
		JTextField messField = new JTextField();
		messField.setBounds(100, 10, 100, 25);
		panel7.add(messField);
		
		JTextArea messArea = new JTextArea();
		messArea.setBounds(10, 45, 510, 80);
		messArea.setLineWrap(true);
		panel7.add(messArea);
		
		JButton messButton = new JButton("Send");
		messButton.setBounds(420, 135, 100, 25);
		messButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageModel message = new MessageModel();
				message.setSender(getUser());
				message.setContent(messArea.getText());
				message.setReceiver(messField.getText());
				
				if(Controller.insertMessage(message)) {
					JOptionPane.showMessageDialog(null, "Your message has been sent!");
				}else {
					JOptionPane.showMessageDialog(null, "Error!");
				}
				updateMessage();
			}
		});
		panel7.add(messButton);
		
		updateMessage();
		message.setBounds(10, 170, 530, 320);
		panel7.add(message);
		
		//###################################### Noti ################
		panel8.setPreferredSize(new Dimension(550, 500));
		panel8.setLayout(null);
		panel8.setBackground(Color.lightGray);
		
		updateNoti();
		notiLabel.setBounds(10, 10, 530, 480);
		panel8.add(notiLabel);
		
		
		//##########################################################
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static void updateMessage() {
		ResultSet rs = Controller.selectMessageByReceiver(getUser());
		String s = "";
		try {
			while(rs.next()) {
				s += rs.getString("sender")+": "+rs.getString("content")+"<br>";
			}
			s = "<html>"+s+"</html>";
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		message.setText(s);
	}
	
	public static void updateNoti() {
		ResultSet rs = Controller.selectNotiByReceiver(getUser());
		String s = "";
		try {
			while(rs.next()) {
				s += rs.getString("sender")+" "+rs.getString("content")+"<br>";
			}
			s = "<html>"+s+"</html>";
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		notiLabel.setText(s);
	}
	
	public static void updateChat() {
		ResultSet rs = Controller.selectAllChat();
		String s = "";
		
		try {
			while(rs.next()) {
				s += rs.getString("sender")+": "+rs.getString("content")+"\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		boxChat.setText(s);
	}
}
