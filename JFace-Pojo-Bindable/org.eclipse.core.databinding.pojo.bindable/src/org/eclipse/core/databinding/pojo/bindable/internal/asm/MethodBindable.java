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
import org.eclipse.core.databinding.pojo.bindable.internal.asm.annotation.AnnotationBindableAware;
import org.eclipse.core.databinding.pojo.bindable.internal.util.ClassUtils;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

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

public class MethodBindable extends AdviceAdapter implements Opcodes,
		BindableSignatureConstants, AnnotationBindableAware {

	// Owvner class bindable
	private ClassBindable classBindable;

	// Getter method name for the property i.e. getName. Getter method is used
	// to get the old and new value
	private String getterMethod;

	// Method name without set (ex : 'name')
	private String propertyName;

	// Method arguments types. Used to generate the variable to store the old
	// value.
	// Type[] argumentTypes;
	private Type firstArgumentType = null;

	// Variable index holder in the bytecode.
	private int oldValueVarIndex;

	// true if method must be transformed and false otherwise.
	private boolean transformToBindableMethod = true;

	public MethodBindable(ClassBindable classBindable, int access,
			String methodName, String desc, MethodVisitor mv) {
		super(mv, access, methodName, desc);
		this.classBindable = classBindable;

		// Get the first argument type of the method
		Type[] argumentTypes = Type.getArgumentTypes(desc);
		this.firstArgumentType = (argumentTypes.length == 1 ? argumentTypes[0]
				: null);

		// Get the property name = method name without 'set' prefix.
		this.propertyName = ClassUtils.getPropertyName(methodName);
		// Get the getter method name
		this.getterMethod = ClassUtils
				.getGetterMethod(propertyName,
						(firstArgumentType != null && firstArgumentType
								.getSort() == Type.BOOLEAN));

		if (classBindable.getBindableAnnotationValue() != null) {
			// Initialize the bindable annotation value of the method with
			// Bindable
			// value of the Class.
			setBindableAnnotationValue(classBindable
					.getBindableAnnotationValue());
		}

	}

	@Override
	protected void onMethodEnter() {
		if (!canTransformToBindableMethod())
			return;

		/*
		 * Inject the bytecode to store the old value of the property into a
		 * variable. E.g. String s = getName();
		 * 
		 * Experience has shown that sometimes getter methods do not simply
		 * return the field value especially in JPA entities. Further, field
		 * names could be totally different than the setter/getter method names.
		 * This is the reason that getter method is used to get the old value,
		 * rather than accessing the field.
		 */

		// Get the index of the variable. Mostly it will be 2.
		oldValueVarIndex = newLocal(firstArgumentType);

		String methodDesc = getGettterMethodDesc();
		mv.visitVarInsn(ALOAD, 0);
		visitMethodInsn(INVOKEVIRTUAL, classBindable.getClassName(),
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
		switch (type.getSort()) {
		case Type.BOOLEAN:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf",
			// "(Z)Ljava/lang/Boolean;");
			mv.visitMethodInsn(INVOKESTATIC, BOOLEAN_SHORT_SIGNATURE,
					VALUEOF_METHOD, BOOLEAN_SIGNATURE);
			break;
		case Type.BYTE:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf",
			// "(B)Ljava/lang/Byte;");
			mv.visitMethodInsn(INVOKESTATIC, BYTE_SHORT_SIGNATURE,
					VALUEOF_METHOD, BYTE_SIGNATURE);
			break;
		case Type.CHAR:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Character",
			// "valueOf", "(C)Ljava/lang/Character;");
			mv.visitMethodInsn(INVOKESTATIC, CHARACTER_SHORT_SIGNATURE,
					VALUEOF_METHOD, CHARACTER_SIGNATURE);
			break;
		case Type.INT:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf",
			// "(I)Ljava/lang/Integer;");
			mv.visitMethodInsn(INVOKESTATIC, INTEGER_SHORT_SIGNATURE,
					VALUEOF_METHOD, INTEGER_SIGNATURE);
			break;
		case Type.SHORT:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Short",
			// "valueOf","(S)Ljava/lang/Short;");
			mv.visitMethodInsn(INVOKESTATIC, SHORT_SHORT_SIGNATURE,
					VALUEOF_METHOD, SHORT_SIGNATURE);
			break;
		case Type.LONG:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf",
			// "(J)Ljava/lang/Long;");
			mv.visitMethodInsn(INVOKESTATIC, LONG_SHORT_SIGNATURE,
					VALUEOF_METHOD, LONG_SIGNATURE);
			break;
		case Type.FLOAT:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf",
			// "(F)Ljava/lang/Float;");
			mv.visitMethodInsn(INVOKESTATIC, FLOAT_SHORT_SIGNATURE,
					VALUEOF_METHOD, FLOAT_SIGNATURE);
			break;
		case Type.DOUBLE:
			// mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf",
			// "(D)Ljava/lang/Double;");
			mv.visitMethodInsn(INVOKESTATIC, DOUBLE_SHORT_SIGNATURE,
					VALUEOF_METHOD, DOUBLE_SIGNATURE);
			break;
		}
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
			insertFirePropertyChangeCode();
		}

	}

	/**
	 * Inject the fire property change code in the method end. E.g.
	 * _bindable_getPropertyChangeSupport().firePropertyChange("name", s,
	 * getName());
	 **/
	private void insertFirePropertyChangeCode() {

		String methodDesc = getGettterMethodDesc();
		visitVarInsn(ALOAD, 0);
		visitMethodInsn(INVOKESPECIAL, classBindable.getClassName(),
				"_bindable_getPropertyChangeSupport",
				"()Ljava/beans/PropertyChangeSupport;");
		visitLdcInsn(propertyName);
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
		if (classBindable.getBindableStrategy().isUseAnnotation()) {
			if (B_SIGNATURE.equals(desc)) {
				// It's Bindable annotation, visit it.
				return new AnnotationBindable(
						mv.visitAnnotation(desc, visible), this);
			}
		}
		return super.visitAnnotation(desc, visible);
	}

	public void setBindableAnnotationValue(boolean value) {
		this.transformToBindableMethod = value;
	}

	/**
	 * Return true if method must be transformed and false otherwise.
	 * 
	 * @return
	 */
	private boolean canTransformToBindableMethod() {
		return transformToBindableMethod && firstArgumentType != null;
	}
}
