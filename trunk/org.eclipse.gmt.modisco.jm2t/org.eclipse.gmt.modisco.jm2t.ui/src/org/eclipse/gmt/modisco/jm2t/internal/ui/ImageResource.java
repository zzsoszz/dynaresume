/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.jm2t.internal.ui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionDelta;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmt.modisco.jm2t.core.util.StringUtils;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Utility class to handle image resources.
 */
public class ImageResource {
	// the image registry
	private static ImageRegistry imageRegistry;

	// map of image descriptors since these
	// will be lost by the image registry
	private static Map<String, ImageDescriptor> imageDescriptors;

	// base urls for images
	private static URL ICON_BASE_URL;

	private static final String URL_OBJ = "obj16/";

	// General Object Images
	public static final String IMG_GENERATOR = "generator";
	public static final String IMG_MODEL_CONVERTER = "modelConverter";	

	static {
		try {
			String pathSuffix = "icons/";
			ICON_BASE_URL = JM2TUI.getDefault().getBundle()
					.getEntry(pathSuffix);
		} catch (Exception e) {
			Trace.trace(Trace.SEVERE, "Could not set icon base URL", e);
		}
	}

	/**
	 * Cannot construct an ImageResource. Use static methods only.
	 */
	private ImageResource() {
		// do nothing
	}

	/**
	 * Dispose of element images that were created.
	 */
	protected static void dispose() {
		// do nothing
	}

	/**
	 * Return the image with the given key.
	 * 
	 * @param key
	 *            java.lang.String
	 * @return org.eclipse.swt.graphics.Image
	 */
	public static Image getImage(String key) {
		return getImage(key, null);
	}

	/**
	 * Return the image with the given key.
	 * 
	 * @param key
	 *            java.lang.String
	 * @return org.eclipse.swt.graphics.Image
	 */
	public static Image getImage(String key, String keyIfImageNull) {
		if (imageRegistry == null)
			initializeImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null) {
			if (keyIfImageNull != null) {
				return getImage(keyIfImageNull, null);
			}
			imageRegistry.put(key, ImageDescriptor.getMissingImageDescriptor());
			image = imageRegistry.get(key);
		}
		return image;
	}

	/**
	 * Return the image descriptor with the given key.
	 * 
	 * @param key
	 *            java.lang.String
	 * @return org.eclipse.jface.resource.ImageDescriptor
	 */
	public static ImageDescriptor getImageDescriptor(String key) {
		if (imageRegistry == null)
			initializeImageRegistry();
		ImageDescriptor id = imageDescriptors.get(key);
		if (id != null)
			return id;

		return ImageDescriptor.getMissingImageDescriptor();
	}

	/**
	 * Initialize the image resources.
	 */
	protected static void initializeImageRegistry() {
		imageRegistry = new ImageRegistry();
		imageDescriptors = new HashMap<String, ImageDescriptor>();

		// load general object images
		registerImage(IMG_GENERATOR, URL_OBJ + "generator.gif");
		registerImage(IMG_MODEL_CONVERTER, URL_OBJ + "modelConverter.gif");
		
		loadGeneratorImages();

		// PlatformUI
		// .getWorkbench()
		// .getProgressService()
		// .registerIconForFamily(getImageDescriptor(IMG_SERVER),
		// GeneratorUtil.SERVER_JOB_FAMILY);
	}

	/**
	 * Register an image with the registry.
	 * 
	 * @param key
	 *            java.lang.String
	 * @param partialURL
	 *            java.lang.String
	 */
	private static void registerImage(String key, String partialURL) {
		try {
			ImageDescriptor id = ImageDescriptor.createFromURL(new URL(
					ICON_BASE_URL, partialURL));
			imageRegistry.put(key, id);
			imageDescriptors.put(key, id);
		} catch (Exception e) {
			Trace.trace(Trace.SEVERE, "Error registering image " + key
					+ " from " + partialURL, e);
		}
	}

	/**
	 * Load the generator images.
	 */
	private static void loadGeneratorImages() {
		Trace.trace(Trace.CONFIG,
				"->- Loading .generatorImages extension point ->-");
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		loadGeneratorImages(registry.getConfigurationElementsFor(
				JM2TUI.PLUGIN_ID, JM2TUI.EXTENSION_GENERATOR_IMAGES));
		JM2TUI.addRegistryListener();
		Trace.trace(Trace.CONFIG,
				"-<- Done loading .generatorImages extension point -<-");
	}

	/**
	 * Load the generator images.
	 */
	private static void loadGeneratorImages(IConfigurationElement[] cf) {
		int size = cf.length;
		for (int i = 0; i < size; i++) {
			try {
				String name = cf[i].getDeclaringExtension().getContributor()
						.getName();
				String iconPath = cf[i].getAttribute("icon");
				ImageDescriptor imageDescriptor = AbstractUIPlugin
						.imageDescriptorFromPlugin(name, iconPath);
				if (imageDescriptor == null && iconPath != null
						&& iconPath.length() > 0)
					imageDescriptor = ImageDescriptor
							.getMissingImageDescriptor();

				if (imageDescriptor != null) {
					String[] typeIds = StringUtils.tokenize(
							cf[i].getAttribute("typeIds"), ",");
					int size2 = typeIds.length;
					for (int j = 0; j < size2; j++) {
						imageRegistry.put(typeIds[j], imageDescriptor);
						imageDescriptors.put(typeIds[j], imageDescriptor);
					}
				}
				Trace.trace(Trace.CONFIG,
						"  Loaded generatorImage: " + cf[i].getAttribute("id"));
			} catch (Throwable t) {
				Trace.trace(Trace.SEVERE, "  Could not load generatorImage: "
						+ cf[i].getAttribute("id"), t);
			}
		}
	}

	protected static void handleGeneratorImageDelta(IExtensionDelta delta) {
		if (imageRegistry == null) // not loaded yet
			return;

		IConfigurationElement[] cf = delta.getExtension()
				.getConfigurationElements();

		if (delta.getKind() == IExtensionDelta.ADDED)
			loadGeneratorImages(cf);
		else {
			int size = cf.length;
			for (int i = 0; i < size; i++) {
				String typeId = cf[i].getAttribute("typeIds");
				imageRegistry.remove(typeId);
				imageDescriptors.remove(typeId);
			}
		}
	}
}