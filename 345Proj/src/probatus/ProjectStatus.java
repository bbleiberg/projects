package probatus;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;

public class ProjectStatus extends JPanel implements ActionListener{

	Driver driver;
	Project currentProject;
	
	
	/**
	 * Create the panel.
	 */
	public ProjectStatus(Driver d, Project p) {
		
		driver = d;
		currentProject = p;
		
		
		//testing changing the title to match where user is in program
		driver.setTitle(driver.getTitle().concat(" - " + currentProject.getName()));
		
		setLayout(null);
		
		JButton SrsUl = new JButton("Open");
		SrsUl.setBounds(562, 179, 107, 38);
		SrsUl.setActionCommand("open srs");
		SrsUl.addActionListener(this);
		if(!currentProject.getDocument("srs").isStarted())
			SrsUl.setText("Create");
		add(SrsUl);
		
		JButton SrsDl = new JButton("Export");
		SrsDl.setBounds(436, 179, 108, 39);
		SrsDl.setActionCommand("export srs");
		SrsDl.addActionListener(this);
		add(SrsDl);
		
		JLabel lblDocumentName = new JLabel("Document Name");
		lblDocumentName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDocumentName.setBounds(63, 154, 123, 14);
		add(lblDocumentName);
		
		JLabel lblCompleted = new JLabel("Completed");
		lblCompleted.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCompleted.setBounds(305, 154, 146, 14);
		add(lblCompleted);
		
		JButton sddDl = new JButton("Export");
		sddDl.setBounds(436, 229, 109, 38);
		sddDl.setActionCommand("export sdd");
		sddDl.addActionListener(this);
		add(sddDl);
		
		JButton sddUl = new JButton("Open");
		sddUl.setBounds(562, 229, 107, 38);
		sddUl.addActionListener(this);
		sddUl.setActionCommand("open sdd");
		if(!currentProject.getDocument("sdd").isStarted())
			sddUl.setText("Create");
		add(sddUl);
		
		JButton UiddDl = new JButton("Export");
		UiddDl.setBounds(436, 278, 109, 38);
		UiddDl.addActionListener(this);
		UiddDl.setActionCommand("export uidd");
		add(UiddDl);
		
		JButton UiddUl = new JButton("Open");
		UiddUl.setBounds(562, 278, 107, 38);
		UiddUl.addActionListener(this);
		UiddUl.setActionCommand("open uidd");
		if(!currentProject.getDocument("uidd").isStarted())
			UiddUl.setText("Create");
		add(UiddUl);
		
		JButton CirDl = new JButton("Export");
		CirDl.setBounds(436, 327, 109, 38);
		CirDl.addActionListener(this);
		CirDl.setActionCommand("export cir");
		add(CirDl);
		
		JButton CirUl = new JButton("Open");
		CirUl.setBounds(562, 327, 107, 38);
		CirUl.addActionListener(this);
		CirUl.setActionCommand("open cir");
		if(!currentProject.getDocument("cir").isStarted())
			CirUl.setText("Create");
		add(CirUl);
		
		JButton TrDl = new JButton("Export");
		TrDl.setBounds(436, 376, 109, 38);
		TrDl.addActionListener(this);
		TrDl.setActionCommand("export tr");
		add(TrDl);
		
		JButton TrUl = new JButton("Open");
		TrUl.setBounds(562, 376, 107, 38);
		TrUl.addActionListener(this);
		TrUl.setActionCommand("open tr");
		if(!currentProject.getDocument("tr").isStarted())
			TrUl.setText("Create");
		add(TrUl);
		
		JButton AmDl = new JButton("Export");
		AmDl.setBounds(436, 425, 109, 38);
		AmDl.addActionListener(this);
		AmDl.setActionCommand("export am");
		add(AmDl);
		
		JButton AmUl = new JButton("Open");
		AmUl.setBounds(562, 425, 107, 38);
		AmUl.addActionListener(this);
		AmUl.setActionCommand("open am");
		if(!currentProject.getDocument("am").isStarted())
			AmUl.setText("Create");
		add(AmUl);

		JLabel lblSystemRequirementsSpecification = new JLabel("System Requirements Specification");
		lblSystemRequirementsSpecification.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSystemRequirementsSpecification.setBounds(63, 179, 220, 38);
		add(lblSystemRequirementsSpecification);
		
		JLabel lblSystemDesignDocument = new JLabel("System Design Document");
		lblSystemDesignDocument.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSystemDesignDocument.setBounds(63, 229, 220, 38);
		add(lblSystemDesignDocument);
		
		JLabel lblUserInterfaceDesign = new JLabel("User Interface Design Document");
		lblUserInterfaceDesign.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblUserInterfaceDesign.setBounds(63, 278, 220, 38);
		add(lblUserInterfaceDesign);
		
		JLabel lblCodeInspectionReport = new JLabel("Code Inspection Report");
		lblCodeInspectionReport.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCodeInspectionReport.setBounds(63, 327, 220, 38);
		add(lblCodeInspectionReport);
		
		JLabel lblTestingReport = new JLabel("Testing Report");
		lblTestingReport.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTestingReport.setBounds(63, 376, 220, 38);
		add(lblTestingReport);
		
		JLabel lblAdministratorManual = new JLabel("Administrator Manual");
		lblAdministratorManual.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAdministratorManual.setBounds(63, 425, 220, 38);
		add(lblAdministratorManual);
		
		final JCheckBox UiddCheckBox = new JCheckBox("");
		if(currentProject.getDocument("uidd").getDocumentStatus().equals("Complete"))
		{
			UiddCheckBox.setSelected(true);
		}
		UiddCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(UiddCheckBox.isSelected() && !currentProject.getDocument("uidd").getDocumentStatus().equals("Complete"))
				{
					currentProject.getDocument("uidd").setComplete();
					currentProject.getDocument("uidd").setTime();
				}
				if(!UiddCheckBox.isSelected())
				{
					currentProject.getDocument("uidd").setIncomplete();
					currentProject.getDocument("uidd").resetTime();
				}
			}
		});
		UiddCheckBox.setBounds(333, 278, 97, 38);
		add(UiddCheckBox);
		
		final JCheckBox SrsCheckBox = new JCheckBox("");
		if(currentProject.getDocument("srs").getDocumentStatus().equals("Complete"))
		{
			SrsCheckBox.setSelected(true);
		}
		SrsCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(SrsCheckBox.isSelected() && !currentProject.getDocument("srs").getDocumentStatus().equals("Complete"))
				{
					currentProject.getDocument("srs").setComplete();
					currentProject.getDocument("srs").setTime();
				}
				if(!SrsCheckBox.isSelected())
				{
					currentProject.getDocument("srs").setIncomplete();
					currentProject.getDocument("srs").resetTime();
				}
			}
		});
		SrsCheckBox.setBounds(333, 179, 97, 38);
		add(SrsCheckBox);
		
		final JCheckBox SddCheckBox = new JCheckBox("");
		if(currentProject.getDocument("sdd").getDocumentStatus().equals("Complete"))
		{
			SddCheckBox.setSelected(true);
		}
		SddCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(SddCheckBox.isSelected() && !currentProject.getDocument("sdd").getDocumentStatus().equals("Complete"))
				{
					currentProject.getDocument("sdd").setComplete();
					currentProject.getDocument("sdd").setTime();
				}
				if(!SddCheckBox.isSelected())
				{
					currentProject.getDocument("sdd").setIncomplete();
					currentProject.getDocument("sdd").resetTime();
				}
			}
		});
		SddCheckBox.setBounds(333, 229, 97, 38);
		add(SddCheckBox);
		
		final JCheckBox CirCheckBox = new JCheckBox("");
		if(currentProject.getDocument("cir").getDocumentStatus().equals("Complete"))
		{
			CirCheckBox.setSelected(true);
		}
		CirCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(CirCheckBox.isSelected() && !currentProject.getDocument("cir").getDocumentStatus().equals("Complete"))
				{
					currentProject.getDocument("cir").setComplete();
					currentProject.getDocument("cir").setTime();
				}
				if(!CirCheckBox.isSelected())
				{
					currentProject.getDocument("cir").setIncomplete();
					currentProject.getDocument("cir").resetTime();
				}
			}
		});
		CirCheckBox.setBounds(333, 327, 97, 38);
		add(CirCheckBox);
		
		final JCheckBox AmCheckBox = new JCheckBox("");
		if(currentProject.getDocument("am").getDocumentStatus().equals("Complete"))
		{
			AmCheckBox.setSelected(true);
		}
		AmCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(AmCheckBox.isSelected() && !currentProject.getDocument("am").getDocumentStatus().equals("Complete"))
				{
					currentProject.getDocument("am").setComplete();
					currentProject.getDocument("am").setTime();
				}
				if(!AmCheckBox.isSelected())
				{
					currentProject.getDocument("am").setIncomplete();
					currentProject.getDocument("am").resetTime();
				}
			}
		});
		AmCheckBox.setBounds(333, 425, 97, 38);
		add(AmCheckBox);
		
		final JCheckBox TrCheckBox = new JCheckBox("");
		if(currentProject.getDocument("tr").getDocumentStatus().equals("Complete"))
		{
			TrCheckBox.setSelected(true);
		}
		TrCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(TrCheckBox.isSelected() && !currentProject.getDocument("tr").getDocumentStatus().equals("Complete"))
				{
					currentProject.getDocument("tr").setComplete();
					currentProject.getDocument("tr").setTime();
				}
				if(!TrCheckBox.isSelected())
				{
					currentProject.getDocument("tr").setIncomplete();
					currentProject.getDocument("tr").resetTime();
				}
			}
		});
		TrCheckBox.setBounds(333, 376, 97, 38);
		add(TrCheckBox);
		
		JLabel lblGenerateReport = new JLabel("Generate Report");
		lblGenerateReport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblGenerateReport.setBounds(63, 494, 167, 30);
		add(lblGenerateReport);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				driver.changePanelToReport(currentProject);
			}
		});
		btnGenerate.setBounds(240, 500, 89, 23);
		add(btnGenerate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(driver.getActiveSession().isCurrentUserAdmin())
					driver.changePanelTo(Driver.GUIPanel.MANAGEPROJECTS);
				else
					driver.changePanelTo(Driver.GUIPanel.OPENEXISTINGPROJECT);
			}
		});
		btnBack.setBounds(616, 561, 106, 38);
		add(btnBack);
		
		JButton btnProjectNotes = new JButton("Notes");
		btnProjectNotes.setBounds(315, 97, 89, 23);
		btnProjectNotes.addActionListener(this);
		btnProjectNotes.setActionCommand("notes");
		add(btnProjectNotes);
		
		JLabel projectName = new JLabel(currentProject.getName());
		projectName.setHorizontalAlignment(SwingConstants.CENTER);
		projectName.setFont(new Font("Times New Roman", Font.BOLD, 40));
		projectName.setBounds(63, 39, 606, 47);
		add(projectName);
		
		JButton button = new JButton("<html>Add/Remove<br />Users</html>");
		if(currentProject.currentUserHasPriv(driver.getActiveSession().getActiveUser()))
			button.setVisible(false);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				driver.changePanelToMPU(currentProject);
			}
		});
		button.setBounds(10, 561, 106, 46);
		add(button);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contains("open")){
			String documentType = e.getActionCommand().split(" ")[1];
			String docText;
			
			Document doc = currentProject.getDocument(documentType);
			
			if(!doc.isStarted()){
				doc.setStarted(true);
			}
			else{
//				System.out.println("document was found: projectStatus class");
			}

			//TODO make sure saving the string works and cannot delete the project while editor is still open
			//open textEditor, add it to an array tracking the open editors, have a method in the editor to remove itself on close
			TextEdit temp = new TextEdit(driver, doc, currentProject.getName());
			if(!Driver.easyMode){
				temp.showWindow();
				driver.openEditors.add(temp);
			}
			else{
				docText = temp.showAndWaitForClose();
			}
			JButton callingButton = (JButton)e.getSource();
			callingButton.setText("Open");
			callingButton.repaint();
        }
		else if(e.getActionCommand().contains("notes")){
			TextEdit temp = new TextEdit(currentProject);
			temp.showWindow();
			driver.openEditors.add(temp);
		}
		else if(e.getActionCommand().contains("export")){
			String documentType = e.getActionCommand().split(" ")[1];
			
			//if doc exist we get the doc
			Document doc = currentProject.getDocument(documentType);
			
			if(doc.isStarted()){
				TextEdit.export((Component)e.getSource(), doc.getText());
			}
			else{
//				int responce = JOptionPane.showConfirmDialog((Component)e.getSource(), "This document has not been created yet. Would you like to create it now?", null, JOptionPane.YES_NO_OPTION);
//				
//				if(responce == JOptionPane.YES_OPTION)
//					doc.setStarted(true);
				JOptionPane.showMessageDialog((Component)e.getSource(), "Document has not be created yet.", "Whoops", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
