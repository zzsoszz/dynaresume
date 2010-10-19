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
package org.eclipse.gmt.modisco.jm2t.internal.ui.viewers;

import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Generator configuration table label provider.
 */
public class GeneratorConfigurationTableLabelProvider extends BaseLabelProvider
		implements ITableLabelProvider {

	/**
	 * GeneratorConfigurationTableLabelProvider constructor comment.
	 */
	public GeneratorConfigurationTableLabelProvider() {
		super();
	}

	/**
	 * GeneratorConfigurationTableLabelProvider constructor comment.
	 * 
	 * @param decorator
	 *            a label decorator, or null if no decorator is required
	 */
	public GeneratorConfigurationTableLabelProvider(ILabelDecorator decorator) {
		super(decorator);
	}

	/**
	 * @see ITableLabelProvider#getColumnImage(Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 0) {
			IGeneratorConfiguration runtime = (IGeneratorConfiguration) element;
			// IGeneratorLaunchConfigurationType runtimeType =
			// runtime.getGeneratorLaunchConfigurationType();
			// if (runtimeType != null) {
			// Image image = ImageResource.getImage(runtimeType.getId());
			// if (decorator != null) {
			// Image dec = decorator.decorateImage(image, element);
			// if (dec != null)
			// return dec;
			// }
			// return image;
			// }
		}
		return null;
	}

	/**
	 * @see ITableLabelProvider#getColumnText(Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		IGeneratorConfiguration runtime = (IGeneratorConfiguration) element;
		if (columnIndex == 0) {
			return getDecoratedText(runtime.getName(), element);
		} else if (columnIndex == 1) {
			IGeneratorType generatorType = runtime.getGeneratorType();
			if (generatorType != null) {
				return getDecoratedText(generatorType.getName(), element);	
			}
			return "Unknown";
		} else
			return "";
	}

	public boolean isLocked(Object element) {
		return false;
	}
}
