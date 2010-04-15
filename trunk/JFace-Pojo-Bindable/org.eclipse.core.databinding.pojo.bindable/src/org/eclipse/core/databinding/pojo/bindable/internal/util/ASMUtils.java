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
package org.eclipse.core.databinding.pojo.bindable.internal.util;

import org.eclipse.core.databinding.pojo.bindable.internal.asm.BindableSignatureConstants;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Utilities for ASM bytecode.
 * 
 */
public class ASMUtils implements Opcodes, BindableSignatureConstants {

	/**
	 * Transform primitive type (int, boolean...) into java Object (int to
	 * java.lang.Integer by using {@link Integer#valueOf(int)}, boolean to
	 * java.lang.Boolean by using {@link Boolean#valueOf(boolean)}).
	 * 
	 * @param type
	 */
	public static void convertPrimitiveTypeToObjectTypeIfNeeded(
			MethodVisitor mv, Type type) {
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

}
