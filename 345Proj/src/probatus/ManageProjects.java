package probatus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ManageProjects extends JFrame {

	
	private ActiveSession as;
	private static JPanel contentPane;
	Driver driver;
	private static JList projectNames;
	private static DefaultListModel projects;
	
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
//					ManageProjects frame = new ManageProjects();
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
	public ManageProjects(Driver d) {
		driver = d;
		as = d.getActiveSession();
		
		setTitle("Manage Projects");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 673);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		projectNames = new JList(new DefaultListModel());
		projects = (DefaultListModel)projectNames.getModel();
		projects.addListDataListener(new MyListDataListener());
		
		ArrayList<Project> projs = as.getProjects();
		for(int i = 0; i < as.getProjects().size(); i++){
			if(projs.get(i).isActive())
			{
				projects.addElement(projs.get(i).getName());
			}
			else
				System.out.println(projs.get(i).getName());
		}
		
		projectNames.setBounds(10, 11, 546, 517);
		projectNames.addListSelectionListener(new MyListSelectionListener());
		contentPane.add(projectNames);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				driver.changePanelTo(Driver.GUIPanel.NEWPROJECTSCREEN);
			}
		});
		btnCreate.setBounds(580, 68, 106, 46);
		contentPane.add(btnCreate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!projectNames.isSelectionEmpty())
				{
					int response = JOptionPane.showConfirmDialog(contentPane, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
				

					if(response == JOptionPane.YES_OPTION){
						System.out.println(projectNames.getSelectedIndex());
						if(as.getActiveUser().getUsername() != projectNames.getSelectedValue().toString() && projectNames.getSelectedIndex()>=0) //first part is bug
						{	
							Project target = as.getProjectbyName(projectNames.getSelectedValue().toString());
							//System.out.println(target.getUsername());
							projects.remove(projectNames.getSelectedIndex());
							target.deactivate();     }
						else
							System.out.println("Can't delete Active User!");
				}
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(580, 125, 106, 46);
		contentPane.add(btnDelete);
		
		JButton btnAddremoveUser = new JButton("<html>Add/Remove<br />Users</html>");
		btnAddremoveUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Project p = as.getProjectbyName((String)projectNames.getSelectedValue());
				if(p  != null){
					driver.changePanelToMPU(p);
				}
			}	
		});
		btnAddremoveUser.setBounds(580, 182, 106, 46);
		contentPane.add(btnAddremoveUser);
		btnAddremoveUser.setLayout(new BorderLayout());
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelTo(Driver.GUIPanel.ADMINMENU);
			}
		});
		btnBack.setBounds(616, 561, 106, 38);
		contentPane.add(btnBack);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Project p = as.getProjectbyName((String)projectNames.getSelectedValue());
				if(p  != null){
					driver.changePanelToProjectStatus(p); //PanelTo(Driver.GUIPanel.PROJECTSTATUS);
				}
			}
		});
		btnOpen.setBounds(580, 11, 106, 46);
		contentPane.add(btnOpen);
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

	public static void addProjectToList(String projectName){
		projects.addElement(projectName); 
	}

}
