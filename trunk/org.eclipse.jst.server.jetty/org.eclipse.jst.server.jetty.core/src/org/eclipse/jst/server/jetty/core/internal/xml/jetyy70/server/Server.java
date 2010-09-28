package org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.server;

import java.io.File;
import java.util.Collection;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.server.jetty.core.internal.xml.XMLElement;

public class Server extends XMLElement {

	private File file;
	private IPath path;
	
	public Collection<Connector> getConnectors() {
		return null;
	}

	public void setFile(File jettyXMLFile) {
		this.file = jettyXMLFile;		
	}
	
	public File getFile() {
		return file;
	}

	public IPath getPath() {
		return path;
	}

	public void setPath(IPath path) {
		this.path = path;
	}

	
	
}
