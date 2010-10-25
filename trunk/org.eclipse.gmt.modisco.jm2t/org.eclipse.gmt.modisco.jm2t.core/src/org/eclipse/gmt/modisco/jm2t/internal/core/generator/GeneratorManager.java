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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IRegistryChangeEvent;
import org.eclipse.core.runtime.IRegistryChangeListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.GeneratorException;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGenerator;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorManager;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverter;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterCategoryType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.core.Trace;

/**
 * Generator manage implementation.
 * 
 */
public class GeneratorManager implements IGeneratorManager,
		IRegistryChangeListener {

	private static final String EXTENSION_GENERATOR_TYPE = "generatorTypes";
	private static final String EXTENSION_MODEL_CONVERTER_TYPE = "modelConverterTypes";
	private static final String EXTENSION_MODEL_CONVERTER_CATEGORY_TYPE = "modelConverterCategoryTypes";

	public static GeneratorManager INSTANCE = new GeneratorManager();

	// cached copy of all generator and configuration types
	private List<IGeneratorType> generatorTypes;
	// cached copy of all model converter
	private List<IModelConverterType> modelConverterTypes;
	// cached copy of all model converter category types
	private List<IModelConverterCategoryType> modelConverterCategoryTypes;

	private boolean registryListenerIntialized;

	protected GeneratorManager() {
		this.registryListenerIntialized = false;
	}

	public static GeneratorManager getManager() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorManager#generate
	 * (java.lang.Object,
	 * org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration)
	 */
	public void generate(final Object model,
			final IGeneratorConfiguration configuration)
			throws GeneratorException {

		IGenerator generator = configuration.getGeneratorType().getGenerator();
		IModelConverter modelConverter = configuration.getModelConverterType()
				.getModelConverter();

		Object convertedModel = model;
		// Get model.
		if (modelConverter != null) {
			convertedModel = modelConverter.convertModel(model, configuration);
		}
		if (model == null) {
			// throw ex;
		}
		IPath templatePath = configuration.getTemplatePath();
		if (templatePath == null) {
			// throw ex;
		}
		IPath targetContainerPath = configuration.getTargetContainerPath();
		if (targetContainerPath == null) {
			// throw ex;
		}
		generator.generate(convertedModel, configuration);

	}

	public void registryChanged(final IRegistryChangeEvent event) {
		IExtensionDelta[] deltas = event.getExtensionDeltas(JM2TCore.PLUGIN_ID,
				EXTENSION_GENERATOR_TYPE);
		if (deltas != null) {
			for (IExtensionDelta delta : deltas)
				handleGeneratorTypeDelta(delta);
		}
		deltas = event.getExtensionDeltas(JM2TCore.PLUGIN_ID,
				EXTENSION_MODEL_CONVERTER_TYPE);
		if (deltas != null) {
			for (IExtensionDelta delta : deltas)
				handleModelConverterTypeDelta(delta);
		}
		deltas = event.getExtensionDeltas(JM2TCore.PLUGIN_ID,
				EXTENSION_MODEL_CONVERTER_CATEGORY_TYPE);
		if (deltas != null) {
			for (IExtensionDelta delta : deltas)
				handleModelConverterCategoryTypeDelta(delta);
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

	// --------------------- ModelConverter types

	/**
	 * Returns an array of all known modelConverter types.
	 * <p>
	 * A new array is returned on each call, so clients may store or modify the
	 * result.
	 * </p>
	 * 
	 * @return the array of modelConverter types {@link IModelConverterType}
	 */
	public IModelConverterType[] getModelConverterTypes() {
		if (modelConverterTypes == null)
			loadModelConverterTypes();

		IModelConverterType[] st = new IModelConverterType[modelConverterTypes
				.size()];
		modelConverterTypes.toArray(st);
		return st;
	}

	/**
	 * Returns the modelConverter type with the given id, or <code>null</code>
	 * if none. This convenience method searches the list of known
	 * modelConverter types ({@link #getModelConverterTypes()}) for the one with
	 * a matching modelConverter type id ({@link IModelConverterType#getId()}).
	 * The id may not be null.
	 * 
	 * @param id
	 *            the modelConverter type id
	 * @return the modelConverter type, or <code>null</code> if there is no
	 *         modelConverter type with the given id
	 */
	public IModelConverterType findModelConverterType(String id) {
		if (id == null)
			throw new IllegalArgumentException();

		if (modelConverterTypes == null)
			loadModelConverterTypes();

		Iterator<IModelConverterType> iterator = modelConverterTypes.iterator();
		while (iterator.hasNext()) {
			IModelConverterType modelConverterType = (IModelConverterType) iterator
					.next();
			if (id.equals(modelConverterType.getId()))
				return modelConverterType;
		}
		return null;
	}

	public void findModelConverterTypesByCategory(String category,
			List<IModelConverterType> filteredModelConverterTypes) {
		if (category == null)
			throw new IllegalArgumentException();

		if (modelConverterTypes == null)
			loadModelConverterTypes();
		Iterator<IModelConverterType> iterator = modelConverterTypes.iterator();
		while (iterator.hasNext()) {
			IModelConverterType modelConverterType = (IModelConverterType) iterator
					.next();
			if (category.equals(modelConverterType.getCategoryId())) {
				filteredModelConverterTypes.add(modelConverterType);
			}
		}
	}

	/**
	 * Load the modelConverter types.
	 */
	private synchronized void loadModelConverterTypes() {
		if (modelConverterTypes != null)
			return;

		Trace.trace(Trace.EXTENSION_POINT,
				"->- Loading .modelConverterTypes extension point ->-");

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] cf = registry.getConfigurationElementsFor(
				JM2TCore.PLUGIN_ID, EXTENSION_MODEL_CONVERTER_TYPE);
		List<IModelConverterType> list = new ArrayList<IModelConverterType>(
				cf.length);
		addModelConverterTypes(cf, list);
		addRegistryListenerIfNeeded();
		modelConverterTypes = list;

		Trace.trace(Trace.EXTENSION_POINT,
				"-<- Done loading .modelConverterTypes extension point -<-");
	}

	/**
	 * Load the modelConverter types.
	 */
	private synchronized void addModelConverterTypes(
			IConfigurationElement[] cf, List<IModelConverterType> list) {
		for (IConfigurationElement ce : cf) {
			try {
				list.add(new ModelConverterType(ce));
				Trace.trace(Trace.EXTENSION_POINT,
						"  Loaded modelConverterType: " + ce.getAttribute("id"));
			} catch (Throwable t) {
				Trace.trace(
						Trace.SEVERE,
						"  Could not load modelConverterType: "
								+ ce.getAttribute("id"), t);
			}
		}
	}

	protected void handleModelConverterTypeDelta(IExtensionDelta delta) {
		if (modelConverterTypes == null) // not loaded yet
			return;

		IConfigurationElement[] cf = delta.getExtension()
				.getConfigurationElements();

		List<IModelConverterType> list = new ArrayList<IModelConverterType>(
				modelConverterTypes);
		if (delta.getKind() == IExtensionDelta.ADDED) {
			addModelConverterTypes(cf, list);
		} else {
			int size = list.size();
			ModelConverterType[] st = new ModelConverterType[size];
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
		modelConverterTypes = list;
	}

	// --------------------- ModelConverterCategory types

	/**
	 * Returns an array of all known modelConverterCategory types.
	 * <p>
	 * A new array is returned on each call, so clients may store or modify the
	 * result.
	 * </p>
	 * 
	 * @return the array of modelConverterCategory types
	 *         {@link IModelConverterCategoryType}
	 */
	public IModelConverterCategoryType[] getModelConverterCategoryTypes() {
		if (modelConverterCategoryTypes == null)
			loadModelConverterCategoryTypes();

		IModelConverterCategoryType[] st = new IModelConverterCategoryType[modelConverterCategoryTypes
				.size()];
		modelConverterCategoryTypes.toArray(st);
		return st;
	}

	/**
	 * Returns the modelConverterCategory type with the given id, or
	 * <code>null</code> if none. This convenience method searches the list of
	 * known modelConverterCategory types (
	 * {@link #getModelConverterCategoryTypes()}) for the one with a matching
	 * modelConverterCategory type id (
	 * {@link IModelConverterCategoryType#getId()}). The id may not be null.
	 * 
	 * @param id
	 *            the modelConverterCategory type id
	 * @return the modelConverterCategory type, or <code>null</code> if there is
	 *         no modelConverterCategory type with the given id
	 */
	public IModelConverterCategoryType findModelConverterCategoryType(String id) {
		if (id == null)
			throw new IllegalArgumentException();

		if (modelConverterCategoryTypes == null)
			loadModelConverterCategoryTypes();

		Iterator<IModelConverterCategoryType> iterator = modelConverterCategoryTypes
				.iterator();
		while (iterator.hasNext()) {
			IModelConverterCategoryType modelConverterCategoryType = (IModelConverterCategoryType) iterator
					.next();
			if (id.equals(modelConverterCategoryType.getId()))
				return modelConverterCategoryType;
		}
		return null;
	}

	/**
	 * Load the modelConverterCategory types.
	 */
	private synchronized void loadModelConverterCategoryTypes() {
		if (modelConverterCategoryTypes != null)
			return;

		Trace.trace(Trace.EXTENSION_POINT,
				"->- Loading .modelConverterCategoryTypes extension point ->-");

		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] cf = registry.getConfigurationElementsFor(
				JM2TCore.PLUGIN_ID, EXTENSION_MODEL_CONVERTER_CATEGORY_TYPE);
		List<IModelConverterCategoryType> list = new ArrayList<IModelConverterCategoryType>(
				cf.length);
		addModelConverterCategoryTypes(cf, list);
		addRegistryListenerIfNeeded();
		modelConverterCategoryTypes = list;

		Trace.trace(Trace.EXTENSION_POINT,
				"-<- Done loading .modelConverterCategoryTypes extension point -<-");
	}

	/**
	 * Load the modelConverterCategory types.
	 */
	private synchronized void addModelConverterCategoryTypes(
			IConfigurationElement[] cf, List<IModelConverterCategoryType> list) {
		for (IConfigurationElement ce : cf) {
			try {
				list.add(new ModelConverterCategoryType(ce));
				Trace.trace(
						Trace.EXTENSION_POINT,
						"  Loaded modelConverterCategoryType: "
								+ ce.getAttribute("id"));
			} catch (Throwable t) {
				Trace.trace(
						Trace.SEVERE,
						"  Could not load modelConverterCategoryType: "
								+ ce.getAttribute("id"), t);
			}
		}
	}

	protected void handleModelConverterCategoryTypeDelta(IExtensionDelta delta) {
		if (modelConverterTypes == null) // not loaded yet
			return;

		IConfigurationElement[] cf = delta.getExtension()
				.getConfigurationElements();

		List<IModelConverterCategoryType> list = new ArrayList<IModelConverterCategoryType>(
				modelConverterCategoryTypes);
		if (delta.getKind() == IExtensionDelta.ADDED) {
			addModelConverterCategoryTypes(cf, list);
		} else {
			int size = list.size();
			ModelConverterCategoryType[] st = new ModelConverterCategoryType[size];
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
		modelConverterCategoryTypes = list;
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
