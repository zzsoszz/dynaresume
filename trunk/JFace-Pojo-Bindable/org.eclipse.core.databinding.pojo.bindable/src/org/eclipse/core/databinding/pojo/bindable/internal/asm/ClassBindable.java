/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *     Joga Singh <joga.singh@gmail.com Added code the generate the code for newly added methods to the BindableAware interface.
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.internal.asm;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindable;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindableAware;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * ASM {@link ClassVisitor} to change bytecode of POJO to implements
 * {@link BindableAware}.
 * 
 */
public class ClassBindable extends ClassAdapter implements Opcodes,
		BindableSignatureConstants, AnnotationBindableAware {

	private String className = null;
	private BindableStrategy bindableStrategy;
	private Boolean bindableAnnotation = null;
	// Bindable value of property 'computedProperties' of Bindable annotation.
	private String[] computedProperties = null;

	public ClassBindable(ClassVisitor cv, BindableStrategy bindableStrategy) {
		super(cv);
		this.bindableStrategy = bindableStrategy;
	}

	@Override
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		this.className = name;
		String[] newInterfaces = addBindableAwareIfNeeded(interfaces);
		super.visit(version, access, name, signature, superName, newInterfaces);
	}

	/**
	 * Add {@link BindableAware} interface to the POJO if needed.
	 * 
	 * @param interfaces
	 * @return
	 */
	private String[] addBindableAwareIfNeeded(String[] interfaces) {
		// Test if POJO implements already BindableAware interface.
		for (int i = 0; i < interfaces.length; i++) {
			if (interfaces[i].equals(BA_SHORT_SIGNATURE))
				return interfaces;
		}

		// Add BindableAware interface.
		String[] newInterfaces = new String[interfaces.length + 1];
		for (int i = 0; i < newInterfaces.length - 1; i++) {
			newInterfaces[i] = interfaces[i];
		}
		newInterfaces[newInterfaces.length - 1] = BA_SHORT_SIGNATURE;
		return newInterfaces;
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		if (bindableStrategy.isUseAnnotation()) {
			if (B_SIGNATURE.equals(desc)) {
				// It's Bindable annotation, visit it.
				return new AnnotationBindable(
						cv.visitAnnotation(desc, visible), this);
			}
		}
		return super.visitAnnotation(desc, visible);
	}

	@Override
	public void visitEnd() {

		// Add PropertyChangeSupport field.
		addPropertyChangeSupport();
		// Add private getter PropertyChangeSupport method.
		addGetPropertyChangeSupport();
		// Add addPropertyChangeListener method
		addAddPropertyChangeListener();
		// Add removePropertyChangeListener method
		addRemovePropertyChangeListener();

		super.visitEnd();
	}

	@Override
	public MethodVisitor visitMethod(int access, String methodName,
			String desc, String signature, String[] exceptions) {
		if (bindableStrategy.isBindableMethod(className, methodName)) {
			// Method is bindable, visit it.
			return new MethodBindable(this, access, methodName, desc, cv
					.visitMethod(access, methodName, desc, signature,
							exceptions));

		}
		return super.visitMethod(access, methodName, desc, signature,
				exceptions);
	}

	/**
	 * Add a variable of type PropertyChangeSupport to the class. When this
	 * method has been run, the class will contain a variable declaration
	 * similar to the following
	 * 
	 * private transient _bindable_propertyChangeSupport;
	 */
	public void addPropertyChangeSupport() {
		// private transient _bindable_propertyChangeSupport;
		cv.visitField(ACC_PRIVATE + ACC_TRANSIENT, PCS_FIELD, PCS_SIGNATURE,
				null, null);
	}

	/**
	 * Add the implementation of the _bindable_getPropertyChangeSupport method
	 * to the class. The result is a method that looks as follows:
	 * 
	 * private PropertyChangeSupport _bindable_getPropertyChangeSupport() { if
	 * (_bindable_propertyChangeSupport == null) {
	 * this._bindable_propertyChangeSupport = new PropertyChangeSupport( this);
	 * } return _bindable_propertyChangeSupport; }
	 */
	public void addGetPropertyChangeSupport() {

		// private PropertyChangeSupport _bindable_getPropertyChangeSupport()
		MethodVisitor mv = cv.visitMethod(ACC_PRIVATE, PCS_GETTER, "()"
				+ PCS_SIGNATURE, null, null);
		mv.visitCode();

		// if (_bindable_propertyChangeSupport == null)
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, className, PCS_FIELD, PCS_SIGNATURE);
		Label l0 = new Label();
		mv.visitJumpInsn(IFNONNULL, l0);

		// this._bindable_propertyChangeSupport = new PropertyChangeSupport(
		// this);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitTypeInsn(NEW, PCS_SHORT_SIGNATURE);
		mv.visitInsn(DUP);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, PCS_SHORT_SIGNATURE, "<init>",
				"(Ljava/lang/Object;)V");
		mv.visitFieldInsn(PUTFIELD, className, PCS_FIELD, PCS_SIGNATURE);
		mv.visitLabel(l0);
		mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

		// return _bindable_propertyChangeSupport;
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, className, PCS_FIELD, PCS_SIGNATURE);
		mv.visitInsn(ARETURN);
		mv.visitMaxs(4, 1);

	}

	/**
	 * Add the implementation of the addPropertyChangeListener method to the
	 * class. The result is a method that looks as follows:
	 * 
	 * public void addPropertyChangeListener(String propertyName,
	 * PropertyChangeListener listener) {
	 * _bindable_getPropertyChangeSupport().addPropertyChangeListener(
	 * propertyName, listener); }
	 */
	private void addAddPropertyChangeListener() {

		// public void addPropertyChangeListener(String propertyName,
		// PropertyChangeListener listener)
		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC,
				"addPropertyChangeListener",
				"(Ljava/lang/String;Ljava/beans/PropertyChangeListener;)V",
				null, null);
		mv.visitCode();

		// _bindable_getPropertyChangeSupport().addPropertyChangeListener(propertyName,
		// listener)
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, className, PCS_GETTER,
				"()Ljava/beans/PropertyChangeSupport;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitVarInsn(ALOAD, 2);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/beans/PropertyChangeSupport",
				"addPropertyChangeListener",
				"(Ljava/lang/String;Ljava/beans/PropertyChangeListener;)V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(3, 3);

		// public void addPropertyChangeListener(PropertyChangeListener
		// listener)
		mv = cv.visitMethod(ACC_PUBLIC, "addPropertyChangeListener",
				"(Ljava/beans/PropertyChangeListener;)V", null, null);
		mv.visitCode();

		// _bindable_getPropertyChangeSupport().addPropertyChangeListener(listener)
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, className, PCS_GETTER,
				"()Ljava/beans/PropertyChangeSupport;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/beans/PropertyChangeSupport",
				"addPropertyChangeListener",
				"(Ljava/beans/PropertyChangeListener;)V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(3, 3);

	}

	/**
	 * Add the implementation of the removePropertyChangeListener method to the
	 * class. The result is a method that looks as follows:
	 * 
	 * public void removePropertyChangeListener(String propertyName,
	 * PropertyChangeListener listener) {
	 * _bindable_getPropertyChangeSupport().removePropertyChangeListener(
	 * propertyName, listener); }
	 */
	private void addRemovePropertyChangeListener() {

		// public void removePropertyChangeListener(String propertyName,
		// PropertyChangeListener listener)
		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC,
				"removePropertyChangeListener",
				"(Ljava/lang/String;Ljava/beans/PropertyChangeListener;)V",
				null, null);
		mv.visitCode();

		// _bindable_getPropertyChangeSupport().removePropertyChangeListener(propertyName,
		// listener)
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, className, PCS_GETTER,
				"()Ljava/beans/PropertyChangeSupport;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitVarInsn(ALOAD, 2);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/beans/PropertyChangeSupport",
				"removePropertyChangeListener",
				"(Ljava/lang/String;Ljava/beans/PropertyChangeListener;)V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(3, 3);

		// public void removePropertyChangeListener(PropertyChangeListener
		// listener)
		mv = cv.visitMethod(ACC_PUBLIC, "removePropertyChangeListener",
				"(Ljava/beans/PropertyChangeListener;)V", null, null);
		mv.visitCode();

		// _bindable_getPropertyChangeSupport().removePropertyChangeListener(listener)
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, className, PCS_GETTER,
				"()Ljava/beans/PropertyChangeSupport;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/beans/PropertyChangeSupport",
				"removePropertyChangeListener",
				"(Ljava/beans/PropertyChangeListener;)V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(3, 3);

	}

	/**
	 * Returns the {@link BindableStrategy}.
	 * 
	 * @return
	 */
	protected BindableStrategy getBindableStrategy() {
		return bindableStrategy;
	}

	/**
	 * Returns class name visited.
	 * 
	 * @return
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Return the value of Bindable annotation putted into Class and null
	 * otherwise.
	 * 
	 * @return
	 */
	public Boolean getBindableAnnotationValue() {
		return this.bindableAnnotation;
	}

	/**
	 * Return the string array of 'computedProperties' Bindable annotation
	 * putted into Class and null otherwise.
	 * 
	 * @return
	 */
	public String[] getBindableAnnotationComputedProperties() {
		return computedProperties;
	}

	/**
	 * Set the value of Bindable annotation putted into Class.
	 */
	public void setBindableAnnotationValue(boolean value) {
		this.bindableAnnotation = value;
	}

	public void setBindableAnnotationComputedProperties(
			String[] computedProperties) {
		this.computedProperties = computedProperties;
	}

}
