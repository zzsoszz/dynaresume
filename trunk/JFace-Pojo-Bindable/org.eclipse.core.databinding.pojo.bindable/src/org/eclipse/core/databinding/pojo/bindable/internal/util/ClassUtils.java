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
package org.eclipse.core.databinding.pojo.bindable.internal.util;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.databinding.pojo.bindable.BindableStrategyProvider;
import org.eclipse.core.databinding.pojo.bindable.initializer.instrument.agent.NoBindableStrategyProviderException;
import org.objectweb.asm.Opcodes;

/**
 * Utilities for Class name, package name.
 * 
 */
public class ClassUtils implements Opcodes {

	// Regexp '.' and '/'
	private static final String DOT_REGEXP = "\\.";
	private static final String SLASH_REGEXP = "\\/";

	// get/is/set prefix method name.
	public static final String GET_PREFIX_METHOD = "get";
	public static final String IS_PREFIX_METHOD = "is";
	public static final String SET_PREFIX_METHOD = "set";

	/**
	 * Replace String array package names with '/' character and returns it into
	 * Collection of String.
	 * 
	 * @param packageNames
	 * @return
	 */
	public static Collection<String> toSlash(String[] packageNames,
			boolean slashIt) {
		if (packageNames == null)
			return null;
		Collection<String> packageNamesWithSlash = new ArrayList<String>();
		for (int i = 0; i < packageNames.length; i++) {
			String packageName = toSlash(packageNames[i], slashIt);
			packageNamesWithSlash.add(packageName);
		}
		return packageNamesWithSlash;
	}

	/**
	 * Replace '.' character with '/' if slashIt equals to true otherwise
	 * replace '/' character with '.'.
	 * 
	 * @param packageName
	 * 
	 * @return
	 */
	public static String toSlash(String packageName, boolean slashIt) {
		if (!slashIt) {
			return toDot(packageName);
		}
		return packageName.replaceAll(DOT_REGEXP, SLASH_REGEXP);
	}

	/**
	 * Replace className '/' with '.' character.
	 * 
	 * @param className
	 * @return
	 */
	public static String toDot(String className) {
		return className.replaceAll(SLASH_REGEXP, DOT_REGEXP);
	}

	/**
	 * Return the property name = method name without 'set' prefix.
	 * 
	 * @param methodName
	 * @return
	 */
	public static String getPropertyName(String methodName) {
		if (methodName.startsWith(SET_PREFIX_METHOD)) {

			String firstChar = methodName.substring(3, 4).toLowerCase();
			if (methodName.length() > 4) {
				return firstChar + methodName.substring(4, methodName.length());
			} else {
				return firstChar;
			}

		}
		return methodName;
	}

	/**
	 * Returns the getter method name by using the propertyName.
	 * 
	 * @param propertyName
	 * @param type
	 * @return
	 */
	public static String getGetterMethod(String propertyName,
			boolean booleanType) {
		StringBuffer getterMethod = new StringBuffer();
		if (booleanType) {
			getterMethod.append(IS_PREFIX_METHOD);
		} else {
			getterMethod.append(GET_PREFIX_METHOD);
		}
		// propertyName has the first char as lower case but in getter method it
		// is the upper case after the 'get' prefix.
		getterMethod.append(propertyName.substring(0, 1).toUpperCase());
		getterMethod.append(propertyName.substring(1));
		return getterMethod.toString();
	}

	/**
	 * Create {@link BindableStrategyProvider} instance from the className.
	 * 
	 * @param classLoader
	 * @param className
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static BindableStrategyProvider createBindableStrategyProviderInstance(
			ClassLoader classLoader, String className)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		Object instance = createInstance(classLoader, className);
		if (!(instance instanceof BindableStrategyProvider)) {
			throw new NoBindableStrategyProviderException(className);
		}
		return (BindableStrategyProvider) instance;
	}

	/**
	 * Create instance of className by using classLoader.
	 * 
	 * @param classLoader
	 * @param className
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private static Object createInstance(ClassLoader classLoader,
			String className) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		return classLoader.loadClass(className).newInstance();
	}

}
