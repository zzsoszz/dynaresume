package org.eclipse.jst.server.jetty.core.internal;

public interface IJettyServerWorkingCopy {

	/**
	 * The default deployment directory.  Avoid "webapps" due to
	 * side effects.
	 */
	public static final String DEFAULT_DEPLOYDIR = "wtpwebapps";

	/**
	 * The deployment directory used by default in prior versions.
	 */
	public static final String LEGACY_DEPLOYDIR = "webapps";

	/**
	 * Sets this server to test environment mode.
	 * 
	 * @param b boolean
	 */
	public void setTestEnvironment(boolean b);
	
	/**
	 * Sets the instance directory for the server. If set to
	 * null, the instance directory is derived from the
	 * testEnvironment setting.'
	 * 
	 * @param instanceDir absolule path to the instance directory.
	 */
	public void setInstanceDirectory(String instanceDir);

	/**
	 * Set the deployment directory for the server.  May be absolute
	 * or relative to runtime base directory.
	 * 
	 * @param deployDir deployment directory for the server
	 */
	public void setDeployDirectory(String deployDir);
}
