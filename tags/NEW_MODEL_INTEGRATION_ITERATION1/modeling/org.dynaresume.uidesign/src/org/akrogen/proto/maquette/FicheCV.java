package org.akrogen.proto.maquette;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class FicheCV extends FormPage {
	private Text text;

	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public FicheCV(String id, String title) {
		super(id, title);
	}

	/**
	 * Create the form page.
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public FicheCV(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * Create contents of the form.
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("Angelo Zerr");
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		toolkit.paintBordersFor(body);
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.spacing = 10;
		fillLayout.marginWidth = 5;
		fillLayout.marginHeight = 5;
		managedForm.getForm().getBody().setLayout(fillLayout);
		{
			Composite composite = managedForm.getToolkit().createComposite(managedForm.getForm().getBody(), SWT.NONE);
			managedForm.getToolkit().paintBordersFor(composite);
			composite.setLayout(new GridLayout(2, false));
			{
				Label lblMission = managedForm.getToolkit().createLabel(composite, "Intitulé du poste  :", SWT.NONE);
				lblMission.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			}
			{
				text = managedForm.getToolkit().createText(composite, "New Text", SWT.NONE);
				text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			}
		}
	}

}
