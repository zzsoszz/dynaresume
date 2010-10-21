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
package org.eclipse.gmt.modisco.jm2t.internal.ui.viewers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.JM2TUI;

/**
 * Generator type content provider.
 */
public class GeneratorTypeTreeContentProvider extends AbstractTreeContentProvider {
	protected boolean creation;
	protected String type;
	protected String version;
	protected String generatorTypeId;

	/**
	 * GeneratorTypeContentProvider constructor.
	 * 
	 * @param creation true to include generators that can be created
	 */
	public GeneratorTypeTreeContentProvider(boolean creation) {
		super();
		this.creation = creation;
	}

	public GeneratorTypeTreeContentProvider(boolean creation, String type, String version, String generatorTypeId) {
		super(false);
		this.type = type;
		this.version = version;
		this.generatorTypeId = generatorTypeId;
		this.creation = creation;
		
		fillTree();
	}

	public void fillTree() {
		clean();
		List<TreeElement> list = new ArrayList<TreeElement>();
		IGeneratorType[] generatorTypes = JM2TCore.getGeneratorManager().getGeneratorTypes();//ServerUtil.getGeneratorTypes(type, version, generatorTypeId);
		if (generatorTypes != null) {
			int size = generatorTypes.length;
			for (int i = 0; i < size; i++) {
				IGeneratorType generatorType = generatorTypes[i];
				//if (!creation/* || generatorType.canCreate()*/) {
					TreeElement ele = getOrCreate(list, generatorType.getVendor());
					ele.contents.add(generatorType);
					elementToParentMap.put(generatorType, ele);
				//}
			}
		}
		elements = list.toArray();
	}
}