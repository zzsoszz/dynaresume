package org.eclipse.jst.server.jetty.core.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;

public interface IJettyServerBehaviour {

	/**
	 * Returns the main class that is used to launch the Tomcat server.
	 * 
	 * @return the main runtime class
	 */
	public String getRuntimeClass();

	/**
	 * Setup for starting the server.
	 * 
	 * @param launch ILaunch
	 * @param launchMode String
	 * @param monitor IProgressMonitor
	 * @throws CoreException if anything goes wrong
	 */
	public void setupLaunch(ILaunch launch, String launchMode, IProgressMonitor monitor) throws CoreException;
}
