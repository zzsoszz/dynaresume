package org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.fragment;

import org.eclipse.gmt.modisco.jm2t.core.TaskModel;
import org.eclipse.gmt.modisco.jm2t.core.generator.IGeneratorType;
import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.page.SelectModelConverterTypeComposite;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.swt.widgets.Composite;

public class SelectModelConverterTypeWizardFragment extends WizardFragment {

	private SelectModelConverterTypeComposite comp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.ui.internal.task.WizardTask#getWizardPage()
	 */
	public Composite createComposite(Composite parent, IWizardHandle wizard) {
		comp = new SelectModelConverterTypeComposite(parent, wizard,
				getTaskModel());
		return comp;
	}

	public boolean hasComposite() {
		return true;
	}

	@Override
	public void enter() {
		if (comp != null) {
			IGeneratorType generatorType = (IGeneratorType)getTaskModel().getObject(TaskModel.TASK_GENERATOR_TYPE);
			comp.setGeneratorType(generatorType);
		}
	}

}
