package probatus;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Document implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5686793775329340913L;
	private String docName;
	private String text;
	private String documentStatus;
	private String completionDate;
	private Date startDate;
	private boolean started = false;
	
	public Document(String name) {
		this.docName = name;
		this.documentStatus = "Incomplete";
		this.completionDate = "N\\A";
	}
	
	public String getName() {
		return docName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void setComplete()
	{
		this.documentStatus = "Complete";
	}
	
	public void setIncomplete()
	{
		this.documentStatus = "Incomplete";
	}
	
	public void setTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date date = new Date();
		this.completionDate = dateFormat.format(date);
	}
	
	public void resetTime()
	{
		this.completionDate = "N\\A";
	}
	
	public String getDocumentStatus()
	{
		return documentStatus;
	}
	
	public String getCompletionDate()
	{
		return completionDate;
	}

	/**
	 * @return has the document been started yet
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * @param set if the document has been created/started, if true the date and time is set for the start or this document 
	 */
	public void setStarted(boolean started) {
		this.started = started;
		
		if(started)
			startDate = new Date();
	}
	
}
