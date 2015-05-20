package probatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1120811457048179251L;
	private String username;
	private String password;
	private ArrayList<Project> userProjects = new ArrayList<Project>();
	private boolean activeUser;
	
	
	/*public void addProject(Project p)
	{
		userProjects.add(p);
	}*/
	
	public User(String name, String pass) {
		this.username = name;
		this.password = pass;
		this.activeUser = true;
	}
	
	public String getUsername() {
		return username;
	}
	
	/**
	 * Returns the projects the user has access too.
	 * @return ArrayList<Project>
	 */
	public ArrayList<Project> getProjects()
	{
		return this.userProjects;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void activate() {
		this.activeUser = true;
	}
	
	public boolean isActive(){
		return this.activeUser;
	}
	
	public void deactivate() {
		this.activeUser = false;
	}
	
	public void setPassword(String newPass) {
		this.password = newPass;
	}
	
	public String toString(){
		return "user name: " + username + ", password: " + password; 
	}
}
