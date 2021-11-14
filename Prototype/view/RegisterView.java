package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Controller;

public class RegisterView {
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JLabel confpass;
	private static JPasswordField passwordText;
	private static JPasswordField repassText;
	private static JButton button;
	private static JLabel success;
	private static JLabel lbutton;
	
	public static void showMessage(String smg) {
		JOptionPane.showMessageDialog(null, smg);
	}
	
	public static boolean validateInput() {
		String uname = userText.getText();
		String pass1 = String.valueOf(passwordText.getPassword());
		String pass2 = String.valueOf(repassText.getPassword());
		
		if(uname.trim().equals("") || pass1.trim().equals("") || pass2.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Empty field(s) exist!","Empty Field",2);
			return false;
		}else if(!pass1.equals(pass2)){
			JOptionPane.showMessageDialog(null, "Difference Password!", "Error Pass", 2);
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean checkUsername(String username) {
		ResultSet rs = Controller.selectUserByName(username);
		boolean check = true;
		try {
			if(rs.next()) {
				check = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static void getUserInfo() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(300, 250);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		confpass = new JLabel("Re-Password");
		confpass.setBounds(10, 80, 80, 25);
		panel.add(confpass);
		
		repassText = new JPasswordField();
		repassText.setBounds(100, 80, 165, 25);
		panel.add(repassText);
		
		button = new JButton("Register");
		button.setBounds(10, 110, 150, 25);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = userText.getText();
				String pass1 = String.valueOf(passwordText.getPassword());
				String pass2 = String.valueOf(repassText.getPassword());
				
				if(checkUsername(username)) {
					if(validateInput()) {
						if(Controller.registerUser(username,pass1)) {
							showMessage("Register success!");
							frame.dispose();
							LoginView.getUserInfo();
						}else {
							showMessage("Error!");
						}
					}
				}else {
					showMessage("Existed username!");
				}
			}
		});
		panel.add(button);
		
		lbutton = new JLabel("Login");
		lbutton.setBounds(10, 140, 80, 15);
		lbutton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				LoginView.getUserInfo();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				lbutton.setText("Press");
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				lbutton.setText("Release");
			}
		});
		panel.add(lbutton);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		frame.setVisible(true);
	}
}
