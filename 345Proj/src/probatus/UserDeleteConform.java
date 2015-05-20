package probatus;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UserDeleteConform extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserDeleteConform() {
		setLayout(null);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to delete this user?");
		lblAreYouSure.setBounds(69, 11, 293, 14);
		add(lblAreYouSure);
		
		JButton btnYes = new JButton("Yes");
		btnYes.setBounds(69, 36, 89, 23);
		add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.setBounds(187, 36, 89, 23);
		add(btnNo);

	}

}
