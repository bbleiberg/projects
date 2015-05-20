package probatus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingConstants;

import java.awt.Color;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField pass;
	private JPasswordField passConfirm;
	private JTextField txtProject;
	private ActiveSession as;
	private Driver driver;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddUser frame = new AddUser();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public AddUser(Driver d) {
		driver = d;
		as = d.getActiveSession();
		
		setTitle("Add User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewUserName = new JLabel("New User Name:");
		lblNewUserName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewUserName.setBounds(21, 43, 282, 20);
		panel.add(lblNewUserName);
		
		textField = new JTextField();
		textField.setBounds(234, 45, 251, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(21, 74, 282, 20);
		panel.add(lblNewLabel);
		
		pass = new JPasswordField();
		pass.setBounds(234, 76, 251, 20);
		panel.add(pass);
		pass.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblConfirmPassword.setBounds(21, 105, 282, 20);
		panel.add(lblConfirmPassword);
		
		passConfirm = new JPasswordField();
		passConfirm.setBounds(234, 105, 251, 20);
		panel.add(passConfirm);
		passConfirm.setColumns(10);
		
		JLabel lblAsso = new JLabel("Associated Projects:");
		lblAsso.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAsso.setBounds(21, 136, 282, 20);
		panel.add(lblAsso);
		
		final JComboBox comboBox = new JComboBox(as.getProjectNames().toArray());
		comboBox.setBounds(234, 136, 251, 20);
		panel.add(comboBox);
		
		final ArrayList<String> projectsToAddPerm = new ArrayList<String>();
		JButton btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(comboBox.getItemCount() != 0)
				{	
					projectsToAddPerm.add((String)comboBox.getSelectedItem());
				}
			}
		});
		btnAdd.setBounds(518, 137, 65, 23);
		panel.add(btnAdd);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(37, 256, 649, 238);
//		panel.add(layeredPane);
		
		txtProject = new JTextField();
		txtProject.setText("Project 1");
		txtProject.setBounds(10, 38, 109, 20);
		layeredPane.add(txtProject);
		txtProject.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		rdbtnNewRadioButton.setBounds(166, 38, 86, 23);
		layeredPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		rdbtnNewRadioButton_1.setBounds(331, 38, 86, 23);
		layeredPane.add(rdbtnNewRadioButton_1);
		
		JLabel lblProjects = new JLabel("Projects:");
		lblProjects.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblProjects.setBounds(10, 10, 109, 17);
		layeredPane.add(lblProjects);
		
		JLabel lblPrivlidge = new JLabel("Privlege: add users");
		lblPrivlidge.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPrivlidge.setBounds(129, 16, 145, 14);
		layeredPane.add(lblPrivlidge);
		
		JLabel lblPrivlegeDeleteUsers = new JLabel("Privlege: Delete Users");
		lblPrivlegeDeleteUsers.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPrivlegeDeleteUsers.setBounds(284, 16, 133, 14);
		layeredPane.add(lblPrivlegeDeleteUsers);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(437, 38, 28, 23);
		layeredPane.add(chckbxNewCheckBox);
		
		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblRemove.setBounds(427, 16, 145, 14);
		layeredPane.add(lblRemove);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelTo(Driver.GUIPanel.MANAGEUSER);
			}
		});
		btnBack.setBounds(616, 561, 106, 38);
		panel.add(btnBack);
		
		final JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setBounds(21, 171, 346, 74);
		panel.add(lblError);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String error = "<html>";
				//must be an admin if here...
				if(as.userExists(textField.getText()))
				{
					if(as.userExistsInactive(textField.getText()))
					{
						error = error + "User name was previously used<br>";
					}
					else
					error = error + "User already exists<br>";
				}
				/*if(textField.getText().equals(""))
				{
					error = error + "Invalid Username<br>";
				}*/
				if(!Arrays.equals(pass.getPassword(), passConfirm.getPassword()))
				{
					error = error + "Passwords do not match<br>";
				}
				/*if(pass.getPassword().length == 0 || pass.getPassword().length == 0 )
				{
					error = error + "Invalid Password<br>";
				}*/
				if(textField.getText().length() < 6 || textField.getText().length() > 20)
				{
					error = error + "Username must be 6-20 characters long<br>";
				}
				if(pass.getPassword().length < 6 || pass.getPassword().length > 20 || passConfirm.getPassword().length < 6 || passConfirm.getPassword().length > 20)
				{
					error = error + "Password must be 6-20 characters long<br>";
				}
				if(!as.userExists(textField.getText()) && Arrays.equals(pass.getPassword(), passConfirm.getPassword()))
				{
					if(pass.getPassword().length >= 6 && pass.getPassword().length <= 20 && textField.getText().length() >= 6 && passConfirm.getPassword().length <= 20 && passConfirm.getPassword().length >= 6)
					{
						User newUser = new User(textField.getText(), new String(pass.getPassword()));
						as.addUser(newUser);
						User user = as.getUserbyName(newUser.getUsername());
						user.activate();
						
						for(String s : projectsToAddPerm)
						{
							as.getProjectbyName(s).addUser(newUser); //adds User to each project specified to add too.
						}
						
						driver.changePanelTo(Driver.GUIPanel.MANAGEUSER);
			
						System.out.println("User Added!");
					}
				}
				error = error + "</html>";
				lblError.setText(error);
				lblError.repaint();
			}
		});
		btnSubmit.setBounds(0, 561, 106, 38);
		panel.add(btnSubmit);
		
	}
	
	public JPanel getPanel(){
		return contentPane;
	}
}
