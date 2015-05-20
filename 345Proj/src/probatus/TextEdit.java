package probatus;

import java.io.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Component;
import java.awt.event.*;
import java.util.*;

public class TextEdit extends JFrame implements ActionListener, WindowListener, DocumentListener {
	private JTextArea textArea = new JTextArea();
	private JMenu fileMenu = new JMenu("File");
	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem newItem = new JMenuItem("New");
	private JMenuItem openItem = new JMenuItem("Open");
	private JMenuItem saveItem = new JMenuItem("Save");
	private JMenuItem exportToItem = new JMenuItem("Export To");
	private JMenuItem exitItem = new JMenuItem("Exit");
	private String filename = null;  // set by "Open" or "Save As"

	//my additions
	private boolean modified = false;
	private Document doc;
	private Project proj;
	private boolean isNote = false;

	public static void main(String args[]) {
		new TextEdit();
	}



	// Constructor: create a text editor with a menu
	public TextEdit() {
		super("Text Editor");

		// Create menu and add listeners
		//    fileMenu.add(newItem);
		//    fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exportToItem);
		fileMenu.add(exitItem);
		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exportToItem.addActionListener(this);
		exitItem.addActionListener(this);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);

		// Create and display rest of GUI
		add(new JScrollPane(textArea));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
	}

	//second constructor to be used with the showAndWaitForClose()
	public TextEdit(Component c, Document d, String projName) {
		super("Text Editor");

		doc = d;

		setTitle(projName + ": " + doc.getName());
		addWindowListener(this);

		// Create menu and add listeners
//		fileMenu.add(newItem);
		//	    fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exportToItem);
		fileMenu.add(exitItem);
		newItem.addActionListener(this);
		//	    openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exportToItem.addActionListener(this);
		exitItem.addActionListener(this);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		textArea.setText(doc.getText());
		textArea.getDocument().addDocumentListener(this);

		// Create and display rest of GUI
		add(new JScrollPane(textArea));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300, 300);
	}
	
	
	/**
	 * constructor for use with document notes
	 */
	public TextEdit(Project p) {
		super("Text Editor");

		isNote = true;
		proj = p;
		
		setTitle(p.getName() + ": Notes");
		addWindowListener(this);

		// Create menu and add listeners 
//		fileMenu.add(newItem);
		//	    fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exportToItem);
		fileMenu.add(exitItem);
		newItem.addActionListener(this);
		//	    openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exportToItem.addActionListener(this);
		exitItem.addActionListener(this);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		textArea.setText(p.getNotes());
		textArea.getDocument().addDocumentListener(this);

		// Create and display rest of GUI
		add(new JScrollPane(textArea));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300, 300);
	}

	// Handle menu events
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newItem)
			textArea.setText("");
//		else if (e.getSource() == openItem)
//			loadFile();
		else if (e.getSource() == saveItem){
			if(!isNote){
				doc.setText(textArea.getText());
				setTitle(getTitle().substring(1));
				modified = false;
			}
			else{
				proj.setNotes(textArea.getText());
				setTitle(getTitle().substring(1));
				modified = false;
			}
		}
		else if (e.getSource() == exportToItem)
			saveFile(null);
		else if (e.getSource() == exitItem){
			if(confirmClose())
				closeThisWindow();
		}

	}

	// Prompt user to enter filename and load file.  Allow user to cancel.
	// If file is not found, pop up an error and leave screen contents
	// and filename unchanged.
//	private void loadFile() {
//		JFileChooser fc = new JFileChooser();
//		String name = null;
//		if (fc.showOpenDialog(null) != JFileChooser.CANCEL_OPTION)
//			name = fc.getSelectedFile().getAbsolutePath();
//		else
//			return;  // user cancelled
//		try {
//			Scanner in = new Scanner(new File(name));  // might fail
//			filename = name;
//			textArea.setText("");
//			while (in.hasNext())
//				textArea.append(in.nextLine() + "\n");
//			in.close();
//		}
//		catch (FileNotFoundException e) {
//			JOptionPane.showMessageDialog(null, "File not found: " + name,
//					"Error", JOptionPane.ERROR_MESSAGE);
//		}
//	}

	// Save named file.  If name is null, prompt user and assign to filename.
	// Allow user to cancel, leaving filename null.  Tell user if save is
	// successful.
	private void saveFile(String name) {
		if (name == null) {  // get filename from user
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(null) != JFileChooser.CANCEL_OPTION)
				name = fc.getSelectedFile().getAbsolutePath();
		}
		if (name != null) {  // else user cancelled
			try {
				Formatter out = new Formatter(new File(name));  // might fail
				filename = name;
				out.format("%s", textArea.getText());
				out.close();
				JOptionPane.showMessageDialog(null, "Saved to " + filename,
						"Save File", JOptionPane.PLAIN_MESSAGE);
			}
			catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Cannot write to file: " + name,
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	//Display the editor and return the text entered. 
	public String showAndWaitForClose(){
		setVisible(true);
		return textArea.getText();
	}

	public void showWindow(){
		setVisible(true);
	}

	private void closeThisWindow(){
		//remove from static list of open windows
		//any other clean up to do?
		//close window
		Driver.openEditors.remove(this);
		setVisible(false);
	}

	private boolean confirmClose(){
		int responce;

		//prompt user for confirmation and want to save if unsaved or if not modified confirm quit
		if(modified){
			responce = JOptionPane.showConfirmDialog(this, "Do you want to save before closing?");
		}else{
			responce = JOptionPane.showConfirmDialog(this, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
		}

		if(!modified && responce == JOptionPane.YES_OPTION){
			return true;
		}
		else if(modified && responce == JOptionPane.OK_OPTION){
			//set contents of document file to what is in the text area then close window
			if(!isNote){
				doc.setText(textArea.getText());
			}
			else{
				proj.setNotes(textArea.getText());
			}
			return true;
		}
		else if(modified && responce == JOptionPane.NO_OPTION){
			return true;
		}

		return false;
	}
	
	public void applicationClosing(){
		confirmClose();
		closeThisWindow();
	}

	public void windowClosing(WindowEvent evt) {
		System.out.println("user tryed to close window");

		if(confirmClose())
			closeThisWindow();
		//else do nothing
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}


	//used to know if the document has been modified
	@Override
	public void changedUpdate(DocumentEvent arg0) {
		modified = true;
		if(!getTitle().contains("*"))
			setTitle("*" + getTitle());
	}



	@Override
	public void insertUpdate(DocumentEvent arg0) {
		modified = true;
		if(!getTitle().contains("*"))
			setTitle("*" + getTitle());
	}



	@Override
	public void removeUpdate(DocumentEvent arg0) {
		modified = true;
		if(!getTitle().contains("*"))
			setTitle("*" + getTitle());
	}
	
	
	/**
	 * static method for saving a file to a folder of the users choice.
	 */
	public static void export(Component c, String toSave){
		String name = null;
		
		JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(c) != JFileChooser.CANCEL_OPTION)
			name = fc.getSelectedFile().getAbsolutePath();
		
		if (name != null) {  // else user cancelled
			try {
				Formatter out = new Formatter(new File(name));  // might fail
				String filename = name;
				out.format("%s", toSave);
				out.close();
				JOptionPane.showMessageDialog(null, "Saved to " + filename,
						"Save File", JOptionPane.PLAIN_MESSAGE);
			}
			catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Cannot write to file: " + name,
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}