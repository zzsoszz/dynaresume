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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.pojo.bindable.BindableAware;
import org.eclipse.core.databinding.pojo.bindable.BindableStrategy;
import org.eclipse.core.databinding.pojo.bindable.annotation.Bindable;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindable;
import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindableAware;
import org.eclipse.core.databinding.pojo.bindable.internal.util.ClassUtils;
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

	// Bindable value of property 'dependsOn' of Bindable annotation.
	private String[] dependsOn = null;

	// List of MethodBindables
	private Collection<SetterMethodBindable> setterMethodBindableList = null;

	// List of MethodBindables
	private Map<String, Collection<GetterMethodBindable>> dependsOnGetterMethodBindable = null;

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
		// Add for each method dependsOn method
		addDependsOnMethodsIfNeeded();

		super.visitEnd();
	}

	@Override
	public MethodVisitor visitMethod(int access, String methodName,
			String desc, String signature, String[] exceptions) {
		if (bindableStrategy.isBindableMethod(className, methodName)) {
			// Method is bindable, visit it.
			return new SetterMethodBindable(this, access, methodName, desc, cv
					.visitMethod(access, methodName, desc, signature,
							exceptions));

		} else {
			// Test if it's getter method
			if (isDependsOnSupported() && ClassUtils.isGetterMethod(methodName)) {
				return new GetterMethodBindable(this, access, methodName, desc,
						cv.visitMethod(access, methodName, desc, signature,
								exceptions));
			}

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
	 * Set the value of Bindable annotation putted into Class.
	 */
	public void setBindableAnnotationValue(boolean value) {
		this.bindableAnnotation = value;
	}

	// ----------------- @Bindable#dependsOn

	/**
	 * Return the string array of 'dependsOn' Bindable annotation putted into
	 * Class and null otherwise.
	 * 
	 * @return
	 */
	public String[] getBindableAnnotationDependsOn() {
		return dependsOn;
	}

	@Override
	public void setBindableAnnotationDependsOn(String[] dependsOn) {
		this.dependsOn = dependsOn;

	}

	/**
	 * Add a {@link SetterMethodBindable} which have call (if Bindable
	 * annotation is activated serveral methods _bindable_afterDependsOn...).
	 * 
	 * @param methodBindable
	 */
	protected void addSetterMethodBindable(SetterMethodBindable methodBindable) {
		if (setterMethodBindableList == null) {
			setterMethodBindableList = new ArrayList<SetterMethodBindable>();
		}
		setterMethodBindableList.add(methodBindable);
	}

	/**
	 * Add {@link GetterMethodBindable} which have declared dependsOn into
	 * {@link Bindable} annotation.
	 * 
	 * @param methodBindable
	 */
	public void addGetterMethodBindable(GetterMethodBindable methodBindable) {
		String[] dependsOn = methodBindable.getBindableAnnotationDependsOn();
		if (dependsOn == null)
			return;

		if (dependsOnGetterMethodBindable == null) {
			dependsOnGetterMethodBindable = new HashMap<String, Collection<GetterMethodBindable>>();
		}

		String depend = null;
		Collection<GetterMethodBindable> methodsBindable = null;
		for (int i = 0; i < dependsOn.length; i++) {
			depend = dependsOn[i];
			if (methodBindable.getPropertyName().equals(depend)) {
				continue;
			}

			methodsBindable = dependsOnGetterMethodBindable.get(depend);
			if (methodsBindable == null) {
				methodsBindable = new ArrayList<GetterMethodBindable>();
				dependsOnGetterMethodBindable.put(depend, methodsBindable);
			}
			if (!methodsBindable.contains(methodBindable)) {
				methodsBindable.add(methodBindable);
			}
		}
	}

	/**
	 * Generate methods _bindable_beforeDependsOn_... and
	 * _bindable_afterDependsOn_... for each {@link SetterMethodBindable}.
	 */
	private void addDependsOnMethodsIfNeeded() {
		if (setterMethodBindableList == null)
			// No methods have declared @Bindable#dependsOn.
			return;

		// Loop for each MethodBindable which have declared
		// @Bindable#dependsOn and generate dependsOn methods
		// required for each SetterMethodBindable
		Collection<GetterMethodBindable> getterMethodBindableList = null;
		String setterPropertyName = null;
		for (SetterMethodBindable setterMethodBindable : setterMethodBindableList) {

			if (dependsOnGetterMethodBindable != null) {
				// Get the property name of the setter
				setterPropertyName = setterMethodBindable.getPropertyName();
				// Search if it exists dependsOn (defined into getter) linked to
				// this
				// property name of the setter.
				getterMethodBindableList = dependsOnGetterMethodBindable
						.get(setterPropertyName);

			}
			addDependsOnFields(setterMethodBindable, getterMethodBindableList);
			// Generate before
			addBeforeDependsOnMethod(setterMethodBindable,
					getterMethodBindableList);
			// Generate after
			addAfterDependsOnMethod(setterMethodBindable,
					getterMethodBindableList);
		}
	}

	/**
	 * Generate fields dependsOn for the methodBindable.
	 * 
	 * @param setterMethodBindable
	 */
	private void addDependsOnFields(SetterMethodBindable setterMethodBindable,
			Collection<GetterMethodBindable> getterMethodBindableList) {
		if (getterMethodBindableList == null)
			return;

		// It exists list of getter wich defines dependsOn to this setter
		// method.
		// Loop for this list and generate dependsOn field.
		for (GetterMethodBindable getterMethodBindable : getterMethodBindableList) {
			addDependsOnFields(setterMethodBindable, getterMethodBindable);
		}

	}

	/**
	 * Generate dependsOn field for the setterMethodBindable by using
	 * getterMethodBindable :
	 * 
	 * private transient <type>
	 * _bindable_<setterMethodName>_<propertyNameOfGetterMethod>;
	 * 
	 * Ex : private transient double _bindable_setSellingPrice_ratio;
	 * 
	 * @param setterMethodBindable
	 * @param getterMethodBindable
	 */
	private void addDependsOnFields(SetterMethodBindable setterMethodBindable,
			GetterMethodBindable getterMethodBindable) {

		String dependsOnFieldName = getDependsOnFieldName(setterMethodBindable
				.getMethodName(), getterMethodBindable.getPropertyName());
		String propertyDesc = getterMethodBindable.getPropertyDesc();

		/*
		 * Generate private transient <type>
		 * _bindable_<setterMethodName>_<propertyNameOfGetterMethod>;
		 * 
		 * Ex : private transient double _bindable_setSellingPrice_ratio;
		 */
		cv.visitField(ACC_PRIVATE + ACC_TRANSIENT, dependsOnFieldName,
				propertyDesc, null, null);
	}

	/**
	 * Returns name of dependsOn field name.
	 * 
	 * @param methodName
	 * @param fieldName
	 * @return
	 */
	private String getDependsOnFieldName(String methodName, String fieldName) {
		StringBuffer name = new StringBuffer();
		name.append("_bindable_");
		name.append(methodName);
		name.append("_");
		name.append(fieldName);
		return name.toString();
	}

	/**
	 * Generate bindable_beforeDependsOn_... for the methodBindable.
	 * 
	 * @param methodBindable
	 * @param getterMethodBindableList
	 */
	protected void addBeforeDependsOnMethod(
			SetterMethodBindable methodBindable,
			Collection<GetterMethodBindable> getterMethodBindableList) {
		MethodVisitor mv = cv.visitMethod(ACC_PRIVATE, methodBindable
				.getBeforeDependsOnMethodName(), "()V", null, null);
		mv.visitCode();

		mv.visitInsn(RETURN);
		mv.visitMaxs(5, 1);
		mv.visitEnd();
	}

	/**
	 * Generate bindable_afterDependsOn_... for the methodBindable.
	 * 
	 * @param methodBindable
	 * @param getterMethodBindableList
	 */
	protected void addAfterDependsOnMethod(SetterMethodBindable methodBindable,
			Collection<GetterMethodBindable> getterMethodBindableList) {
		MethodVisitor mv = cv.visitMethod(ACC_PRIVATE, methodBindable
				.getAfterDependsOnMethodName(), "()V", null, null);
		mv.visitCode();

		mv.visitInsn(RETURN);
		mv.visitMaxs(5, 1);
		mv.visitEnd();

	}

	/**
	 * Return true if dependsOn is supported and false otherwise.
	 * 
	 * @return
	 */
	public boolean isDependsOnSupported() {
		return bindableStrategy.isUseAnnotation();
	}

}
