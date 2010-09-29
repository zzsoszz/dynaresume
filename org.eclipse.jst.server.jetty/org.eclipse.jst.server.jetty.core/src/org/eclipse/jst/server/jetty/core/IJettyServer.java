package org.eclipse.jst.server.jetty.core;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.wst.server.core.model.IURLProvider;

public interface IJettyServer extends IURLProvider {

	/**
	 * Property which specifies whether this server is configured for testing
	 * environment.
	 */
	public static final String PROPERTY_TEST_ENVIRONMENT = "testEnvironment";

	/**
	 * Property which specifies the directory where the server instance exists.
	 * If not specified, instance directory is derived from the textEnvironment
	 * setting.
	 */
	public static final String PROPERTY_INSTANCE_DIR = "instanceDir";

	/**
	 * Property which specifies the directory where web applications are
	 * published.
	 */
	public static final String PROPERTY_DEPLOY_DIR = "deployDir";

	/**
	 * Property which specifies if modules should be served without publishing.
	 */
	public static final String PROPERTY_SERVE_MODULES_WITHOUT_PUBLISH = "serveModulesWithoutPublish";

	IJettyConfiguration getJettyConfiguration() throws CoreException;

	IJettyConfiguration getServerConfiguration() throws CoreException;

}
