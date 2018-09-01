package oss.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TemplateEditorController implements KeyListener {

private static Logger logger = LogManager.getLogger(TemplateEditorController.class);

@Override
public void keyTyped(KeyEvent e) {
	logger.debug(e.toString());
}

@Override
public void keyPressed(KeyEvent e) {
}

@Override
public void keyReleased(KeyEvent e) {
}

}
