/*******************************************************************************
 * Copyright (c) 2010, Original authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR <angelo.zerr@gmail.com>
 *     Joga Singh <joga.singh@gmail.com> Changed the code to inject the bytecode in the start and end of method.
 *******************************************************************************/
package org.eclipse.core.databinding.pojo.bindable.internal.asm;

import java.beans.PropertyChangeSupport;

import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindable;
import org.eclipse.core.databinding.pojo.bindable.internal.util.ASMUtils;
import org.eclipse.core.databinding.pojo.bindable.internal.util.ClassUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

/**
 * 
 * ASM {@link MethodVisitor} which transform a method bytecode to call
 * {@link PropertyChangeSupport#firePropertyChange(String, Object, Object)} into
 * setter method. Below is the example code:
 * 
 * Original method:
 * 
 * public void setName(String name) { this.name = name; }
 * 
 * Method after transformation:
 * 
 * public void setName(String name) { String s = getName(); this.name = name;
 * _bindable_getPropertyChangeSupport().firePropertyChange("name", s,
 * getName()); }
 * 
 */

public class SetterMethodBindable extends AbstractMethodBindable {

	private static final String BINDABLE_BEFORE_DEPENDS_ON_METHOD_SUFFIX = "_bindable_beforeDependsOn_";
	private static final String BINDABLE_AFTER_DEPENDS_ON_METHOD_SUFFIX = "_bindable_afterDependsOn_";

	// Getter method name for the property i.e. getName. Getter method is used
	// to get the old and new value
	private String getterMethod;

	// Method arguments types. Used to generate the variable to store the old
	// value.
	// Type[] argumentTypes;
	private Type firstArgumentType = null;

	// Variable index holder in the bytecode.
	private int oldValueVarIndex;

	public SetterMethodBindable(ClassBindable classBindable, int access,
			String methodName, String desc, MethodVisitor mv) {
		super(classBindable, access, methodName, desc, mv);

		// Get the first argument type of the method
		Type[] argumentTypes = Type.getArgumentTypes(desc);
		this.firstArgumentType = (argumentTypes.length == 1 ? argumentTypes[0]
				: null);

		// Get the getter method name
		this.getterMethod = ClassUtils
				.getGetterMethod(getPropertyName(),
						(firstArgumentType != null && firstArgumentType
								.getSort() == Type.BOOLEAN));
	}

	@Override
	protected void onMethodEnter() {
		if (!canTransformToBindableMethod())
			return;

		// Call methods wich get the old values of computedProperties
		addBeforeDependsOnMethodsIfNeeded();
		// Get the old value.
		addGetOldValueCode();

	}

	/**
	 * Inject the bytecode to store the old value of the property into a
	 * variable. E.g. String s = getName();
	 * 
	 * Experience has shown that sometimes getter methods do not simply return
	 * the field value especially in JPA entities. Further, field names could be
	 * totally different than the setter/getter method names. This is the reason
	 * that getter method is used to get the old value, rather than accessing
	 * the field.
	 */
	private void addGetOldValueCode() {
		// Get the index of the variable. Mostly it will be 2.
		oldValueVarIndex = newLocal(firstArgumentType);

		String methodDesc = getGettterMethodDesc();
		mv.visitVarInsn(ALOAD, 0);
		visitMethodInsn(INVOKEVIRTUAL, getClassBindable().getClassName(),
				getterMethod, methodDesc);
		convertPrimitiveTypeToObjectTypeIfNeeded(firstArgumentType);
		visitVarInsn(ASTORE, oldValueVarIndex);
	}

	/**
	 * If setter method has primitive type (int, boolean...), the parameter must
	 * be transformed into java Object (int to java.lang.Integer, boolean to
	 * java.lang.Boolean).
	 * 
	 * @param type
	 */
	private void convertPrimitiveTypeToObjectTypeIfNeeded(Type type) {
		ASMUtils.convertPrimitiveTypeToObjectTypeIfNeeded(mv, type);
	}

	@Override
	protected void onMethodExit(int opcode) {
		if (!canTransformToBindableMethod())
			return;
		/*
		 * Setter menthods return nothing, so we have to cater only if the
		 * opcode value is RETURN We do not fire property change if there is any
		 * exception
		 */
		if (opcode == RETURN) {
			// Fire event
			addFirePropertyChangeCode();
			// Call methods wich fire events for each dependsOn
			addAfterDependsOnMethodsIfNeeded();
		}

	}

