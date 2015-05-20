package probatus;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GUI1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Driver mainWindow = new Driver();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI1 window = new GUI1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println("end of gui holder");
	}

	/**
	 * Create the application.
	 */
	public GUI1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Create New Project");
//		frame.add(new OpenExistingProject(this));
	}

}
