package org.eclipse.gmt.modisco.jm2t.core.generator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;

public interface IGeneratorConfigurationValidator {

	/**
	 * Validate the generator configurations
	 * 
	 * @param generatorConfiguration
	 */
	IStatus validate(IGeneratorConfiguration generatorConfiguration,
			IJM2TProject project);

}
