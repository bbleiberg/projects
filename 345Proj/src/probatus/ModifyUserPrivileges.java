package probatus;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ModifyUserPrivileges extends JPanel {

	/**
	 * Create the panel.
	 */
	ActiveSession as; 
	Driver driver;
	final JCheckBox addDelete = new JCheckBox("Add Users/Remove Users");
	final JComboBox userNames; 
	
	private void check(Project p, User u)
	{
		if(p != null && p.currentUserHasPriv(u))
		{
			addDelete.setSelected(true);
			addDelete.repaint();
		}
		else
			addDelete.setSelected(false);
	}
	
	public ModifyUserPrivileges(Driver d) {
		driver = d;
		as = d.getActiveSession();
		
		setLayout(null);
		userNames  = new JComboBox(as.getUserNames().toArray());

		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsername.setBounds(45, 67, 127, 14);
		add(lblUsername);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblProject.setBounds(377, 67, 97, 14);
		add(lblProject);
		
		final JComboBox projectNames = new JComboBox();
		projectNames.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				check(as.getProjectbyName(projectNames.getSelectedItem().toString()),as.getUserbyName(userNames.getSelectedItem().toString()));
			}
		});
		projectNames.setBounds(451, 66, 201, 20);
		//comboBox_1.addItem(item);
		add(projectNames);
		
		
		if(userNames.getSelectedIndex() == 0)
		{
			ArrayList<String> projectsByUser = new ArrayList<String>();
			for(Project p: as.getProjects())
			{
				if(p.userHasAccess(as.getUserbyName((String)userNames.getSelectedItem())))
				{
					projectsByUser.add(p.getName());
				}
			}
			projectNames.setModel(new DefaultComboBoxModel(projectsByUser.toArray()));
		}
		userNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> projectsByUser = new ArrayList<String>();
				for(Project p: as.getProjects())
				{
					if(p.userHasAccess(as.getUserbyName((String)userNames.getSelectedItem())))
					{
						projectsByUser.add(p.getName());
					}
				}
				projectNames.setModel(new DefaultComboBoxModel(projectsByUser.toArray()));
			}
		});
		userNames.setBounds(138, 66, 201, 20);
		add(userNames);
		
		JLabel lblPrivileges = new JLabel("Remove Privileges");
		lblPrivileges.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPrivileges.setBounds(288, 279, 186, 14);
		add(lblPrivileges);
		
		if(projectNames.getSelectedItem() != null){
			String projName = projectNames.getSelectedItem().toString();
			Project projHolder = as.getProjectbyName(projName);
			User userHolder = as.getUserbyName(userNames.getSelectedItem().toString());
		
			if(projHolder.currentUserHasPriv(userHolder)){
				addDelete.setSelected(true);
				addDelete.repaint();
			}
		}
		
		
		addDelete.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		addDelete.setBounds(196, 319, 308, 23);
		add(addDelete);
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(addDelete.isSelected())
				{
					userNames.getSelectedItem();
					if(projectNames.getSelectedItem() != null){
						Project temp = as.getProjectbyName(projectNames.getSelectedItem().toString());
						User tempUser= as.getUserbyName(userNames.getSelectedItem().toString());
						temp.giveAddDeletePriv(tempUser);
					//System.out.println("Proj name = " +  temp.getName());
					//System.out.println("User name = " + as.getUserbyName(userNames.getSelectedItem().toString()).getUsername());
					//as.getUserbyName(userNames.getSelectedItem().toString());
					}
				}
			}
		});
		btnSaveChanges.setBounds(196, 349, 136, 38);
		add(btnSaveChanges);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelTo(Driver.GUIPanel.ADMINMENU);
			}
		});
		btnBack.setBounds(616, 561, 106, 38);
		add(btnBack);

	}
}
