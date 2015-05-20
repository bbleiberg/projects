package probatus;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 *
 */
public class Project implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5252116612412407928L;
	private String projName;
	private ArrayList<Document> docList = new ArrayList<Document>();
	private ArrayList<User> userList = new ArrayList<User>();
	private String supervisor;
	private String teamMems;
	private String sqaoManager;
	private boolean activeProject;
	private String dateStarted;
	private String notes;
	private ArrayList<User> addDelete;
	
	
	
	public Project(String name) {
		this.projName = name;
		this.AddDocuments();
		addDelete = new ArrayList<User>();
	}
	
	public Project(String param_projName, String param_supervisor, String param_teamMems, String param_sqaoManager){
		
		this.projName = param_projName;
		this.supervisor = param_supervisor;
		this.teamMems = param_teamMems;
		this.sqaoManager = param_sqaoManager;
		this.activeProject = true;
		this.AddDocuments();
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date date = new Date();
		this.dateStarted = dateFormat.format(date);
		addDelete = new ArrayList<User>();
		
	}
	
	/**
	 * Adds userName to the team members string
	 * @param userName
	 */
	public void addTeamMems(String userName)
	{
		if(teamMems == "")
		{
			teamMems = teamMems + userName;
		}
		else
			teamMems = teamMems + "," + userName;
	}
	
	/**
	 * Deletes userName from the team members string
	 * @param userName
	 */
	public void deleteTeamMems(String userName)
	{
		String tokens[] = teamMems.split(",");
		int counter = 0;
		int removeElement = 0;
		for(String s : tokens)
		{
			if(s.equals(userName))
			{
				removeElement = counter;
				System.out.println("counter = " + counter);
			}
			counter++;
		}
		teamMems = "";
		int end = counter;
		counter = 0;
		for(String s : tokens)
		{
			counter++;
			if(s != tokens[removeElement])
			{
				if(counter != end)
				{
					teamMems = teamMems + s + ",";
				}
				else
					teamMems = teamMems + s;
			}
		}
	}
	
	/**
	 * Give the user passed in the ability to add or delete users
	 * @param u
	 */
	public void giveAddDeletePriv(User u)
	{
		addDelete.add(u);
	}
	
	/**
	 * Checks to see if user passed in has the ability to add or remove users
	 * @param u
	 * @return boolean
	 */
	public boolean currentUserHasPriv(User u){
		if(addDelete.contains(u))
			return true;
		return false;
	}
	
	/**
	 * Returns the users who have access to the project
	 * @return ArrayList<User>
	 */
	public ArrayList<User> getUserList()
	{
		return userList;
	}
	
	
	/**
	 * Adds user to list that allows them to access the project
	 * @param u
	 */
	public void addUser(User u)
	{
		userList.add(u);
	}
	
	/**
	 * Removes user from list so they no long have access to the project
	 * @param u
	 */
	public void removeUser(User u)
	{
		userList.remove(u);
	}
	
	/**
	 * Returns true is user already has acess to project
	 * @param u
	 * @return
	 */
	public boolean userHasPriv(User u)
	{
		if(userList.contains(u))
		{
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Checks to see if a user has access to the project
	 * returns true if user has access false if not
	 * @param currentUser
	 * @return boolean
	 */
	public boolean userHasAccess(User currentUser)
	{
		if(userList.contains(currentUser))
		{
			return true;
		}
		else
			return false;
	} 
	
	public void AddDocuments()
	{
		createAndReturnDocument("srs");
		createAndReturnDocument("sdd");
		createAndReturnDocument("uidd");
		createAndReturnDocument("cir");
		createAndReturnDocument("tr");
		createAndReturnDocument("am");
	}
	
	public void createDocument(String name) {
		docList.add(new Document(name));
	}
	
	public Document createAndReturnDocument(String name) {
		Document temp = new Document(name);
		docList.add(temp);
		return temp;
	}
	
	public String getName() {
		return projName;
	}
	
	public String getSupervisor() {
		return supervisor;
	}
	
	public String getTeamMems() {
		return teamMems;
	}
	
	public String getsqaoManager() {
		return sqaoManager;
	}
	
	/**
	 * Returns the date Started
	 * @return string
	 */
	public String getDate() {
		return dateStarted;
	}
	
	public void setName(String newName) {
		this.projName = newName;
	}
	
	public String generateReport() {
		return "generateReport()";
	}
	
	
	/**
	 * Marks the project as Active and not deleted so it will display
	 */
	public void activate() {
		this.activeProject = true;
	}
	
	/**
	 * Marks the project as deleted
	 */
	public void deactivate() {
		this.activeProject = false;
	}
	
	/**
	 * Checks to see if project is not deleted
	 * @return
	 */
	public boolean isActive(){
		return this.activeProject;
	}
	
	
	/**
	 * returns the document with the name given or null if no document with that name is found
	 * @param name
	 * @return
	 */
	public Document getDocument(String name){
		for(Document d : docList){
			if(d.getName().equals(name))
				return d;
		}
		
		return null;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
