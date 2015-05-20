/**
 * 
 */
package probatus;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

/**
 * @author Ben Bleiberg
 *
 */
public class NewProjectScreen extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	ActiveSession as;
	Driver driver;
	
	public NewProjectScreen(Driver d) {
		driver = d;
		as = d.getActiveSession();
		
		setLayout(null);
		
		JLabel lblCreateNewProject = new JLabel("Create New Project");
		lblCreateNewProject.setBounds(30, 38, 160, 38);
		lblCreateNewProject.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblCreateNewProject);
		
		JLabel lblEnterProjectName = new JLabel("Enter Project Name: ");
		lblEnterProjectName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEnterProjectName.setBounds(30, 82, 234, 38);
		add(lblEnterProjectName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(246, 93, 451, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEnterSupervisorsName = new JLabel("Enter Supervisor(s) Name: ");
		lblEnterSupervisorsName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEnterSupervisorsName.setBounds(30, 131, 234, 38);
		add(lblEnterSupervisorsName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(246, 142, 451, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEnterTeamMembers = new JLabel("Enter Team Member(s) Name: ");
		lblEnterTeamMembers.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEnterTeamMembers.setBounds(30, 180, 234, 38);
		add(lblEnterTeamMembers);
		
		textField_3 = new JTextField();
		textField_3.setBounds(246, 191, 451, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEnterSqadManagers = new JLabel("Enter SQAO Manager(s) Name: ");
		lblEnterSqadManagers.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEnterSqadManagers.setBounds(30, 229, 234, 38);
		add(lblEnterSqadManagers);
		
		textField_4 = new JTextField();
		textField_4.setBounds(246, 240, 451, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNoteEachAdditional = new JLabel("Note: Each additional name must be separated by a comma. ");
		lblNoteEachAdditional.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNoteEachAdditional.setBounds(30, 278, 354, 14);
		add(lblNoteEachAdditional);
		
		JLabel lblNewLabel = new JLabel("A project may only have one name. ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(30, 297, 354, 14);
		add(lblNewLabel);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(0, 0, 0, 0);
		add(verticalStrut);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelTo(Driver.GUIPanel.MANAGEPROJECTS);
			}
		});
		btnBack.setBounds(616, 561, 106, 38);
		add(btnBack);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBounds(591, 278, 106, 38);
		add(btnSubmit);
		
		final JLabel lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblError.setBounds(30, 337, 411, 117);
		add(lblError);
		//clicking on the mouse will create
		btnSubmit.addMouseListener(new MouseAdapter(){
			
			public void mouseClicked(MouseEvent e){
				String error = "<html>";
				if(as.projectExists(textField_1.getText()))
				{
					error = error + "Project already exists<br>";
				}
				if(textField_1.getText().equals(""))
				{
					error = error + "You forgot to enter Project Name<br>";
				}
				if(textField_2.getText().equals(""))
				{
					error = error + "You forgot to enter Supervisor(s) Name<br>";
				}
				if(textField_3.getText().equals(""))
				{
					error = error + "You forgot to enter Team Member(s) Name<br>";
				}
				if(textField_4.getText().equals(""))
				{
					error = error + "You forgot to enter SQAO Manager(s)<br>";
				}
				
				if(!as.projectExists(textField_1.getText()))
				{
					String tokens[] = textField_3.getText().split(",");
					int counter = 0;
					boolean foundInvalidUser = false;
					while(counter < tokens.length)
					{
						if(!as.userExists(tokens[counter]) && !textField_3.getText().equals(""))
						{
							error = error + tokens[counter] + ", ";
							foundInvalidUser = true;
						}
						if(as.userExists(tokens[counter]))
						{
							if(!as.getUserbyName(tokens[counter]).isActive())
							{
								error = error + tokens[counter] + ", ";
								foundInvalidUser = true;
							}
						}
						counter++;
					}
					
					if(foundInvalidUser)
					{
						error = error + " Not valid username(s) <br>";
					}
					if(error.equals("<html>"))
					{
						Project p1 = new Project(textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText());
						as.projects.add(p1);
						counter = 0;
						
						while(counter < tokens.length)
						{
							p1.addUser(as.getUserbyName(tokens[counter]));
							counter++;
						}	
						driver.changePanelTo(Driver.GUIPanel.MANAGEPROJECTS);
						System.out.println("Project Added!");
					}
							
				}
				
				error = error + "</html>";
				lblError.setText(error);
				lblError.repaint();	
			}
			
			
		});

	
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
