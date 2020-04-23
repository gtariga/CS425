package finalProjectCS425;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {
	
	
	Connection connect =null;
	
	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	

	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connect = functions.connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBounds(100, 100, 495, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(0, 95, 146, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 172, 146, 43);
		frame.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(147, 94, 324, 43);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(147, 172, 324, 43);
		frame.getContentPane().add(password);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				
				
				
			try {
				
				String query = "select username, password from employee where username=? and password=?";
				
				PreparedStatement myStmt = connect.prepareStatement(query);/*This is the line we are going to use if the user is actually an employee that is registered in the database*/
				
				myStmt.setString(1, username.getText());/*parameter for checking the username*/
				
				myStmt.setString(2, password.getText());/*parameter for checking the password*/
				
				ResultSet result = myStmt.executeQuery(); /*stores the result of the query*/
				
				int count = 0; /*will be used to check if the user entered is actually valid*/
						
				while(result.next()) {
					
					count++;
					
				}				
				
				if (count==1) {
					/*opens the menu inetrface after succesful login*/
					frame.dispose();
					EmployeeMenu menu = new EmployeeMenu();
					menu.setVisible(true);
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
					
				}
				
				result.close();
				myStmt.close();
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}

				
				
			}
		});
		login.setBackground(Color.BLACK);
		login.setFont(new Font("Tahoma", Font.BOLD, 23));
		login.setBounds(196, 277, 113, 53);
		frame.getContentPane().add(login);
		
		
	}
}
