package org.eclipse.jst.server.jetty.core;

import org.eclipse.jst.server.jetty.core.internal.IJettyWebModule;

public interface IJettyConfigurationWorkingCopy extends IJettyConfiguration {
	/**
	 * Add a web module.
	 *
	 * @param index int
	 * @param module org.eclipse.jst.server.jetty.WebModule
	 */
	public void addWebModule(int index, IJettyWebModule module);
	
	/**
	 * Change a web module.
	 * 
	 * @param index int
	 * @param docBase java.lang.String
	 * @param path java.lang.String
	 * @param reloadable boolean
	 */
	public void modifyWebModule(int index, String docBase, String path, boolean reloadable);

	/**
	 * Remove a web module.
	 * 
	 * @param index int
	 */
	public void removeWebModule(int index);

//	/**
//	 * Adds a mime mapping.
//	 *
//	 * @param index int
//	 * @param map MimeMapping
//	 */
//	public void addMimeMapping(int index, IMimeMapping map);
//
//	/**
//	 * Change a mime mapping.
//	 * 
//	 * @param index int
//	 * @param map MimeMapping
//	 */
//	public void modifyMimeMapping(int index, IMimeMapping map);

	/**
	 * Modify the port with the given id.
	 *
	 * @param id java.lang.String
	 * @param port int
	 */
	public void modifyServerPort(String id, int port);

	/**
	 * Remove a mime mapping.
	 * 
	 * @param index int
	 */
	//public void removeMimeMapping(int index);
}