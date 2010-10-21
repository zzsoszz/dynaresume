/*******************************************************************************
 * Copyright (c) 2010 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.jm2t.internal.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmt.modisco.jm2t.core.util.StringUtils;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JM2TUI extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.gmt.modisco.jm2t.ui"; //$NON-NLS-1$

	private static final String EXTENSION_WIZARD_FRAGMENTS = "wizardFragments";

	public static final String EXTENSION_JM2T_IMAGES = "images";

	// The shared instance
	private static JM2TUI plugin;

	private static RegistryChangeListener registryListener;

	// cached copy of all generator configurations wizards
	private static Map<String, WizardFragmentData> wizardFragments;

	static class WizardFragmentData {
		String id;
		IConfigurationElement ce;
		WizardFragment fragment;

		public WizardFragmentData(String id, IConfigurationElement ce) {
			this.id = id;
			this.ce = ce;
		}
	}

	protected static class RegistryChangeListener implements
			IRegistryChangeListener {

		public void registryChanged(IRegistryChangeEvent event) {
			IExtensionDelta[] deltas = event.getExtensionDeltas(
					JM2TUI.PLUGIN_ID, EXTENSION_WIZARD_FRAGMENTS);
			if (deltas != null) {
				for (int i = 0; i < deltas.length; i++) {
					handleWizardFragmentDelta(deltas[i]);
				}
			}

		}

	}

	/**
	 * The constructor
	 */
	public JM2TUI() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static JM2TUI getDefault() {
		return plugin;
	}

	/**
	 * Create error status with exception.
	 * 
	 * @param e
	 * @return
	 */
	public static IStatus createStatus(Throwable e) {
		return new Status(IStatus.ERROR, JM2TUI.PLUGIN_ID, e.getMessage(), e);
	}

	/**
	 * Returns the wizard fragment with the given id.
	 * 
	 * @param typeId
	 *            the server or runtime type id
	 * @return a wizard fragment, or <code>null</code> if none could be found
	 */
	public static WizardFragment getWizardFragment(String typeId) {
		if (typeId == null)
			return null;

		if (wizardFragments == null)
			loadWizardFragments();

		Iterator<String> iterator = wizardFragments.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (typeId.equals(key)) {
				WizardFragmentData data = wizardFragments.get(key);
				return getWizardFragment(data);
			}
		}
		return null;
	}

	protected static WizardFragment getWizardFragment(
			WizardFragmentData fragment) {
		if (fragment == null)
			return null;

		if (fragment.fragment == null) {
			try {
				long time = System.currentTimeMillis();
				fragment.fragment = (WizardFragment) fragment.ce
						.createExecutableExtension("class");
				Trace.trace(Trace.PERFORMANCE, "JM2TUI.getWizardFragment(): <"
						+ (System.currentTimeMillis() - time) + "> "
						+ fragment.ce.getAttribute("id"));
			} catch (Throwable t) {
				Trace.trace(Trace.SEVERE, "Could not create wizardFragment: "
						+ fragment.ce.getAttribute("id"), t);
			}
		}
		return fragment.fragment;
	}

	/**
	 * Load the wizard fragments.
	 */
	private static synchronized void loadWizardFragments() {
		if (wizardFragments != null)
			return;
		Trace.trace(Trace.CONFIG,
				"->- Loading .wizardFragments extension point ->-");
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] cf = registry.getConfigurationElementsFor(
				JM2TUI.PLUGIN_ID, EXTENSION_WIZARD_FRAGMENTS);

		Map<String, WizardFragmentData> map = new HashMap<String, WizardFragmentData>(
				cf.length);
		loadWizardFragments(cf, map);
		addRegistryListener();
		wizardFragments = map;

		Trace.trace(Trace.CONFIG,
				"-<- Done loading .wizardFragments extension point -<-");
	}

	public static synchronized void addRegistryListener() {
		if (registryListener != null)
			return;

		registryListener = new RegistryChangeListener();
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		registry.addRegistryChangeListener(registryListener, JM2TUI.PLUGIN_ID);
	}

	/**
	 * Load wizard fragments.
	 */
	private static synchronized void loadWizardFragments(
			IConfigurationElement[] cf, Map<String, WizardFragmentData> map) {
		for (int i = 0; i < cf.length; i++) {
			try {
				String id = cf[i].getAttribute("typeIds");
				String[] ids = StringUtils.tokenize(id, ",");
				int size = ids.length;
				for (int j = 0; j < size; j++)
					map.put(ids[j], new WizardFragmentData(id, cf[i]));
				Trace.trace(Trace.CONFIG, "  Loaded wizardFragment: " + id);
			} catch (Throwable t) {
				Trace.trace(Trace.SEVERE, "  Could not load wizardFragment: "
						+ cf[i].getAttribute("id"), t);
			}
		}
	}

	protected static void handleWizardFragmentDelta(IExtensionDelta delta) {
		if (wizardFragments == null) // not loaded yet
			return;

		IConfigurationElement[] cf = delta.getExtension()
				.getConfigurationElements();

		Map<String, WizardFragmentData> map = new HashMap<String, WizardFragmentData>(
				wizardFragments);
		if (delta.getKind() == IExtensionDelta.ADDED) {
			loadWizardFragments(cf, map);
		}
		wizardFragments = map;
	}
}
