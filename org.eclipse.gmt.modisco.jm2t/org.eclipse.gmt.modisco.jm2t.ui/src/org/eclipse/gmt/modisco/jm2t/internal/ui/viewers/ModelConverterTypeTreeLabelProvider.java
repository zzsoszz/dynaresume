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

import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.ImageResource;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;

/**
 * ModelConverter type label provider.
 */
public class ModelConverterTypeTreeLabelProvider extends
		AbstractTreeLabelProvider {
	/**
	 * ModelConverterTypeTreeLabelProvider constructor comment.
	 */
	public ModelConverterTypeTreeLabelProvider() {
		super();
	}

	/**
	 * ModelConverterTypeTreeLabelProvider constructor comment.
	 * 
	 * @param decorator
	 *            a label decorator, or null if no decorator is required
	 */
	public ModelConverterTypeTreeLabelProvider(ILabelDecorator decorator) {
		super(decorator);
	}

	/**
	 * 
	 */
	protected Image getImageImpl(Object element) {
		IModelConverterType modelConverterType = (IModelConverterType) element;
		return ImageResource.getImage(modelConverterType.getId(), ImageResource.IMG_MODEL_CONVERTER);
	}

	/**
	 * 
	 */
	protected String getTextImpl(Object element) {
		IModelConverterType modelConverterType = (IModelConverterType) element;
		return notNull(modelConverterType.getName());
	}
}