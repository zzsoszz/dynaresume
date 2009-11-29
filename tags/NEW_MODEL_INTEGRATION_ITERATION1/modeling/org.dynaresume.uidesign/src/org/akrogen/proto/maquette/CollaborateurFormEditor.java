package org.akrogen.proto.maquette;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;

public class CollaborateurFormEditor extends SharedHeaderFormEditor {

	public static final String ID = "org.akrogen.proto.maquette.CollaborateurFormEditor";
	FicheCollaborateur ficheCollaborateur;

	@Override
	protected void addPages() {

		try {
			ficheCollaborateur = new FicheCollaborateur(this, "collab",
					"Collaborateur");

			addPage(ficheCollaborateur);
			FicheCV ficheCV = new FicheCV(this, "cv", "Curriculum Vitae");
			addPage(ficheCV);
			Experiences experiences = new Experiences(this, "Expériences",
					"Expériences");
			addPage(experiences);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site, input);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		ficheCollaborateur.setFocus();
	}
}
