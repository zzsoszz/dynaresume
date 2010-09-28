package org.eclipse.jst.server.jetty.core.internal.xml.jetyy70;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.server.core.internal.ProgressUtil;
import org.eclipse.jst.server.jetty.core.internal.util.IOUtils;
import org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.server.Connector;
import org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.server.Server;

public class ServerInstance {

	private List<Server> jettyServers;

	public ServerInstance(List<Server> jettyServers) {
		if (jettyServers == null)
			throw new IllegalArgumentException(
					"Jetty Server argument may not be null.");
		this.jettyServers = jettyServers;
	}
	
	public Collection<Connector> getConnectors() {
		return null;
	}

	public boolean removeContext(int index) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Server> getJettyServers() {
		return jettyServers;
	}

	public void save(final IFolder folder, IProgressMonitor monitor) throws IOException, CoreException {
		IPath path = null;
		String filename = null;
		byte[] data = null;
		InputStream in  = null;
		IFolder newFolder = folder;
		for (Server jettyServer : jettyServers) {
			path = jettyServer.getPath();			
			if (path.segmentCount() > 1) {
				newFolder = folder.getFolder(path.removeLastSegments(1));
				IOUtils.createFolder(newFolder, monitor);
			}
			
			filename = jettyServer.getFile().getName();
			data = jettyServer.getFactory().getContents();
			in = new ByteArrayInputStream(data);
			IFile file = newFolder.getFile(filename);
			if (file.exists()) {			
				//if (isServerDirty)
					file.setContents(in, true, true, ProgressUtil.getSubMonitorFor(monitor, 200));
				//else
				//	monitor.worked(200);
			} else
				file.create(in, true, ProgressUtil.getSubMonitorFor(monitor, 200));
		}
		
	}
	
	
}
