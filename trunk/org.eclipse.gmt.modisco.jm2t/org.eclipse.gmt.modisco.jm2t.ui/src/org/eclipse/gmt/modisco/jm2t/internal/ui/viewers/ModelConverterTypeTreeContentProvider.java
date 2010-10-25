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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterCategoryType;
import org.eclipse.gmt.modisco.jm2t.core.generator.IModelConverterType;

/**
 * Model converter type content provider.
 */
public class ModelConverterTypeTreeContentProvider extends
		AbstractTreeContentProvider {

	private IGeneratorType generatorType;

	public ModelConverterTypeTreeContentProvider(IGeneratorType generatorType) {
		super(false);
		setGeneratorType(generatorType);
	}

	public void fillTree() {
		clean();
		List<TreeElement> list = new ArrayList<TreeElement>();
		if (generatorType != null) {
			IModelConverterType[] modelConverterTypes = generatorType
					.getSupportedModelConverterTypes();
			if (modelConverterTypes != null) {
				TreeElement ele = null;
				int size = modelConverterTypes.length;
				for (int i = 0; i < size; i++) {
					IModelConverterType modelConverterType = modelConverterTypes[i];
					IModelConverterCategoryType categoryType = modelConverterType
							.getCategoryType();
					if (categoryType != null) {
						ele = getOrCreate(list, categoryType.getName());
						ele.source = categoryType;
					} else {
						ele = getOrCreate(list,
								modelConverterType.getCategoryId());
					}
					ele.contents.add(modelConverterType);
					elementToParentMap.put(modelConverterType, ele);
				}
			}
		}
		elements = list.toArray();
	}

	public void setGeneratorType(IGeneratorType generatorType) {
		this.generatorType = generatorType;
		fillTree();
	}
}