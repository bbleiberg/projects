package probatus;

//version 0.5


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;



public class Driver extends JFrame implements Serializable, WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//if true, makes editor window block the program and wait too be closed before allowing user to interact with the program again
	//but the textEdit class must be changed back to a JFrame if this is false
	public static boolean easyMode = false;
	
	public enum GUIPanel {ADDUSER, ADMINMENU, MANAGEPROJECTS, MANAGEPROJECTUSERS, MANAGEUSER, MODIFYUSERPRIVILEGES,
		NEWPROJECTSCREEN, OPENEXISTINGPROJECT, REPORT, PROJECTSTATUS}
	
	private ActiveSession currSess;
	private JPanel contentHolder; 
	private final String programName = "Probatus";
	private final String fileDirectoryPath = "ProbatusFiles/";
	private JPanel currentPanel;
	public static ArrayList<TextEdit> openEditors = new ArrayList<TextEdit>(); 
	
	//TODO create standard frame size
	Dimension standardWindowSize = new Dimension(750, 650);
	
	public Driver(){
		currSess = new ActiveSession(this);
		
		
		/*test data*/
		User testUser = new Admin("ben", "ben");
		//User testUser2 = new Admin("Admin1234", "Admin1234");
		currSess.addUser(testUser);
		//currSess.addUser(testUser2);
		
		//everything other than starting this frame happens here. starting the frame is in main.
		//set look and feel, window size, set exit on close
		setSize(standardWindowSize);
		contentHolder = new JPanel();
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		contentHolder.setLayout(new CardLayout());
		
		getContentPane().add(contentHolder, BorderLayout.CENTER);
		setTitle(programName);
		
		//TODO add a confirmation dialog for closing
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		
		//read in saved arraylists if any exist or create a new activeSession if no save data found
		try{
			setUpFileSystem();
			
			FileInputStream fin = new FileInputStream("data/currentSession.ser");
		    ObjectInputStream ois = new ObjectInputStream(fin);
		    
			currSess = (ActiveSession) ois.readObject();
			currSess.setDriverObject(this);
			
		    ois.close();
 
	   	}catch(EOFException e){
	   		System.out.println("no files to load.");
	   	}
		catch(Exception ex){
	   		ex.printStackTrace();
//	   		System.out.println("no files to load");
	   	}

		//actually show the JFrame
		setVisible(true);

		//create and display the log in window
		LogInWindow dialog = new LogInWindow(this);
		if(dialog.showDialog()){
			currSess.afterLoginSuccess();
		}
		else{
			System.out.println("login failed");
			System.exit(0);
		}
		
		addWindowListener(this);
	}
	
	private void setUpFileSystem(){
		File inputFile = new File("data/currentSession.ser");
		if(!inputFile.exists()){
			if(!inputFile.getParentFile().exists()){
				inputFile.getParentFile().mkdir();
			}
			try {
				inputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("error creating currentSession.ser file.");
			}
		}
	}
	
	public void changePanelTo(GUIPanel panelName){
		CardLayout c1 = (CardLayout)contentHolder.getLayout();
		
		switch(panelName){
		case ADDUSER: 
			
			contentHolder.add(new AddUser(this).getPanel(), GUIPanel.ADDUSER.toString());
			setTitle(programName + " - Add New User");
			c1.show(contentHolder, GUIPanel.ADDUSER.toString());
			break;
		case ADMINMENU:
			contentHolder.add(new AdminMenu(this).getContentPanel(), GUIPanel.ADMINMENU.toString());
			setTitle(programName + " - Administrator Menu");
			c1.show(contentHolder, GUIPanel.ADMINMENU.toString());
			break;
		case MANAGEPROJECTS:
			contentHolder.add(new ManageProjects(this).getPanel(), GUIPanel.MANAGEPROJECTS.toString());
			setTitle(programName + " - Manage Projects");
			c1.show(contentHolder, GUIPanel.MANAGEPROJECTS.toString());
			break;
//		case MANAGEPROJECTUSERS:
//			contentHolder.add(new ManageProjectUsers(this).getPanel(), GUIPanel.MANAGEPROJECTUSERS.toString());
//			setTitle(programName + " - Manage Project Users");
//			c1.show(contentHolder, GUIPanel.MANAGEPROJECTUSERS.toString());
//			break;
		case MANAGEUSER:
			contentHolder.add(new ManageUser(this).getPanel(), GUIPanel.MANAGEUSER.toString());
			setTitle(programName + " - Manage Users");
			c1.show(contentHolder, GUIPanel.MANAGEUSER.toString());
			break;
		case MODIFYUSERPRIVILEGES:
			contentHolder.add(new ModifyUserPrivileges(this), GUIPanel.MODIFYUSERPRIVILEGES.toString());
			setTitle(programName + " - Modify User Privileges");
			c1.show(contentHolder, GUIPanel.MODIFYUSERPRIVILEGES.toString());
			break;
		case NEWPROJECTSCREEN:
			contentHolder.add(new NewProjectScreen(this), GUIPanel.NEWPROJECTSCREEN.toString());
			setTitle(programName + " - Create New Project");
			c1.show(contentHolder, GUIPanel.NEWPROJECTSCREEN.toString());
			break;
		case OPENEXISTINGPROJECT:
			contentHolder.add(new OpenExistingProject(this), GUIPanel.OPENEXISTINGPROJECT.toString());
			setTitle(programName + " - Open Existing Project");
			c1.show(contentHolder, GUIPanel.OPENEXISTINGPROJECT.toString());
			break;
//		case REPORT:
//			contentHolder.add(new Report(this), GUIPanel.REPORT.toString());
//			setTitle(programName + " - Project Report");
//			c1.show(contentHolder, GUIPanel.REPORT.toString());
		}
		
		contentHolder.revalidate();		
	}
	
	public void changePanelToMPU(Project project){
		CardLayout c1 = (CardLayout)contentHolder.getLayout();
		
		setTitle(programName + " - Manage Project Users");
		contentHolder.add(new ManageProjectUsers(this, project).getPanel(), GUIPanel.MANAGEPROJECTUSERS.toString());
		c1.show(contentHolder, GUIPanel.MANAGEPROJECTUSERS.toString());
		
		contentHolder.revalidate();
	}
	
	public void changePanelToProjectStatus(Project project){
		CardLayout c1 = (CardLayout)contentHolder.getLayout();
		
		setTitle(programName + " - Project Status");
		contentHolder.add(new ProjectStatus(this, project), GUIPanel.PROJECTSTATUS.toString());
		c1.show(contentHolder, GUIPanel.PROJECTSTATUS.toString());
		
		contentHolder.revalidate();
	}
	
	public void changePanelToReport(Project project){
		CardLayout c1 = (CardLayout)contentHolder.getLayout();
		
		setTitle(programName + " - Project Report");
		contentHolder.add(new Report(this, project), GUIPanel.REPORT.toString());
		c1.show(contentHolder, GUIPanel.REPORT.toString());
		
		contentHolder.revalidate();
	}
	
	public ActiveSession getActiveSession(){
		return currSess;
	}
	
	public void saveApplication(){
		closeOpenEditors();
		
		currSess.setActiveUser(null);
		try{
			FileOutputStream fout = new FileOutputStream("data/currentSession.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout); 
			 
			oos.writeObject(currSess);
			oos.close();
			System.out.println("Done");
		   }catch(Exception ex){
			   ex.printStackTrace();
			   System.out.println("error on writing save data");
		   }
		//System.exit(0);
	}
	
	public void logInAgain(){
		contentHolder.setVisible(false);
		
		closeOpenEditors();
		
		setSize(standardWindowSize);
		contentHolder = new JPanel();
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		contentHolder.setLayout(new CardLayout());
	 	getContentPane().add(contentHolder, BorderLayout.CENTER);
		setTitle(programName);
		
		LogInWindow dialog = new LogInWindow(this);
		if(dialog.showDialog()){
			currSess.afterLoginSuccess();
		}
		else{
			System.out.println("login failed");
			System.exit(0);
		}
	}
	
	
	private void closeOpenEditors(){
		int arraySize = openEditors.size();
		
		if(arraySize > 0){
			for(TextEdit te : openEditors){
				te.applicationClosing();
			}
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver newWindow = new Driver();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}//end of Main

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("closed"); //this method is not reach
		System.exit(0);
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
//		System.out.println("closing");
		saveApplication();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
