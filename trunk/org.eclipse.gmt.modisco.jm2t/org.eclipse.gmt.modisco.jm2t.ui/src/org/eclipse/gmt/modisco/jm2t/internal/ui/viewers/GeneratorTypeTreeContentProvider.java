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

import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;

/**
 * Generator type content provider.
 */
public class GeneratorTypeTreeContentProvider extends
		AbstractTreeContentProvider {
	/**
	 * GeneratorTypeContentProvider constructor.
	 * 
	 */
	public GeneratorTypeTreeContentProvider() {
		super(false);

		fillTree();
	}

	public void fillTree() {
		clean();
		List<TreeElement> list = new ArrayList<TreeElement>();
		IGeneratorType[] generatorTypes = JM2TCore.getGeneratorManager()
				.getGeneratorTypes();
		if (generatorTypes != null) {
			int size = generatorTypes.length;
			for (int i = 0; i < size; i++) {
				IGeneratorType generatorType = generatorTypes[i];
				TreeElement ele = getOrCreate(list, generatorType.getVendor());
				ele.contents.add(generatorType);
				elementToParentMap.put(generatorType, ele);
			}
		}
		elements = list.toArray();
	}
}