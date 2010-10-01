package org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.server;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.server.jetty.core.internal.xml.XMLElement;

public class WebApp extends XMLElement {

	private File file;
	private IPath path;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public IPath getPath() {
		return path;
	}
	public void setPath(IPath path) {
		this.path = path;
	}
	
	
}
