package org.eclipse.equinox.weaving.springweaver;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassFileTransformerWrapper implements ClassFileTransformer {
	
	private ClassFileTransformer classFileTransformer;
	private String importDynamicPackages;

	public ClassFileTransformerWrapper(
		ClassFileTransformer classFileTransformer,
		String importDynamicPackages) {
	    this.classFileTransformer = classFileTransformer;
	    this.importDynamicPackages = importDynamicPackages;
	}

	public String getImportDynamicPackages() {
	    return importDynamicPackages;
	}

	@Override
	public byte[] transform(ClassLoader loader, String className,
		Class<?> classBeingRedefined,
		ProtectionDomain protectionDomain, byte[] classfileBuffer)
		throws IllegalClassFormatException {
	    return classFileTransformer.transform(loader, className,
		    classBeingRedefined, protectionDomain, classfileBuffer);
	}

}
