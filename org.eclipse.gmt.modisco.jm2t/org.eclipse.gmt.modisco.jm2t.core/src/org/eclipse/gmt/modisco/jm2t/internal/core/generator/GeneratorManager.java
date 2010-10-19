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
package org.eclipse.gmt.modisco.jm2t.internal.core.generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGenerator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorManager;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelProvider;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelProviderType;
import org.eclipse.gmt.modisco.jm2t.internal.core.Trace;

/**
 * Generator manage implementation.
 * 
 */
public class GeneratorManager implements IGeneratorManager,
		IRegistryChangeListener {

	private static final String EXTENSION_GENERATOR_TYPE = "generatorTypes";
	private static final String EXTENSION_MODEL_PROVIDER_TYPE = "modelProviderTypes";

	public static GeneratorManager INSTANCE = new GeneratorManager();

	// cached copy of all generator and configuration types
	private List<IGeneratorType> generatorTypes;
	// cached copy of all generator and configuration types
	private List<IModelProviderType> modelProviderTypes;
	private boolean registryListenerIntialized;

	protected GeneratorManager() {
		this.registryListenerIntialized = false;
	}

	public static GeneratorManager getManager() {
		return INSTANCE;
	}

	public void generate(final Object model,
			final IGeneratorConfiguration generatorConfiguration) {

		IGenerator generator = generatorConfiguration.getGeneratorType()
				.getGenerator();
		IModelProvider modelProvider = generatorConfiguration
				.getGeneratorType().getModelProviderType().getModelProvider();

		Object m = model;
		// Get model.
		if (modelProvider != null) {
			m = modelProvider.getModel(model);
		}
		System.out.println("Generate" + generatorConfiguration.getName());
		generator.generate(m, generatorConfiguration);

	}

	public void registryChanged(final IRegistryChangeEvent event) {
		IExtensionDelta[] deltas = event.getExtensionDeltas(JM2TCore.PLUGIN_ID,
				EXTENSION_GENERATOR_TYPE);
		if (deltas != null) {
			for (IExtensionDelta delta : deltas)
				handleGeneratorTypeDelta(delta);
		}
		deltas = event.getExtensionDeltas(JM2TCore.PLUGIN_ID,
				EXTENSION_MODEL_PROVIDER_TYPE);
		if (deltas != null) {
			for (IExtensionDelta delta : deltas)
				handleModelProviderTypeDelta(delta);
		}
	}

	// --------------------- Generator types

	/**
	 * Returns an array of all known generator types.
	 * <p>
	 * A new array is returned on each call, so clients may store or modify the
	 * result.
	 * </p>
	 * 
	 * @return the array of generator types {@link IGeneratorType}
	 */
	public IGeneratorType[] getGeneratorTypes() {
		if (generatorTypes == null)
			loadGeneratorTypes();

		IGeneratorType[] st = new IGeneratorType[generatorTypes.size()];
		generatorTypes.toArray(st);
		return st;
	}

	/**
	 * Returns the generator type with the given id, or <code>null</code> if
	 * none. This convenience method searches the list of known generator types
	 * ({@link #getGeneratorTypes()}) for the one with a matching generator type
	 * id ({@link IGeneratorType#getId()}). The id may not be null.
	 * 
	 * @param id
	 *            the generator type id
	 * @return the generator type, or <code>null</code> if there is no generator
	 *         type with the given id
	 */
	public IGeneratorType findGeneratorType(String id) {
		if (id == null)
			throw new IllegalArgumentException();

		if (generatorTypes == null)
			loadGeneratorTypes();

		Iterator<IGeneratorType> iterator = generatorTypes.iterator();
		while (iterator.hasNext()) {
			IGeneratorType generatorType = (IGeneratorType) iterator.next();
			if (id.equals(generatorType.getId()))
				return generatorType;
		}
		return null;
	}

	/**
	 * Load the generator types.
	 */
	private synchronized void loadGeneratorTypes() {
		if (generatorTypes != null)
			return;

		Trace.trace(Trace.EXTENSION_POINT,
				"->- Loading .generatorTypes extension point ->-");

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] cf = registry.getConfigurationElementsFor(
				JM2TCore.PLUGIN_ID, EXTENSION_GENERATOR_TYPE);
		List<IGeneratorType> list = new ArrayList<IGeneratorType>(cf.length);
		addGeneratorTypes(cf, list);
		addRegistryListenerIfNeeded();
		generatorTypes = list;

		Trace.trace(Trace.EXTENSION_POINT,
				"-<- Done loading .generatorTypes extension point -<-");
	}

	/**
	 * Load the generator types.
	 */
	private synchronized void addGeneratorTypes(IConfigurationElement[] cf,
			List<IGeneratorType> list) {
		for (IConfigurationElement ce : cf) {
			try {
				list.add(new GeneratorType(ce));
				Trace.trace(Trace.EXTENSION_POINT, "  Loaded generatorType: "
						+ ce.getAttribute("id"));
			} catch (Throwable t) {
				Trace.trace(Trace.SEVERE, "  Could not load generatorType: "
						+ ce.getAttribute("id"), t);
			}
		}
	}

	protected void handleGeneratorTypeDelta(IExtensionDelta delta) {
		if (generatorTypes == null) // not loaded yet
			return;

		IConfigurationElement[] cf = delta.getExtension()
				.getConfigurationElements();

		List<IGeneratorType> list = new ArrayList<IGeneratorType>(
				generatorTypes);
		if (delta.getKind() == IExtensionDelta.ADDED) {
			addGeneratorTypes(cf, list);
		} else {
			int size = list.size();
			GeneratorType[] st = new GeneratorType[size];
			list.toArray(st);
			int size2 = cf.length;

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size2; j++) {
					if (st[i].getId().equals(cf[j].getAttribute("id"))) {
						st[i].dispose();
						list.remove(st[i]);
					}
				}
			}
		}
		generatorTypes = list;
	}

	// --------------------- ModelProvider types

	/**
	 * Returns an array of all known modelProvider types.
	 * <p>
	 * A new array is returned on each call, so clients may store or modify the
	 * result.
	 * </p>
	 * 
	 * @return the array of modelProvider types {@link IModelProviderType}
	 */
	public IModelProviderType[] getModelProviderTypes() {
		if (modelProviderTypes == null)
			loadModelProviderTypes();

		IModelProviderType[] st = new IModelProviderType[modelProviderTypes
				.size()];
		modelProviderTypes.toArray(st);
		return st;
	}

	/**
	 * Returns the modelProvider type with the given id, or <code>null</code> if
	 * none. This convenience method searches the list of known modelProvider
	 * types ({@link #getModelProviderTypes()}) for the one with a matching
	 * modelProvider type id ({@link IModelProviderType#getId()}). The id may
	 * not be null.
	 * 
	 * @param id
	 *            the modelProvider type id
	 * @return the modelProvider type, or <code>null</code> if there is no
	 *         modelProvider type with the given id
	 */
	public IModelProviderType findModelProviderType(String id) {
		if (id == null)
			throw new IllegalArgumentException();

		if (modelProviderTypes == null)
			loadModelProviderTypes();

		Iterator<IModelProviderType> iterator = modelProviderTypes.iterator();
		while (iterator.hasNext()) {
			IModelProviderType modelProviderType = (IModelProviderType) iterator
					.next();
			if (id.equals(modelProviderType.getId()))
				return modelProviderType;
		}
		return null;
	}

	/**
	 * Load the modelProvider types.
	 */
	private synchronized void loadModelProviderTypes() {
		if (modelProviderTypes != null)
			return;

		Trace.trace(Trace.EXTENSION_POINT,
				"->- Loading .modelProviderTypes extension point ->-");

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] cf = registry.getConfigurationElementsFor(
				JM2TCore.PLUGIN_ID, EXTENSION_MODEL_PROVIDER_TYPE);
		List<IModelProviderType> list = new ArrayList<IModelProviderType>(
				cf.length);
		addModelProviderTypes(cf, list);
		addRegistryListenerIfNeeded();
		modelProviderTypes = list;

		Trace.trace(Trace.EXTENSION_POINT,
				"-<- Done loading .modelProviderTypes extension point -<-");
	}

	/**
	 * Load the modelProvider types.
	 */
	private synchronized void addModelProviderTypes(IConfigurationElement[] cf,
			List<IModelProviderType> list) {
		for (IConfigurationElement ce : cf) {
			try {
				list.add(new ModelProviderType(ce));
				Trace.trace(Trace.EXTENSION_POINT,
						"  Loaded modelProviderType: " + ce.getAttribute("id"));
			} catch (Throwable t) {
				Trace.trace(
						Trace.SEVERE,
						"  Could not load modelProviderType: "
								+ ce.getAttribute("id"), t);
			}
		}
	}

	protected void handleModelProviderTypeDelta(IExtensionDelta delta) {
		if (modelProviderTypes == null) // not loaded yet
			return;

		IConfigurationElement[] cf = delta.getExtension()
				.getConfigurationElements();

		List<IModelProviderType> list = new ArrayList<IModelProviderType>(
				modelProviderTypes);
		if (delta.getKind() == IExtensionDelta.ADDED) {
			addModelProviderTypes(cf, list);
		} else {
			int size = list.size();
			ModelProviderType[] st = new ModelProviderType[size];
			list.toArray(st);
			int size2 = cf.length;

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size2; j++) {
					if (st[i].getId().equals(cf[j].getAttribute("id"))) {
						st[i].dispose();
						list.remove(st[i]);
					}
				}
			}
		}
		modelProviderTypes = list;
	}

	private void addRegistryListenerIfNeeded() {
		if (registryListenerIntialized)
			return;

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		registry.addRegistryChangeListener(this, JM2TCore.PLUGIN_ID);
		registryListenerIntialized = true;
	}

	public void initialize() {

	}

	public void destroy() {
		Platform.getExtensionRegistry().removeRegistryChangeListener(this);
	}

}
