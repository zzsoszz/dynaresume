/*****************************************************************************
* Copyright (c) 2009
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     Angelo Zerr <angelo.zerr@gmail.com>
*     Pascal Leclercq <pascal.leclercq@gmail.com>
*******************************************************************************/

package org.akrogen.dynaresume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

class GestcvContext {

	private Collection<Locale> supportedLocales = null; // Locales Supported by SAFARI
	private String realPath;
	
	private GestcvContext() {
		
	}
	
	private static GestcvContext gestcvContext = null;
	public static GestcvContext getInstance() {
		if(gestcvContext == null) {
			gestcvContext = new GestcvContext();
		}
		return gestcvContext;
	}
	
	/**
	 * Return Supported Locale of SAFARI Application 
	 * @return Collection of Locale
	 */	
	public Collection<Locale> getSupportedLocales() {
		// Today Locale supported are France and English		
		if (supportedLocales == null) {
			supportedLocales = new ArrayList<Locale>();
			
			supportedLocales.add(new Locale("FR"));
			supportedLocales.add(new Locale("EN"));
		}		
		return supportedLocales;
	}
	
	/**
	 * @return Returns the realPath.
	 */
	public String getRealPath() {
		return realPath;
	}
	/**
	 * @param realPath The realPath to set.
	 */
	public void setRealPath(String path) {
		realPath = path;
	}
}
