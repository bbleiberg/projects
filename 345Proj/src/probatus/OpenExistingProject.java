package probatus;

//import javax.media.nativewindow.util.Dimension;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ScrollPaneConstants;

public class OpenExistingProject extends JPanel {

	ArrayList<JButton> buttonList = new ArrayList<JButton>();
	Driver driver;
	ActiveSession as;
	/**
	 * Create the panel.
	 */
	public OpenExistingProject(Driver d) {
		driver = d;
		as = d.getActiveSession();
		setLayout(null);
		
		JLabel lblOpenExistingProject = new JLabel("Open Existing Project");
		lblOpenExistingProject.setBounds(294, 27, 176, 20);
		lblOpenExistingProject.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(lblOpenExistingProject);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.saveApplication();
				driver.logInAgain();
			}
		});
		btnLogout.setBounds(616, 561, 106, 38);
		add(btnLogout);
		
		
		
		JPanel panel = new JPanel();
		
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(85, 69, 578, 481);
		add(scrollPane);
		
				
		/*JButton btnNewButton = new JButton("1. 345 Project 2");
		
		btnNewButton.setBounds(20, 21, 536, 50);
		
		JButton btnGame = new JButton("2. Game 2");
		btnGame.setBounds(20, 82, 536, 50);
		btnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				driver.changePanelToProjectStatus(new Project("test"));
			}
		});
		scrollPanel.setLayout(null);
		scrollPanel.add(btnGame);
		scrollPanel.add(btnNewButton);
		*/
		
		for(final Project p : as.getProjects())
		{
			if(p.userHasAccess(as.getActiveUser()))
			{
				if(p.isActive())
				{
					//System.out.println(counter);
					JButton button = new JButton(p.getName());
					//button.setBounds(20, 82, 536, 50);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							driver.changePanelToProjectStatus(p);
						}
				});
					buttonList.add(button);
				}
				
			}
			
		}
		
		int counter = 0;
		for(JButton j: buttonList)
		{
			j.setBounds(20, 22+counter, 536, 50);
			panel.setLayout(null);
			panel.add(j);
			invalidate();
			panel.setPreferredSize(new Dimension(200,150+counter));
			scrollPane.validate();
			counter = counter + 60;
		}
		
		
	}
}
