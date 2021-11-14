package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.Controller;
import Model.ImageModel;

public class EditImageView {
	private static ImageModel image;
	
	public EditImageView(ImageModel image) {
		this.image = image;
	}
	
	public static void getData() {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.lightGray);
		
		JLabel title = new JLabel("Title");
		title.setBounds(10, 10, 80, 25);
		panel.add(title);
		
		JTextField titleField = new JTextField(image.getTitle());
		titleField.setBounds(100, 10, 280, 25);
		panel.add(titleField);
		
		JLabel cate = new JLabel("Categories");
		cate.setBounds(10, 45, 80, 25);
		panel.add(cate);
		
		JTextField cateField = new JTextField(image.getCategories());
		cateField.setBounds(100, 45, 280, 25);
		panel.add(cateField);
		
		JLabel des = new JLabel("Description");
		des.setBounds(10, 80, 80, 25);
		panel.add(des);
		
		JTextArea desArea = new JTextArea(image.getDescription());
		desArea.setBounds(100, 80, 280, 200);
		desArea.setLineWrap(true);
		panel.add(desArea);
		
		JButton update = new JButton("Update");
		update.setBounds(300, 300, 80, 25);
		update.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = titleField.getText();
				String cate = cateField.getText();
				String des = desArea.getText();
				
				if(title.equals("")||cate.equals("")||des.equals("")) {
					JOptionPane.showMessageDialog(null, "Empty field(s)!");
				}else {
					if(Controller.updateImage(title,cate,des,Integer.toString(image.getId()))) {
						JOptionPane.showMessageDialog(null, "Updated!");
						frame.dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Error!");
					}
				}
			}
		});
		panel.add(update);
		
		JButton close = new JButton("Close");
		close.setBounds(200, 300, 80, 25);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(close);
		
		frame.setVisible(true);
	}
}
