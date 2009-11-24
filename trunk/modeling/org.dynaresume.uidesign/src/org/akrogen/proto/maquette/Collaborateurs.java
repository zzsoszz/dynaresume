package org.akrogen.proto.maquette;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Collaborateurs implements IPerspectiveFactory {

	public static final String ID="org.akrogen.proto.maquette.Collaborateurs";
	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("org.akrogen.proto.maquette.ListeAgence", IPageLayout.LEFT, 0.2f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("org.akrogen.proto.maquette.ListeCollaborateurs", IPageLayout.TOP, 0.3f, IPageLayout.ID_EDITOR_AREA);
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
