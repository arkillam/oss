package oss;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * <p>
 * Old School Shooter.
 * 
 * <p>
 * I have been writing (and abondoning half-written) clones of a classic tactical western shooter I first played around
 * the age of ten on a C64 in my parent's basement. Ah, the good old days! I'm hoping I finally finish this project up
 * ...
 * 
 * <p>
 * Broke ground late in the evening on August 24th, 2018.
 */

public class Oss extends JFrame {

private static final long serialVersionUID = 1L;

public Oss() {
	super("Old School Shooter (August, 2018)");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(screenSize.width - 50, screenSize.height - 100);
	setLocation(25, 25);

	setVisible(true);
}

public static void main(String[] args) {
	try {
		new Oss();
	} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
}

}
