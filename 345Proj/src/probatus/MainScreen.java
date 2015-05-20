/**
 * 
 */
package probatus;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Ben Bleiberg
 *
 */
public class MainScreen extends JPanel{
	
	private final JPanel panel = new JPanel();
	
	
	public MainScreen(){
		setLayout(new GridLayout(3, 3, 0, 0));
		
		JButton btnNewButton_1 = new JButton("Create new project");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Open existing project");
		add(btnNewButton);
		
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
