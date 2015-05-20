package probatus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ManageProjectUsers extends JFrame {

	private static JPanel contentPane;
	ActiveSession as;
	Driver driver;
	private static JList listnames;
	private static DefaultListModel names;
	Project currentProject;
	
	
	public JPanel getPanel() {
		return contentPane;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManageProjectUsers frame = new ManageProjectUsers();
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
	public ManageProjectUsers(Driver d, Project p) {
		driver = d;
		as = d.getActiveSession();
		currentProject = p;
		
		setTitle("Manage Project: Project Title");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listnames = new JList(new DefaultListModel());
		names = (DefaultListModel)listnames.getModel();
		names.addListDataListener(new MyListDataListener());
		
		for(User u: currentProject.getUserList()){
				names.addElement(u.getUsername()); // Adds to users to the Jlist part of the gui
		}
		
		listnames.setBounds(10, 11, 469, 463);
		listnames.addListSelectionListener(new MyListSelectionListener());
		contentPane.add(listnames);
		
		final JComboBox userNameBox = new JComboBox(as.getUserNames().toArray());
		userNameBox.setBounds(489, 47, 233, 25);
		contentPane.add(userNameBox);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!currentProject.userHasAccess(as.getUserbyName(userNameBox.getSelectedItem().toString())))
				{
					currentProject.addUser(as.getUserbyName(userNameBox.getSelectedItem().toString()));
					names.addElement(as.getUserbyName(userNameBox.getSelectedItem().toString()).getUsername());
					currentProject.addTeamMems(userNameBox.getSelectedItem().toString());
				}
			}
		});
		btnAdd.setBounds(489, 11, 106, 25);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(as.userExists(listnames.getSelectedValue().toString())){
					currentProject.removeUser(as.getUserbyName(listnames.getSelectedValue().toString()));
					currentProject.deleteTeamMems(listnames.getSelectedValue().toString());
					names.remove(listnames.getSelectedIndex());
				}
			}
		});
		btnRemove.setBounds(489, 449, 106, 25);
		contentPane.add(btnRemove);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(driver.getActiveSession().isCurrentUserAdmin())
					driver.changePanelTo(Driver.GUIPanel.MANAGEPROJECTS);
				else
					driver.changePanelToProjectStatus(currentProject);
			}
		});
		btnBack.setBounds(616, 561, 106, 38);
		contentPane.add(btnBack);
		
	}

	class MyListDataListener implements ListDataListener {
		public void contentsChanged(ListDataEvent e) {
			
		}
		public void intervalAdded(ListDataEvent e) {
		}
		public void intervalRemoved(ListDataEvent e) {
		}
	}
	
	class MyListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			//ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		}
	}
	public static void addProjectToList(User userName){
		names.addElement(userName); 
	}
}
