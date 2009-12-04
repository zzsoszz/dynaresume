package org.dynaresume.common.group;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GroupComposite extends Composite {

//	private GroupCompositeController m_controller;
	private Text codeText;
	private Text emailText;
	private Text nameText;

	public GroupComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		new Label(this, SWT.NONE).setText("Code:");

		codeText = new Text(this, SWT.BORDER | SWT.SINGLE);
		codeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		new Label(this, SWT.NONE).setText("Email:");

		emailText = new Text(this, SWT.BORDER | SWT.SINGLE);
		emailText
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		new Label(this, SWT.NONE).setText("Name:");

		nameText = new Text(this, SWT.BORDER | SWT.SINGLE);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

//		m_controller = new GroupCompositeController(this);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Text getCodeText() {
		return codeText;
	}

	public Text getEmailText() {
		return emailText;
	}

	public Text getNameText() {
		return nameText;
	}

}
