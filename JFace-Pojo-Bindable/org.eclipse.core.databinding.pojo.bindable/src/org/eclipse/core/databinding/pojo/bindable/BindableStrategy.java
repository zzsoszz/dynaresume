/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable;

import java.beans.PropertyChangeSupport;
import java.util.Collection;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;

/**
 * Interface which provides strategy to know if Class and Method of the class
 * must be transformed.
 * 
 */
public interface BindableStrategy {

	String POJO_BINDABLE = "org.eclipse.core.databinding.pojo.bindable";

	/**
	 * Return true if className must be transformed (by adding and implementing
	 * {@link BindableAware} interface) and false otherwise.
	 * 
	 * @param className
	 * @return
	 */
	boolean isBindableClass(String className);

	/**
	 * Return true if methodName must be transformed (by calling
	 * {@link PropertyChangeSupport#firePropertyChange(java.beans.PropertyChangeEvent)}
	 * and false otherwise.
	 * 
	 * @param methodName
	 * @return
	 */
	boolean isBindableMethod(String className, String methodName);

	/**
	 * Return true if {@link Bindable} interface must be used and false
	 * otherwise.
	 * 
	 * @return
	 */
	boolean isUseAnnotation();

	/**
	 * Return the base dir if class transformed must be generated and null
	 * otherwise.
	 * 
	 * @return
	 */
	String getGenBaseDir();

	/**
	 * Return true id debug mode is activated (some trace will display) and
	 * false otherwise.
	 * 
	 * @return
	 */
	boolean isDebugMode();

	/**
	 * Returns list of package names of class wich must be transformed.
	 * 
	 * @return
	 */
	Collection<String> getPackageNames();

	/**
	 * Logs a message to the Bindable logger.
	 */
	void log(int severity, String message, Throwable throwable);

}
