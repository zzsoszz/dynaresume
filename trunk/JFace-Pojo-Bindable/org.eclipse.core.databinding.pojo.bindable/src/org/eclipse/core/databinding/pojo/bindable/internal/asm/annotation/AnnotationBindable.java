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

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.objectweb.asm.AnnotationVisitor;

/**
 * {@link AnnotationVisitor} implementation which get the information pof
 * {@link Bindable} annotation.
 * 
 */
public class AnnotationBindable extends AnnotationAdapter implements
		BindableAnnotationConstants {

	private AnnotationBindableAware annotationBindableAware = null;

	// Bindable value of property 'value' of Bindable annotation.
	private Boolean bindableValue = null;

	// Bindable value of property 'computedProperties' of Bindable annotation.
	private String[] computedProperties = null;

	public AnnotationBindable(AnnotationVisitor av,
			AnnotationBindableAware annotationBindableAware) {
		super(av);
		this.annotationBindableAware = annotationBindableAware;
	}

	@Override
	public void visit(String name, Object value) {
		if (BINDABLE_VALUE_ANNOTATION.equals(name)) {
			// value property is definied into @Bindable annotation, get it the
			// value
			bindableValue = (Boolean) value;
		}
		super.visit(name, value);
	}
	
	@Override
	public AnnotationVisitor visitArray(String name) {
		if (BINDABLE_COMPUTED_PROPERTIES_ANNOTATION.equals(name)) {
			// computedproperties is defined into @Bindable annotation, create 
			// AnnotationStringArrayAdapter to get values of computedproperties
			// When String array values are built, AnnotationBindable#setValues is called.
			return new AnnotationStringArrayAdapter(name, this);
		}
		return super.visitArray(name);
	}

	@Override
	public void visitEnd() {
		if (bindableValue != null) {
			// value property is definied into Bindable annotation, set the
			// value to the
			// AnnotationBindableAware (ClassBindable or MethodBindable)
			annotationBindableAware.setBindableAnnotationValue(bindableValue);
		}
		if (computedProperties != null) {
			// computedProperties property is definied into Bindable annotation, set the
			// computedProperties to the
			// AnnotationBindableAware (ClassBindable or MethodBindable)
			annotationBindableAware.setBindableAnnotationComputedProperties(computedProperties);
		}
		super.visitEnd();
	}
	
	@Override
	public void setValues(String arrayName, String[] values) {
		if (BINDABLE_COMPUTED_PROPERTIES_ANNOTATION.equals(arrayName)) {
			this.computedProperties = values;
		}
	}
}
