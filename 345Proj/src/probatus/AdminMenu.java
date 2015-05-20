package probatus;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminMenu extends JFrame {

	private JPanel contentPane;
	ActiveSession as;
	Driver driver;
	
	public JPanel getContentPanel() {
		return contentPane;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminMenu frame = new AdminMenu();
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
	public AdminMenu(Driver d) {
		driver = d;
		as = d.getActiveSession();
		
		setTitle("Administrator Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 795, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		setContentPane(contentPane);
		
		JButton btnManageUsers = new JButton("Manage Users");
		btnManageUsers.setBounds(136, 82, 421, 65);
		btnManageUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				driver.changePanelTo(Driver.GUIPanel.MANAGEUSER);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnManageUsers);
		
		JButton btnManageProjects = new JButton("Manage Projects");
		btnManageProjects.setBounds(136, 177, 421, 65);
		btnManageProjects.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelTo(Driver.GUIPanel.MANAGEPROJECTS);
			}
		});
		contentPane.add(btnManageProjects);
		
		JButton btnModifyUserPrivileges = new JButton("Modify User Privileges");
		btnModifyUserPrivileges.setBounds(136, 273, 421, 65);
		btnModifyUserPrivileges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelTo(Driver.GUIPanel.MODIFYUSERPRIVILEGES);
			}
		});
		contentPane.add(btnModifyUserPrivileges);
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBounds(616, 561, 106, 38);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.saveApplication();
				driver.logInAgain();
			}
		});
		contentPane.add(btnLogout);
	}

}
