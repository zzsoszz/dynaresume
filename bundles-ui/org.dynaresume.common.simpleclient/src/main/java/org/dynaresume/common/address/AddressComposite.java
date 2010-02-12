/*****************************************************************************
 * Copyright (c) 2009
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com>
 *     Jawher Moussa <jawher.moussa@gmail.com>
 *     Nicolas Inchauspe <nicolas.inchauspe@gmail.com>
 *     Pascal Leclercq <pascal.leclercq@gmail.com>
 *******************************************************************************/
package org.dynaresume.common.address;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddressComposite extends Composite {


	private Text cityText;
	private Text faxText;
	private Text telephoneText;
	private Text zipCodeText;
	private CCombo countryCombo;
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
		countryCombo = new CCombo(this, SWT.BORDER | SWT.SINGLE);
		countryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		
	
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

	public CCombo getCountryCombo() {
		return countryCombo;
	}
	
}
