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
package org.eclipse.core.databinding.pojo.bindable.internal.asm;

import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindable;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindableAware;
import org.eclipse.core.databinding.pojo.bindable.internal.util.ClassUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Getter method wich defined {@link Bindable} annotation to manage dependsOn.
 * 
 */
public class GetterMethodBindable extends MethodAdapter implements Opcodes,
		BindableSignatureConstants, AnnotationBindableAware {

	// Owvner class bindable
	private ClassBindable classBindable;

	// Method name
	private String methodName;

	// Method name without set (ex : 'name')
	private String propertyName;

	// Bindable value of property 'dependsOn' of Bindable annotation.
	private String[] dependsOn = null;

	// true if method must be transformed and false otherwise.
	private boolean bindableAnnotationValue = true;

	private String methodDesc = null;
	private String propertyDesc = null;

	protected GetterMethodBindable(ClassBindable classBindable, MethodVisitor mv,
			int access, String methodName, String desc) {
		super(mv);		
		this.methodName = methodName;
		this.classBindable = classBindable;
		// Get the property name = method name without 'set'|'get'|'is' suffix.
		this.propertyName = ClassUtils.getPropertyName(methodName);

		if (classBindable.getBindableAnnotationValue() != null) {
			// Initialize the bindable annotation value of the method with
			// Bindable
			// value of the Class.
			setBindableAnnotationValue(classBindable
					.getBindableAnnotationValue());
		}
		if (classBindable.getBindableAnnotationDependsOn() != null) {
			// Initialize the bindable annotation dependsOn of the
			// method with
			// Bindable
			// value of the Class.
			setBindableAnnotationDependsOn(classBindable
					.getBindableAnnotationDependsOn());
		}

		this.methodDesc = desc;
		this.propertyDesc = ClassUtils.getPropertyDesc(methodDesc);

	}

	@Override
	public void visitEnd() {
		super.visitEnd();
		String[] dependsOn = getBindableAnnotationDependsOn();
		if (dependsOn != null && dependsOn.length > 0) {
			// dependsOn are declared with Bindable annotation, add this
			// GetterMethodBindable to ClassBindable (to generate dependsOn
			// fields)
			getClassBindable().addGetterMethodBindable(this);
		}
	}

	/**
	 * Returns descriptor method (ex : '()D' which means return double).
	 * 
	 * @return
	 */
	public String getMethodDesc() {
		return methodDesc;
	}

	/**
	 * Returns descriptor property (ex : 'D' which means double).
	 * 
	 * @return
	 */
	public String getPropertyDesc() {
		return propertyDesc;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		ClassBindable classBindable = getClassBindable();
		if (classBindable.getBindableStrategy().isUseAnnotation()) {
			if (B_SIGNATURE.equals(desc)) {
				// It's Bindable annotation, visit it.
				return new AnnotationBindable(
						mv.visitAnnotation(desc, visible), this);
			}
		}
		return super.visitAnnotation(desc, visible);
	}

	/**
	 * Return method name.
	 * 
	 * @return
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * Return the property name of the method (ex : "getValue" method name is
	 * "value" property name).
	 * 
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Return owner Class Bindable.
	 * 
	 * @return
	 */
	public ClassBindable getClassBindable() {
		return classBindable;
	}

	/**
	 * 
	 * Set dependsOn array declared into Bindable annotation.
	 */
	public void setBindableAnnotationDependsOn(String[] dependsOn) {
		this.dependsOn = dependsOn;
	}

	/**
	 * Returns dependsOn array declared into Bindable annotation.
	 * 
	 * @return
	 */
	public String[] getBindableAnnotationDependsOn() {
		return dependsOn;
	}

	/**
	 * 
	 * Set value declared into Bindable annotation.
	 */
	public void setBindableAnnotationValue(boolean bindableAnnotationValue) {
		this.bindableAnnotationValue = bindableAnnotationValue;
	}

	/**
	 * 
	 * Returns value declared into Bindable annotation.
	 * 
	 * @return
	 */
	public boolean isBindableAnnotationValue() {
		return bindableAnnotationValue;
	}
}
