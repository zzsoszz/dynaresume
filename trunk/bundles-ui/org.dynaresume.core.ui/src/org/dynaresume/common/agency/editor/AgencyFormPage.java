package org.dynaresume.common.agency.editor;

import java.util.List;

import org.dynaresume.common.address.AddressComposite;
import org.dynaresume.common.address.AddressCompositeController;
import org.dynaresume.common.agency.AgencyComposite;
import org.dynaresume.common.agency.AgencyCompositeController;
import org.dynaresume.core.domain.Agency;
import org.dynaresume.core.domain.Country;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;

public class AgencyFormPage extends FormPage {

	private AgencyComposite agencyComposite;
	private AddressComposite addressComposite;

	/**
	 * Create the form page.
	 * 
	 * @param id
	 * @param title
	 */
	public AgencyFormPage(String id, String title) {
		super(id, title);
	}

	/**
	 * Create the form page.
	 * 
	 * @param editor
	 * @param id
	 * @param title
	 * @wbp.parser.constructor
	 * @wbp.eval.method.parameter id "Some id"
	 * @wbp.eval.method.parameter title "Some title"
	 */
	public AgencyFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		setTitleImage(ResourceManager.getPluginImage("org.dynaresume.common.simpleclient", "icons/branch42x42.png"));
	}

	private Agency agency;

	public void setAgency(Agency agency) {
		this.agency = agency;

	}

	/**
	 * Create contents of the form.
	 * 
	 * @param managedForm
	 */
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		managedForm.getForm().setFont(SWTResourceManager.getFont("Arial Baltic", 24, SWT.BOLD));
		managedForm.getForm().setImage(ResourceManager.getPluginImage("org.dynaresume.common.simpleclient", "icons/branch42x42.png"));
		FormToolkit toolkit = managedForm.getToolkit();
		ScrolledForm form = managedForm.getForm();
		form.setText("Agency "+agency.getName());
		Composite body = form.getBody();
		toolkit.decorateFormHeading(form.getForm());
		//toolkit.paintBordersFor(body);
		managedForm.getForm().getBody().setLayout(new FillLayout(SWT.VERTICAL));

		Section sctnAgency = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE | Section.TITLE_BAR);
		//managedForm.getToolkit().paintBordersFor(sctnAgency);
		sctnAgency.setText("Global informations");

		agencyComposite = new AgencyComposite(sctnAgency, SWT.NONE);
		sctnAgency.setClient(agencyComposite);
		managedForm.getToolkit().adapt(agencyComposite);
		//managedForm.getToolkit().paintBordersFor(agencyComposite);
		sctnAgency.setExpanded(true);

		Section sctnAddress = managedForm.getToolkit().createSection(managedForm.getForm().getBody(), Section.TWISTIE | Section.TITLE_BAR);
		//managedForm.getToolkit().paintBordersFor(sctnAddress);
		sctnAddress.setText("Address");

		addressComposite = new AddressComposite(sctnAddress, SWT.NONE);
		managedForm.getToolkit().adapt(addressComposite);
		//managedForm.getToolkit().paintBordersFor(addressComposite);
		sctnAddress.setClient(addressComposite);
		sctnAddress.setExpanded(true);

		new AgencyCompositeController(agencyComposite, agency);
		AddressCompositeController addressCompositeController=		new AddressCompositeController(addressComposite);
		
		addressCompositeController.initCountryViewer(countries);
		addressCompositeController.setAddress(agency.getAddress());
	}

	public AgencyComposite getAgencyComposite() {
		return agencyComposite;
	}

	public AddressComposite getAddressComposite() {
		return addressComposite;
	}
	private List<Country> countries;
	public void setCountries(List<Country> countries){
		this.countries=countries;
	}
}
