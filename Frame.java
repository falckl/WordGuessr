package wordGuesserGame;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
private static final long serialVersionUID = 1L;
	
	Frame() {
		this.add(new Panel());
		this.setTitle("WordGuessr");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
