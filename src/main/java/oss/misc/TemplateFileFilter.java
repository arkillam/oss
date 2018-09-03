package oss.misc;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class TemplateFileFilter extends FileFilter {

@Override
public boolean accept(File f) {
	if (f == null)
		return false;
	return f.getAbsolutePath().toLowerCase().endsWith(MC.TEMPLATE_SUFFIX);
}

@Override
public String getDescription() {
	return "OSS Template (" + MC.TEMPLATE_SUFFIX + ")";
}

}
