package org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.fragment;

import org.eclipse.gmt.modisco.jm2t.internal.ui.wizard.page.NewGeneratorConfigurationComposite;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.IWizardHandle;
import org.eclipse.gmt.modisco.jm2t.ui.wizard.WizardFragment;
import org.eclipse.swt.widgets.Composite;

public class NewGeneratorConfigurationWizardFragment extends WizardFragment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.ui.internal.task.WizardTask#getWizardPage()
	 */
	public Composite createComposite(Composite parent, IWizardHandle wizard) {
		return new NewGeneratorConfigurationComposite(parent, wizard,
				getTaskModel());
	}

	public boolean hasComposite() {
		return true;
	}

}
