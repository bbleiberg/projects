package probatus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Dialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.factories.FormFactory;
//import com.jgoodies.forms.layout.RowSpec;

public class LogInWindow extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel InvalidLabel;
	private boolean result = false;
	
	ActiveSession as;  // = driver.getActiveSession();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		try {
//			LogInWindow dialog = new LogInWindow();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * Create the dialog.
	 */
	public LogInWindow(Driver d) {
		super(d, Dialog.ModalityType.APPLICATION_MODAL);
		as = d.getActiveSession();
		
		setTitle("LOGIN");
		setResizable(false);
		setBounds(100, 100, 450, 176);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 89);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "4, 4, left, top");
		}
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("User Name: ");
			lblNewLabel.setBounds(47, 39, 73, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(116, 36, 221, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setBounds(47, 64, 73, 14);
			contentPanel.add(lblPassword);
		}
		
		passwordField = new JPasswordField();
		passwordField.setBounds(116, 61, 221, 20);
		contentPanel.add(passwordField);
		
		JLabel lblInvalid = new JLabel("Invalid User Name and Password");
		lblInvalid.setFont(new Font("Serif", Font.PLAIN, 16));
		lblInvalid.setForeground(Color.RED);
		lblInvalid.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalid.setBounds(51, 3, 332, 25);
		lblInvalid.setVisible(false);
		InvalidLabel = lblInvalid;
		contentPanel.add(lblInvalid);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBounds(0, 100, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("LOGIN");
				okButton.setActionCommand("login");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Cancel")){
        	setVisible(false);
    		dispose();
//        	System.exit(0);
        }
        else if(e.getActionCommand().equals("login")){
        	if(as.checkLogin(textField.getText(), getPwd()) && as.getUserbyName(textField.getText()).isActive()){
        			result = true;	
        			setVisible(false);
        			dispose();
        		
        	}
        	else{
        		textField.setText("");
        		passwordField.setText("");
        		InvalidLabel.setVisible(true);
        	}
        }
        
    }
	
	private String getPwd(){
		StringBuilder pswd = new StringBuilder();
    	
    	for(int i = 0; i < passwordField.getPassword().length; i++){
    		pswd.append(passwordField.getPassword()[i]);
    	}
    	
    	return pswd.toString();
	}
	
	public boolean showDialog(){
		setVisible(true);
	    return result;
	}
}
