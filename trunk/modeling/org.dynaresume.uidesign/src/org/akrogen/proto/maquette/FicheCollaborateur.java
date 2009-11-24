package org.akrogen.proto.maquette;

import java.util.Date;

import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;

public class FicheCollaborateur extends FormPage {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;

	/**
	 * Create the form page.
	 * @param id
	 * @param title
	 */
	public FicheCollaborateur(String id, String title) {
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
	public FicheCollaborateur(FormEditor editor, String id, String title) {
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
			Section sctnEtatCivil = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE | Section.TITLE_BAR);
			managedForm.getToolkit().paintBordersFor(sctnEtatCivil);
			sctnEtatCivil.setText("Etat Civil");
			{
				Composite composite = new Composite(sctnEtatCivil, SWT.NONE);
				managedForm.getToolkit().adapt(composite);
				managedForm.getToolkit().paintBordersFor(composite);
				sctnEtatCivil.setClient(composite);
				composite.setLayout(new GridLayout(2, false));
				{
					Label lblNom = new Label(composite, SWT.NONE);
					lblNom.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					managedForm.getToolkit().adapt(lblNom, true, true);
					lblNom.setText("Nom :");
				}
				{
					text = new Text(composite, SWT.BORDER);
					{
						GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
						gridData.widthHint = 200;
						text.setLayoutData(gridData);
					}
					managedForm.getToolkit().adapt(text, true, true);
				}
				{
					Label lblPrnom = new Label(composite, SWT.NONE);
					lblPrnom.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					managedForm.getToolkit().adapt(lblPrnom, true, true);
					lblPrnom.setText("Prénom :");
				}
				{
					text_1 = new Text(composite, SWT.BORDER);
					{
						GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
						gridData.widthHint = 200;
						text_1.setLayoutData(gridData);
					}
					managedForm.getToolkit().adapt(text_1, true, true);
				}
				{
					Label lblDateDeNaissance = new Label(composite, SWT.NONE);
					managedForm.getToolkit().adapt(lblDateDeNaissance, true, true);
					lblDateDeNaissance.setText("Date de naissance :");
				}
				{
					DateChooserCombo dateChooserCombo = new DateChooserCombo(composite, SWT.BORDER | SWT.FLAT);
					dateChooserCombo.setValue(new Date());
					managedForm.getToolkit().adapt(dateChooserCombo);
					managedForm.getToolkit().paintBordersFor(dateChooserCombo);
				}
				{
					Label lblSituationMariatal = new Label(composite, SWT.NONE);
					managedForm.getToolkit().adapt(lblSituationMariatal, true, true);
					lblSituationMariatal.setText("Situation maritale :");
				}
				{
					CCombo combo = new CCombo(composite, SWT.BORDER);
					managedForm.getToolkit().adapt(combo);
					managedForm.getToolkit().paintBordersFor(combo);
				}
			}
			sctnEtatCivil.setExpanded(true);
		}
		{
			Section sctnAdressePrincipale = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE | Section.TITLE_BAR);
			managedForm.getToolkit().paintBordersFor(sctnAdressePrincipale);
			sctnAdressePrincipale.setText("Adresse principale");
			{
				Composite composite = new Composite(sctnAdressePrincipale, SWT.NONE);
				managedForm.getToolkit().adapt(composite);
				managedForm.getToolkit().paintBordersFor(composite);
				sctnAdressePrincipale.setClient(composite);
				composite.setLayout(new GridLayout(2, false));
				{
					Label lblAdresse = new Label(composite, SWT.NONE);
					lblAdresse.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					managedForm.getToolkit().adapt(lblAdresse, true, true);
					lblAdresse.setText("Adresse :");
				}
				{
					text_2 = new Text(composite, SWT.BORDER);
					text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
					managedForm.getToolkit().adapt(text_2, true, true);
				}
				{
					Label lblComplmentDadresse = new Label(composite, SWT.NONE);
					lblComplmentDadresse.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					managedForm.getToolkit().adapt(lblComplmentDadresse, true, true);
					lblComplmentDadresse.setText("Complément d'adresse :");
				}
				{
					text_3 = new Text(composite, SWT.BORDER);
					text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
					managedForm.getToolkit().adapt(text_3, true, true);
				}
				{
					Label lblTypeDeVoie = new Label(composite, SWT.NONE);
					lblTypeDeVoie.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					managedForm.getToolkit().adapt(lblTypeDeVoie, true, true);
					lblTypeDeVoie.setText("Type de voie :");
				}
				{
					Combo combo = new Combo(composite, SWT.NONE);
					combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
					managedForm.getToolkit().adapt(combo);
					managedForm.getToolkit().paintBordersFor(combo);
				}
				{
					Label lblCodePostal = new Label(composite, SWT.NONE);
					lblCodePostal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					managedForm.getToolkit().adapt(lblCodePostal, true, true);
					lblCodePostal.setText("Code postal :");
				}
				{
					text_4 = new Text(composite, SWT.BORDER);
					text_4.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
					managedForm.getToolkit().adapt(text_4, true, true);
				}
				{
					Label lblCommune = new Label(composite, SWT.NONE);
					lblCommune.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					managedForm.getToolkit().adapt(lblCommune, true, true);
					lblCommune.setText("Commune :");
				}
				{
					text_5 = new Text(composite, SWT.BORDER);
					{
						GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
						gridData.widthHint = 200;
						text_5.setLayoutData(gridData);
					}
					managedForm.getToolkit().adapt(text_5, true, true);
				}
				{
					Label lblNumroDeTlphone = new Label(composite, SWT.NONE);
					lblNumroDeTlphone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
					managedForm.getToolkit().adapt(lblNumroDeTlphone, true, true);
					lblNumroDeTlphone.setText("Numéro de téléphone :");
				}
				{
					text_6 = new Text(composite, SWT.BORDER);
					{
						GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
						gridData.widthHint = 160;
						text_6.setLayoutData(gridData);
						text_6.setText("(   ) -  -  -  ");
					}
					managedForm.getToolkit().adapt(text_6, true, true);
				}
			}
			sctnAdressePrincipale.setExpanded(true);
		}
	}
}
