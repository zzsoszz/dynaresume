package org.eclipse.jst.server.jetty.core.internal.xml.jetyy70;

import java.util.Collection;

import org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.server.Connector;
import org.eclipse.jst.server.jetty.core.internal.xml.jetyy70.server.Server;

public class ServerInstance {

	private Server jettyServer;
	private Server jettyDeployServer;

	public ServerInstance(Server jettyServer, Server jettyDeployServer) {
		if (jettyServer == null)
			throw new IllegalArgumentException(
					"Jetty Server argument may not be null.");
		if (jettyDeployServer == null)
			throw new IllegalArgumentException(
					"Jetty-Deploy Server argument may not be null.");
		this.jettyServer = jettyServer;
		this.jettyDeployServer = jettyDeployServer;
	}
	
	public Collection<Connector> getConnectors() {
		return null;
	}

	public void removeContext(int index) {
		// TODO Auto-generated method stub
		
	}
	
	
}
