package oss;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oss.enums.Tile;
import oss.factories.MapFactory;
import oss.model.TemplateEditorModel;
import oss.view.TemplateEditorView;

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

private static final String TEMPLATE_EDITOR_VIEW = "TEMPLATE_EDITOR_VIEW";

private static Logger logger = LogManager.getLogger(Oss.class);

public Oss() {
	super("Old School Shooter (September, 2018)");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	setContentPane(new JPanel());
	getContentPane().setLayout(new CardLayout());

	// set up template editor
	TemplateEditorModel templateEditorModel = new TemplateEditorModel();
	templateEditorModel.setMap(MapFactory.createSimpleMap("New Map", 20, 10, Tile.GRASS));
	TemplateEditorView ossTemplateEditor = new TemplateEditorView();
	ossTemplateEditor.setModel(templateEditorModel);
	// TODO: add controller
	getContentPane().add(TEMPLATE_EDITOR_VIEW, ossTemplateEditor);

	setupMenu();

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(screenSize.width - 50, screenSize.height - 100);
	setLocation(25, 25);

	setVisible(true);
}

/**
 * Creates the menu bar, and all of its content.
 */
private void setupMenu() {
	JMenuBar jMenuBar = new JMenuBar();

	// file menu

	JMenu fileMenu = new JMenu("File");
	fileMenu.setMnemonic(Character.getNumericValue('f'));
	jMenuBar.add(fileMenu);

	JMenuItem miNewTemplate = new JMenuItem("New Template");
	miNewTemplate.addActionListener(e -> {
		((CardLayout) getContentPane().getLayout()).show(getContentPane(), TEMPLATE_EDITOR_VIEW);
		revalidate();
	});
	fileMenu.add(miNewTemplate);

	fileMenu.addSeparator();

	JMenuItem miExit = new JMenuItem("Exit", Character.getNumericValue('x'));
	miExit.addActionListener(e -> System.exit(1));
	fileMenu.add(miExit);

	setJMenuBar(jMenuBar);
}

public static void main(String[] args) {
	try {
		new Oss();
		logger.debug("application started");
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		System.exit(1);
	}
}

}
