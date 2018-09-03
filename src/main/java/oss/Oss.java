package oss;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oss.controllers.TemplateEditorController;
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

/** a handle to the application */
public static Oss handle;

private static Logger logger = LogManager.getLogger(Oss.class);

private static final long serialVersionUID = 1L;

private static final String TEMPLATE_EDITOR_VIEW = "TEMPLATE_EDITOR_VIEW";

/** stores the name of the currently displayed card, because the card layout manager doesn't do this for us */
private String currentCardName;

public static void main(String[] args) {
	try {
		handle = new Oss();
		logger.debug("application started");
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		System.exit(1);
	}
}

/**
 * a handle to this menu item is maintained so that it can be made active or inactive as required, depending on which
 * major panel is currently visible
 */
private JMenuItem miNewTemplate;

/** the model used by the template editor */
private TemplateEditorModel templateEditorModel;

public Oss() {
	super("Old School Shooter (September, 2018)");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	setContentPane(new JPanel());
	getContentPane().setLayout(new CardLayout());

	// set up template editor
	templateEditorModel = new TemplateEditorModel();
	templateEditorModel.setMap(MapFactory.createSimpleMap("New Map", 20, 10, Tile.GRASS));
	TemplateEditorView ossTemplateEditor = new TemplateEditorView();
	TemplateEditorController.getInstance().setTemplateEditorView(ossTemplateEditor);
	ossTemplateEditor.setModel(templateEditorModel);
	getContentPane().add(TEMPLATE_EDITOR_VIEW, ossTemplateEditor);

	setupMenu();

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setSize(screenSize.width - 50, screenSize.height - 100);
	setLocation(25, 25);

	setVisible(true);
}

public TemplateEditorModel getTemplateEditorModel() {
	return templateEditorModel;
}

public void setTemplateEditorModel(TemplateEditorModel templateEditorModel) {
	this.templateEditorModel = templateEditorModel;
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

	miNewTemplate = new JMenuItem("New Template");
	miNewTemplate.setEnabled(false);
	miNewTemplate.addActionListener(e -> {
		((CardLayout) getContentPane().getLayout()).show(getContentPane(), TEMPLATE_EDITOR_VIEW);
		revalidate();
		TemplateEditorController.getInstance().newTemplate();
	});
	fileMenu.add(miNewTemplate);

	fileMenu.addSeparator();

	JMenuItem miSave = new JMenuItem("Save");
	miSave.addActionListener(e -> {
		if (TEMPLATE_EDITOR_VIEW.equals(currentCardName))
			TemplateEditorController.getInstance().saveCurrentTemplate();
		else
			JOptionPane.showMessageDialog(this, "Nothing to save on this screen", "Sorry",
					JOptionPane.INFORMATION_MESSAGE);
	});
	fileMenu.add(miSave);

	JMenuItem miSaveAs = new JMenuItem("Save As");
	miSaveAs.addActionListener(e -> {
		if (TEMPLATE_EDITOR_VIEW.equals(currentCardName))
			TemplateEditorController.getInstance().saveCurrentTemplateAs();
		else
			JOptionPane.showMessageDialog(this, "Nothing to save on this screen", "Sorry",
					JOptionPane.INFORMATION_MESSAGE);
	});
	fileMenu.add(miSaveAs);

	fileMenu.addSeparator();

	JMenuItem miExit = new JMenuItem("Exit", Character.getNumericValue('x'));
	miExit.addActionListener(e -> System.exit(1));
	fileMenu.add(miExit);

	// view menu

	JMenu viewMenu = new JMenu("View");
	fileMenu.setMnemonic(Character.getNumericValue('v'));
	jMenuBar.add(viewMenu);

	JMenuItem miViewTemplateEditor = new JMenuItem("Template Editor");
	miViewTemplateEditor.addActionListener(e -> {
		((CardLayout) getContentPane().getLayout()).show(getContentPane(), TEMPLATE_EDITOR_VIEW);
		currentCardName = TEMPLATE_EDITOR_VIEW;
		miNewTemplate.setEnabled(true);
		miSave.setEnabled(true);
		revalidate();
	});
	viewMenu.add(miViewTemplateEditor);

	setJMenuBar(jMenuBar);
}

}
