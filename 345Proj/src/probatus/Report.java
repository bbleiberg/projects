package probatus;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Report extends JPanel {
	
	private JPanel contentPane;
	ActiveSession as;
	Driver driver;
	Project currentProject;
	StringBuilder text = new StringBuilder();
	
	public JPanel getPanel() {
		return contentPane;
	}

	/**
	 * Create the panel.
	 */
	public Report(Driver d, Project p) {
		driver = d;
		currentProject = p;
		as = d.getActiveSession();
		setLayout(null);
		
		JLabel lblProjectName = new JLabel("Project Name:");
		text.append("Project Name: ");
		lblProjectName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblProjectName.setBounds(34, 29, 296, 24);
		add(lblProjectName);
		
		JLabel projectName = new JLabel(p.getName() + System.getProperty("line.separator"));
		text.append(p.getName());
		projectName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		projectName.setBounds(382, 29, 296, 24);
		add(projectName);
		
		JLabel lblProjectManagers = new JLabel("Project Managers: ");
		text.append("Project Managers: ");
		lblProjectManagers.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblProjectManagers.setBounds(34, 64, 296, 24);
		add(lblProjectManagers);
		
		JLabel projectManagers = new JLabel(p.getSupervisor());
		projectManagers.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		projectManagers.setBounds(382, 64, 296, 24);
		add(projectManagers);
		text.append(p.getSupervisor() + System.getProperty("line.separator"));
		
		JLabel lblUsersWorkingOn = new JLabel("Users Working on Project: ");
		text.append("Users Working on Project: ");
		lblUsersWorkingOn.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsersWorkingOn.setBounds(34, 99, 296, 24);
		add(lblUsersWorkingOn);
		
		JLabel usersWorkingProject = new JLabel(p.getTeamMems());
		usersWorkingProject.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		usersWorkingProject.setBounds(382, 99, 296, 24);
		add(usersWorkingProject);
		text.append(p.getTeamMems() + System.getProperty("line.separator"));
		
		JLabel lblDateStarted = new JLabel("Date Started: ");
		text.append("Date Started: ");
		lblDateStarted.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDateStarted.setBounds(34, 134, 296, 24);
		add(lblDateStarted);
		
		JLabel dateStarted = new JLabel(p.getDate());
		dateStarted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		dateStarted.setBounds(382, 134, 296, 24);
		add(dateStarted);
		text.append(p.getDate() + System.getProperty("line.separator")+System.getProperty("line.separator")+System.getProperty("line.separator"));
		
		JLabel lblSystemRequirementsSpecification = new JLabel("System Requirements Specification");
		text.append("System Requirements Specification: ");
		lblSystemRequirementsSpecification.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSystemRequirementsSpecification.setBounds(34, 236, 296, 24);
		add(lblSystemRequirementsSpecification);
		
		JLabel srsStatus = new JLabel(p.getDocument("srs").getDocumentStatus());
		text.append(p.getDocument("srs").getDocumentStatus() + "    ");
		srsStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		srsStatus.setBounds(382, 236, 133, 24);
		add(srsStatus);
		
		JLabel srsDate = new JLabel(p.getDocument("srs").getCompletionDate());
		text.append(p.getDocument("srs").getCompletionDate() + System.getProperty("line.separator"));
		srsDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		srsDate.setBounds(545, 236, 241, 24);
		add(srsDate);
		
		JLabel lblSystemDesignDocument = new JLabel("System Design Document");
		text.append("System Design Document: ");
		lblSystemDesignDocument.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSystemDesignDocument.setBounds(34, 271, 296, 24);
		add(lblSystemDesignDocument);
		
		JLabel sddStatus = new JLabel(p.getDocument("sdd").getDocumentStatus());
		text.append(p.getDocument("sdd").getDocumentStatus() + "     ");
		sddStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		sddStatus.setBounds(382, 271, 133, 24);
		add(sddStatus);
		
		JLabel sddDate = new JLabel(p.getDocument("sdd").getCompletionDate());
		text.append(p.getDocument("sdd").getCompletionDate() + System.getProperty("line.separator"));
		sddDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		sddDate.setBounds(545, 271, 241, 24);
		add(sddDate);
		
		JLabel lblUserInterfaceDesign = new JLabel("User Interface Design Document");
		text.append("User Interface Design Document: ");
		lblUserInterfaceDesign.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUserInterfaceDesign.setBounds(34, 306, 296, 24);
		add(lblUserInterfaceDesign);
		
		JLabel uidDate = new JLabel(p.getDocument("uidd").getCompletionDate());
		uidDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		uidDate.setBounds(545, 306, 241, 24);
		add(uidDate);
		text.append(p.getDocument("uidd").getCompletionDate() + "     ");
		
		JLabel uidStatus = new JLabel(p.getDocument("uidd").getDocumentStatus());
		uidStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		uidStatus.setBounds(382, 306, 133, 24);
		add(uidStatus);
		text.append(p.getDocument("uidd").getDocumentStatus() + System.getProperty("line.separator"));
		
		JLabel lblCodeInspectionReport = new JLabel("Code Inspection Report");
		text.append("Code Inspection Report: ");
		lblCodeInspectionReport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCodeInspectionReport.setBounds(34, 343, 296, 24);
		add(lblCodeInspectionReport);
		text.append(p.getDocument("cir").getCompletionDate() + "     ");
		
		JLabel cirDate = new JLabel(p.getDocument("cir").getCompletionDate());
		cirDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cirDate.setBounds(545, 343, 241, 24);
		add(cirDate);
		text.append(p.getDocument("cir").getDocumentStatus() + System.getProperty("line.separator"));
		
		JLabel cirStatus = new JLabel(p.getDocument("cir").getDocumentStatus());
		cirStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cirStatus.setBounds(382, 343, 133, 24);
		add(cirStatus);
		
		JLabel lblTestingReport = new JLabel("Testing Report");
		text.append("Testing Report: ");
		lblTestingReport.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTestingReport.setBounds(34, 378, 296, 24);
		add(lblTestingReport);

		JLabel trDate = new JLabel(p.getDocument("tr").getCompletionDate());
		trDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		trDate.setBounds(545, 378, 241, 24);
		add(trDate);
		text.append(p.getDocument("tr").getCompletionDate() + "     ");
		
		JLabel trStatus = new JLabel(p.getDocument("tr").getDocumentStatus());
		trStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		trStatus.setBounds(382, 378, 133, 24);
		add(trStatus);
		text.append(p.getDocument("tr").getDocumentStatus() + System.getProperty("line.separator"));
		
		JLabel lblAdministratorManual = new JLabel("Administrator Manual");
		text.append("Administrator Manual: ");
		lblAdministratorManual.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAdministratorManual.setBounds(34, 413, 296, 24);
		add(lblAdministratorManual);
		
		JLabel amDate = new JLabel(p.getDocument("am").getCompletionDate());
		amDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		amDate.setBounds(545, 413, 241, 24);
		add(amDate);
		text.append(p.getDocument("am").getCompletionDate() + "     ");
		
		JLabel amStatus = new JLabel(p.getDocument("am").getDocumentStatus());
		amStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		amStatus.setBounds(382, 413, 133, 24);
		add(amStatus);
		text.append(p.getDocument("am").getDocumentStatus() + System.getProperty("line.separator"));
		
		JButton btnExport = new JButton("Export");
		btnExport.setBounds(478, 561, 112, 38);
		btnExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					TextEdit.export(e.getComponent().getParent(), text.toString());
			}
		});
		add(btnExport);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					driver.changePanelToProjectStatus(currentProject); //PanelTo(Driver.GUIPanel.PROJECTSTATUS);
			}
		});
		btnBack.setBounds(616, 561, 106, 38);
		add(btnBack);
		
	}
	
	public String toString(){
		return text.toString();
	}
	
}
