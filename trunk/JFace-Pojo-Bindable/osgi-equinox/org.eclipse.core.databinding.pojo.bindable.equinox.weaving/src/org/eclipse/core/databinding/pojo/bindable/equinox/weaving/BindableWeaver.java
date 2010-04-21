package org.eclipse.core.databinding.pojo.bindable.equinox.weaving;

import org.osgi.framework.Bundle;

public interface BindableWeaver {

	public byte[] transform(Bundle bundle, String className, byte[] classfileBuffer);
	
}
