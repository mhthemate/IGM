package View;

import java.awt.HeadlessException;
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
import javax.swing.JTextField;

import Controller.Controller;

public class ForgotPassView {
	private static JFrame frame = new JFrame();
	public static void checkUser(String user) {
		ResultSet rs;

		rs = Controller.selectUserByName(user);
			
		try {
			if(rs.next()) {
				String password = rs.getString("password");
				JOptionPane.showMessageDialog(null, "Your password: "+password);
				frame.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Not exist user!");
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getPass() {
		frame.setSize(300, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel username = new JLabel("Username");
		username.setBounds(10, 20, 80, 25);
		panel.add(username);
		
		JTextField userField = new JTextField();
		userField.setBounds(100, 20, 165, 25);
		panel.add(userField);
		
		JButton getPass = new JButton("Get password");
		getPass.setBounds(100, 55, 120, 25);
		getPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkUser(userField.getText());
			}
		});
		panel.add(getPass);
		
		frame.setVisible(true);
	}
}
