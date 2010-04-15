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
	private static final String GET_PREFIX_METHOD = "get";
	private static final String IS_PREFIX_METHOD = "is";
	private static final String SET_PREFIX_METHOD = "set";

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
	 * Return the property name = method name without 'set', 'get' or 'is'
	 * prefix.
	 * 
	 * @param methodName
	 * @return
	 */
	public static String getPropertyName(String methodName) {
		int index = -1;
		if (isSetterMethod(methodName) || isGetterGetMethod(methodName)) {
			index = 3;
		} else if (isGetterIsMethod(methodName)) {
			index = 2;
		}
		if (index != -1) {
			int prefixIndex = index + 1;
			String firstChar = methodName.substring(index, prefixIndex)
					.toLowerCase();
			if (methodName.length() > prefixIndex) {
				return firstChar
						+ methodName
								.substring(prefixIndex, methodName.length());
			} else {
				return firstChar;
			}

		}
		return methodName;
	}

	/**
	 * Returns property descriptor from method descriptor.
	 * 
	 * Ex : '()D' return 'D'.
	 * 
	 * @param methodDesc
	 * @return
	 */
	public static String getPropertyDesc(String methodDesc) {
		if (methodDesc.startsWith("()")) {
			return methodDesc.substring(2, methodDesc.length());
		}
		return methodDesc;
	}

	/**
	 * Return true if method is getter start with 'get' or 'is' and false
	 * otherwise.
	 * 
	 * @param methodName
	 * @return
	 */
	public static boolean isGetterMethod(String methodName) {
		return isGetterGetMethod(methodName) || isGetterIsMethod(methodName);
	}

	/**
	 * Return true if method is getter start with 'is' and false otherwise.
	 * 
	 * @param methodName
	 * @return
	 */
	public static boolean isGetterIsMethod(String methodName) {
		return methodName.startsWith(ClassUtils.IS_PREFIX_METHOD);
	}

	/**
	 * Return true if method is getter start with 'get' and false otherwise.
	 * 
	 * @param methodName
	 * @return
	 */
	public static boolean isGetterGetMethod(String methodName) {
		return methodName.startsWith(ClassUtils.GET_PREFIX_METHOD);
	}

	/**
	 * Return true if method is setter and false otherwise.
	 * 
	 * @param methodName
	 * @return
	 */
	public static boolean isSetterMethod(String methodName) {
		return methodName.startsWith(SET_PREFIX_METHOD);
	}

	/**
	 * Return true if methodName is 'set', 'get' or 'is'
	 * 
	 * @param methodName
	 * @return
	 */
	public static boolean isOnlyGetSetIs(String methodName) {
		return methodName.equals(SET_PREFIX_METHOD)
				|| methodName.equals(GET_PREFIX_METHOD)
				|| methodName.equals(IS_PREFIX_METHOD);
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
