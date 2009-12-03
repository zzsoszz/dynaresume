package org.dynaresume.common.address;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddressComposite extends Composite {

	private AddressCompositeController m_controller;
	private Text cityText;
	private Text faxText;
	private Text telephoneText;
	private Text zipCodeText;
	private CCombo country;
	public AddressComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		new Label(this, SWT.NONE).setText("City:");

		cityText = new Text(this, SWT.BORDER | SWT.SINGLE);
		cityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		new Label(this, SWT.NONE).setText("Fax:");

		faxText = new Text(this, SWT.BORDER | SWT.SINGLE);
		faxText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		new Label(this, SWT.NONE).setText("Telephone:");

		telephoneText = new Text(this, SWT.BORDER | SWT.SINGLE);
		telephoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("ZipCode:");

		zipCodeText = new Text(this, SWT.BORDER | SWT.SINGLE);
		zipCodeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));

		new Label(this, SWT.NONE).setText("Country:");
		country = new CCombo(this, SWT.BORDER | SWT.SINGLE);
		country.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		
		m_controller = new AddressCompositeController(this);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Text getCityText() {
		return cityText;
	}

	public Text getFaxText() {
		return faxText;
	}

	public Text getTelephoneText() {
		return telephoneText;
	}

	public Text getZipCodeText() {
		return zipCodeText;
	}

}
