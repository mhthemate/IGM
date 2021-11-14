package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import Controller.Controller;

public class ChangePassView {
private static String user;
	
	public ChangePassView(String user) {
		this.user = user;
	}
	
	public static boolean checkPass(String pass) {
		ResultSet rs;
		boolean truePass = false;

		try {
			rs = Controller.selectUserByName(user);
			
			if(rs.next()) {
				String password = rs.getString("password");
				
				if(password.equals(pass)) truePass = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return truePass;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(300, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel oldPass = new JLabel("Old Pass");
		oldPass.setBounds(10, 20, 80, 25);
		panel.add(oldPass);
		
		JPasswordField oldPassField = new JPasswordField();
		oldPassField.setBounds(100, 20, 165, 25);
		panel.add(oldPassField);
		
		JLabel newPass = new JLabel("New Pass");
		newPass.setBounds(10, 55, 80, 25);
		panel.add(newPass);
		
		JPasswordField newPassField = new JPasswordField();
		newPassField.setBounds(100, 55, 165, 25);
		panel.add(newPassField);
		
		JLabel cfPass = new JLabel("Confirm Pass");
		cfPass.setBounds(10, 90, 80, 25);
		panel.add(cfPass);
		
		JPasswordField cfPassField = new JPasswordField();
		cfPassField.setBounds(100, 90, 165, 25);
		panel.add(cfPassField);
		
		JButton changePass = new JButton("Change Pass");
		changePass.setBounds(10, 125, 120, 25);
		changePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPass = String.valueOf(oldPassField.getPassword());
				String newPass = String.valueOf(newPassField.getPassword());
				String cfPass = String.valueOf(cfPassField.getPassword());
				
				if(checkPass(oldPass)) {
					if(!newPass.equals(cfPass)) {
						JOptionPane.showMessageDialog(null, "Diferent new Passwords!");
						return;
					}
					if(newPass.equals("")) {
						JOptionPane.showMessageDialog(null, "Empty password!");
						return;
					}

					ResultSet rs;
					
						if(Controller.updatePassword(newPass, user)) {
							JOptionPane.showMessageDialog(null, "Your password has been changed!");
							frame.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Error: Check your infor!");
						}
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect old password!");
				}
				
			}
		});
		panel.add(changePass);
		
		
		frame.setVisible(true);
	}
}