	/**
	 * Inject the fire property change code in the method end. E.g.
	 * _bindable_getPropertyChangeSupport().firePropertyChange("name", s,
	 * getName());
	 **/
	private void addFirePropertyChangeCode() {

		ClassBindable classBindable = getClassBindable();
		String methodDesc = getGettterMethodDesc();
		visitVarInsn(ALOAD, 0);
		visitMethodInsn(INVOKESPECIAL, classBindable.getClassName(),
				"_bindable_getPropertyChangeSupport",
				"()Ljava/beans/PropertyChangeSupport;");
		visitLdcInsn(getPropertyName());
		visitVarInsn(ALOAD, oldValueVarIndex);
		visitVarInsn(ALOAD, 0);

		visitMethodInsn(INVOKEVIRTUAL, classBindable.getClassName(),
				getterMethod, methodDesc);
		convertPrimitiveTypeToObjectTypeIfNeeded(firstArgumentType);
		visitMethodInsn(INVOKEVIRTUAL, "java/beans/PropertyChangeSupport",
				"firePropertyChange", FPC_DESC_OBJECT /*
													 * "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V")
													 */);
	}

	private String getGettterMethodDesc() {
		// Return type of the getter method is the argument type of the setter
		// method.
		return "()" + firstArgumentType.getDescriptor();
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		super.visitMaxs(maxStack + 3, maxLocals + 3);
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
	 * Return true if method must be transformed and false otherwise.
	 * 
	 * @return
	 */
	private boolean canTransformToBindableMethod() {
		return isBindableAnnotationValue() && firstArgumentType != null;
	}

	// ----------------- @Bindable#dependsOn

	/**
	 * Return name of the method which get old values of dependsOn
	 * "_bindable_beforeDependsOn_<methodName>".
	 * 
	 * @return
	 */
	public String getBeforeDependsOnMethodName() {
		return BINDABLE_BEFORE_DEPENDS_ON_METHOD_SUFFIX + getMethodName();
	}

	/**
	 * Return name of the method which fire event for dependsOn
	 * "_bindable_afterDependsOn_<methodName>".
	 * 
	 * @return
	 */
	public String getAfterDependsOnMethodName() {
		return BINDABLE_AFTER_DEPENDS_ON_METHOD_SUFFIX + getMethodName();
	}

	/**
	 * Call the method "_bindable_beforeDependsOn_<methodName>" to get old
	 * values of dependsOn. This method will be generated into
	 * {@link ClassBindable#addBeforeDependsOnMethod(SetterMethodBindable)}.
	 */
	private void addBeforeDependsOnMethodsIfNeeded() {
		ClassBindable classBindable = getClassBindable();
		if (!classBindable.isDependsOnSupported())
			return;

		// Add this method bindable to owner class bindable
		// to generate dependsOn method bindable.
		classBindable.addSetterMethodBindable(this);

		// Generate _bindable_beforeDependsOn_<methodName>();
		// ex for setValue method, it will generate
		// public void setValue(String value) {
		// _bindable_beforeDependsOn_setValue(); ...
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, classBindable.getClassName(),
				getBeforeDependsOnMethodName(), "()V");

	}

	/**
	 * Call the method "_bindable_afterDependsOn_<methodName>" to fire event for
	 * each dependsOn. Methods is used to get values because here we cannot know
	 * the Type of the dependsOn because method can be not parsed. The method
	 * "_bindable_afterDependsOn_<methodName>" will be generated into
	 * {@link ClassBindable#addAfterDependsOnMethod(SetterMethodBindable)}.
	 */
	private void addAfterDependsOnMethodsIfNeeded() {
		ClassBindable classBindable = getClassBindable();
		if (!classBindable.isDependsOnSupported())
			return;

		// Generate _bindable_afterDependsOn_<methodName>();
		// ex for setValue method, it will generate
		// public void setValue(String value) {
		// _bindable_beforeDependsOn_setValue(); ...
		// ...
		// _bindable_afterDependsOn_setValue(); ...
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, classBindable.getClassName(),
				getAfterDependsOnMethodName(), "()V");

	}
}
