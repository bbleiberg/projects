package probatus;

import java.io.Serializable;
import java.util.ArrayList;

public class ActiveSession implements Serializable{
	
	/**
	 * the serialVersionUID was created by eclipse 
	 */
	private static final long serialVersionUID = 9204142652089416692L;
	public ArrayList<User> users = new ArrayList<User>();
	public ArrayList<Project> projects = new ArrayList<Project>();
	private User currentUser;
	private transient Driver driver;
	
	public ActiveSession(Driver d){
		driver = d;
	}
	
	//need a add user method for when addUser gui needs to add to the list of users
	//need this^ feature for anything to add or remove
	
	public boolean checkLogin(String username, String password){
		for(User u : users){
			if(u.getUsername().equals(username)){
				if(u.getPassword().equals(password)){
					currentUser = u;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns true if user exists false if deleted or not there
	 * @param project
	 * @return boolean
	 *  
	 */
	public boolean userExists(String username){
		for(User u : users){
			if(u.getUsername().equals(username))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check to see if user Exists but is inactive
	 * Returns true if user exists and is active false otherwises
	 * @param username
	 * @return boolean
	 */
	public boolean userExistsInactive(String username){
		for(User u : users){
			if(u.getUsername().equals(username))
			{
				if(!u.isActive())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns true if project exists false if deleted
	 * @param project
	 * @return boolean
	 *  
	 */
	public boolean projectExists(String project){
		for(Project p: projects){
			if(p.getName().equals(project))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true is current user is an Admin
	 * Returns false is current user is not an Admin
	 * @return boolean
	 */
	public boolean isCurrentUserAdmin(){
		if(currentUser instanceof Admin){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks to see if a normal user or a admin is logged in and changes screen acordingly
	 */
	public void afterLoginSuccess(){
		if(this.isCurrentUserAdmin()){
			driver.changePanelTo(Driver.GUIPanel.ADMINMENU);
		}
		else{
			driver.changePanelTo(Driver.GUIPanel.OPENEXISTINGPROJECT);
		}
	}
	
	public void addUser(User u){
		users.add(u);
	}
	/**
	 * Returns an ArrayList of Users
	 * @return
	 */
	public ArrayList<User> getUsers(){
		return this.users;
	}
	
	/**
	 * Adds a project to the ArrayList of projects
	 * @param p
	 */
	public void addProject(Project p){
		projects.add(p);
	}
	
	/**
	 * Returns all the projects in an array list
	 * @return ArrayList<Project>
	 */
	public ArrayList<Project> getProjects(){
		return this.projects;
	}
	
	/**
	 * Returns the active user
	 * @return User
	 */
	public User getActiveUser(){
		return this.currentUser;
	}
	
	/**
	 * Looks for the user in the ArrayList<User> given a User Name
	 * returns the User object if found of null if not found
	 * @param userName
	 * @return User
	 */
	public User getUserbyName(String userName){
		
		for(User u : users){
			if(u.getUsername().equals(userName)){
					return u;
				}
			}
		System.out.println("Could not find user... Contact Support");
		return null;
	}
	
	/*public void addProjectToUser(Project p, String username)
	{
		this.getUserbyName(username).addProject(p);
	}*/
	
	/**
	 * Looks for the project in the ArrayList<Project> given a Project Name
	 * returns the Project object if found of null if not found
	 * @param projectName
	 * @return projects
	 */
	public Project getProjectbyName(String projectName){
		for(Project p : projects){
			if(p.getName().equals(projectName)){
					return p;
				}
			}
		System.out.println("Could not find project... Contact Support");
		return null;
	}
	
	/**
	 * Sets active user
	 * @param user
	 */
	public void setActiveUser(User user){
		this.currentUser = user;
	}
	
	/**
	 * Sets the driver
	 * @param d
	 */
	public void setDriverObject(Driver d){
		driver = d;
	}
	
	/**
	 * Returns all the user names stored in active session
	 * @return ArrayList<String<
	 */
	public ArrayList<String> getUserNames()
	{
		ArrayList<String> temp = new ArrayList<String>();
		for(User u : this.users)
		{
			temp.add(u.getUsername());
		}
		return temp;
	}

	/**
	 * Returns all the active project names stored in active session
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getProjectNames()
	{
		ArrayList<String> temp = new ArrayList<String>();
		for(Project p : this.projects)
		{
			if(p.isActive())
			{
				temp.add(p.getName());
			}
		}
		return temp;
	}
}
