/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation;

import static org.eclipse.core.databinding.pojo.bindable.internal.util.StringUtils.EMPTY_STRING;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 
 * {@link AnnotationAdapter} which get values of the String array declared into
 * an annotation. Ex : @Bindable(computedProperties={"name", "description"})
 * 
 */
public class AnnotationStringArrayAdapter extends AnnotationAdapter {

	// Array name (ex : computedProperties for
	// @Bindable(computedProperties={"name", "description"}))
	protected String arrayName;
	// Values getted from the array annotation (ex ["name", "description"] for
	// @Bindable(computedProperties={"name", "description"}))
	private Collection<String> values = null;

	public AnnotationStringArrayAdapter(String arrayName, AnnotationAdapter av) {
		super(av);
		this.arrayName = arrayName;
	}

	@Override
	public void visit(String name, Object value) {
		// Build the string array values.
		// NOTE : name is null, so super.visit MUST NOT be called!
		if (value != null) {
			if (values == null) {
				values = new ArrayList<String>();
			}
			values.add(value.toString());
		}
	}

	private Collection<String> getValues() {
		if (values == null) {
			return Collections.emptyList();
		}
		return values;
	}

	@Override
	public void visitEnd() {
		super.visitEnd();
		// End of visit of the string array property of the annotation
		// set the string array as values.
		((AnnotationAdapter) av).setValues(arrayName, getValues().toArray(
				EMPTY_STRING));
	}

}
