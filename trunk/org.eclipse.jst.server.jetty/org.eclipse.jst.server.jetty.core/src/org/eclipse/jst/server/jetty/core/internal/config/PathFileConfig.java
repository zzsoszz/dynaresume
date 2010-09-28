package org.eclipse.jst.server.jetty.core.internal.config;

import java.io.File;

import org.eclipse.core.runtime.IPath;

public class PathFileConfig {

	private File file;
	private IPath path;
	
	public PathFileConfig(File file, IPath path) {
		this.file =file;
		this.path = path;
	}
	
	public File getFile() {
		return file;
	}
	
	public IPath getPath() {
		return path;
	}
}
