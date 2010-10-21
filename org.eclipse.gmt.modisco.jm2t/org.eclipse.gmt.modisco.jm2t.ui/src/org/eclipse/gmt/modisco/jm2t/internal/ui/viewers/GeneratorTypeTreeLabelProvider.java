/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.jm2t.internal.ui.viewers;

import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.ImageResource;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;
/**
 * Generator type label provider.
 */
public class GeneratorTypeTreeLabelProvider extends AbstractTreeLabelProvider {
	/**
	 * GeneratorTypeTreeLabelProvider constructor comment.
	 */
	public GeneratorTypeTreeLabelProvider() {
		super();
	}

	/**
	 * GeneratorTypeTreeLabelProvider constructor comment.
	 * 
	 * @param decorator a label decorator, or null if no decorator is required
	 */
	public GeneratorTypeTreeLabelProvider(ILabelDecorator decorator) {
		super(decorator);
	}

	/**
	 * 
	 */
	protected Image getImageImpl(Object element) {
		IGeneratorType generatorType = (IGeneratorType) element;
		return ImageResource.getImage(generatorType.getId());
	}

	/**
	 * 
	 */
	protected String getTextImpl(Object element) {
		IGeneratorType generatorType = (IGeneratorType) element;
		return notNull(generatorType.getName());
	}
}