package org.eclipse.gmt.modisco.jm2t.internal.ui.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorConfiguration;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;

public class WizardTaskUtil {

	public static final WizardFragment SaveGeneratorConfigurationFragment = new WizardFragment() {
		public void performFinish(IProgressMonitor monitor)
				throws CoreException {
			WizardTaskUtil.saveGeneratorConfiguration(getTaskModel(), monitor);
		}
	};

	protected static void saveGeneratorConfiguration(TaskModel taskModel,
			IProgressMonitor monitor) throws CoreException {
		IGeneratorConfiguration configuration = (IGeneratorConfiguration) taskModel
				.getObject(TaskModel.TASK_GENERATOR_CONFIGURATION);
		if (configuration != null) {
			taskModel.putObject(TaskModel.TASK_GENERATOR_CONFIGURATION,
					configuration.save(false, monitor));
		}
	}
}
