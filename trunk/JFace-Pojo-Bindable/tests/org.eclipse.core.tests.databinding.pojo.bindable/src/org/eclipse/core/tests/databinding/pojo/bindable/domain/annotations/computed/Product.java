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
package org.eclipse.core.tests.databinding.pojo.bindable.domain.annotations.computed;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;

/**
 * 
 * Computed values with Object {@link Locale}.
 */
public class Product {

	private volatile Locale currentLocale;
	private StringI18N nameI18N = new StringI18N(true);
	private StringI18N descriptionI18N = new StringI18N(false);

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setName(String name) {
		nameI18N.set(name, currentLocale);
	}

	@Bindable(dependsOn = ("currentLocale"))
	public String getName() {
		return nameI18N.get(currentLocale);
	}

	public void setDescription(String description) {
		descriptionI18N.set(description, currentLocale);
	}

	@Bindable(dependsOn = ("currentLocale"))
	public String getDescription() {
		return descriptionI18N.get(currentLocale);
	}

	private class StringI18N {

		private Map<Locale, String> values = new HashMap<Locale, String>();

		public StringI18N(boolean name) {
			String s = (name ? "name" : "description");
			add(Locale.FRENCH, s);
			add(Locale.ENGLISH, s);
		}

		private void add(Locale locale, String s) {
			values.put(locale, s + "_" + locale.getLanguage());
		}

		public String get(Locale currentLocale) {
			return values.get(currentLocale);
		}

		public void set(String name, Locale currentLocale) {
			values.put(currentLocale, name);
		}

	}
}
