package org.eclipse.gmt.modisco.jm2t.core.generator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmt.modisco.jm2t.core.IJM2TProject;
import org.eclipse.gmt.modisco.jm2t.core.JM2TCore;
import org.eclipse.gmt.modisco.jm2t.core.util.StringUtils;
import org.eclipse.gmt.modisco.jm2t.internal.core.Messages;

public class DefaultGeneratorConfigurationValidator implements
		IGeneratorConfigurationValidator {

	public static IGeneratorConfigurationValidator INSTANCE = new DefaultGeneratorConfigurationValidator();

	public IStatus validate(IGeneratorConfiguration generatorConfiguration,
			IJM2TProject project) {
		IStatus status = validateNameRequired(generatorConfiguration);
		if (!isOK(status)) {
			return status;
		}
		status = validateNameUnique(generatorConfiguration, project);
		if (!isOK(status)) {
			return status;
		}
		return Status.OK_STATUS;
	}

	protected boolean isOK(IStatus status) {
		return status == null || status.isOK();
	}

	protected IStatus validateNameRequired(
			IGeneratorConfiguration generatorConfiguration) {
		String name = generatorConfiguration.getName();
		if (StringUtils.isEmpty(name)) {
			return createErrorStatus(Messages.DefaultGeneratorConfigurationValidator_nameRequired, null);
		}
		return Status.OK_STATUS;
	}

	protected IStatus validateNameUnique(
			IGeneratorConfiguration generatorConfiguration, IJM2TProject project) {
		return Status.OK_STATUS;
	}

	protected IStatus createErrorStatus(String message, Throwable exception) {
		return createStatus(IStatus.ERROR, 0, message, exception);
	}

	protected IStatus createStatus(int severity, int code, String message,
			Throwable exception) {
		return new Status(severity, JM2TCore.PLUGIN_ID, 0, message, exception);
	}

}
