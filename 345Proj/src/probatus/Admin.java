package probatus;

import java.io.Serializable;

public class Admin extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7120719614776931027L;
	ActiveSession as;// = driver.getActiveSession();
	
	public Admin(String name, String pass) {
		super(name, pass);	
	}
	
	public void delProj(String name) {
		System.out.println("delProj");
	}
	
	public User createUser(String name, String password) {
		return new User(name, password);
		//System.out.println("Create user" + name);
	}
	
	public Admin createAdmin(String name, String password) {
		return new Admin(name, password);
	}

	public void destroyUser(User userObj) {
		int index = as.users.indexOf(userObj);
		as.users.get(index).deactivate();
		//System.out.println("Destroy user" + userObj.getUsername());	
	}
	
//	public void addUserToProj(String projName, String userName) {
//		System.out.println("Add user " + userName + " to " + projName);
//	}
//	
//	public void delUserFromProj(String projName, String userName) {
//		System.out.println("Delete user " + userName + " from " + projName);
//	}
}
