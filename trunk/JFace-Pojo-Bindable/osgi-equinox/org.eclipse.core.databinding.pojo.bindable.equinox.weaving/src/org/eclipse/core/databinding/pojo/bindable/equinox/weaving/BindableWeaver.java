package org.eclipse.core.databinding.pojo.bindable.equinox.weaving;

public interface BindableWeaver {

	public byte[] transform(String className, byte[] classfileBuffer);
	
}
