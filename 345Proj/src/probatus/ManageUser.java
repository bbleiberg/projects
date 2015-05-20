package probatus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;

import javax.swing.JList;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ManageUser extends JFrame {

	private ActiveSession as;
	private Driver driver;
	private static JPanel contentPane;
	private static JList listnames;
	private static DefaultListModel names;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManageUser frame = new ManageUser();
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
	public ManageUser(Driver d) {
		driver = d;
		as = d.getActiveSession();
		
		setTitle("Manage User");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 789, 645);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		listnames = new JList(new DefaultListModel());
		names = (DefaultListModel)listnames.getModel();
		names.addListDataListener(new MyListDataListener());
		
		ArrayList<User> users=as.getUsers(); // Populates ArrayList with users loaded from active session.
		for(int i=0; i<as.getUsers().size(); i++){
			if(users.get(i).isActive())
				names.addElement(users.get(i).getUsername()); // Adds to users to the Jlist part of the gui
			else
				System.out.println(users.get(i).getUsername()); // Prints to the console
		}
		
		listnames.setBounds(10, 11, 367, 516);
		listnames.addListSelectionListener(new MyListSelectionListener());
		contentPane.add(listnames);
		
		JPanel panel = new JPanel();
		panel.setBounds(396, 11, 367, 516);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelTo(Driver.GUIPanel.ADDUSER);
			}
		});
		btnNewButton.setBounds(10, 11, 106, 46);
		panel.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(10, 68, 106, 46);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println(listnames.getSelectedIndex());
				
				int responce = JOptionPane.showConfirmDialog(contentPane, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
				
				if(responce == JOptionPane.YES_OPTION){
					if(as.getActiveUser().getUsername() != listnames.getSelectedValue().toString() && listnames.getSelectedIndex()>=0)
					{	
						User target = as.getUserbyName(listnames.getSelectedValue().toString());
						//System.out.println(target.getUsername());
						names.remove(listnames.getSelectedIndex());
						target.deactivate();     
					}
					else
						System.out.println("You can't delete yourself!");
				}
			}
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelTo(Driver.GUIPanel.ADMINMENU);
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

	public static void addUserToList(String userName){
		names.addElement(userName);
	}
	
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return contentPane;
	}
}
