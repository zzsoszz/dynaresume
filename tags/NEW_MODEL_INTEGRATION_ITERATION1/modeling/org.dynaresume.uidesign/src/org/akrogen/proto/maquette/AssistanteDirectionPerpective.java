package org.akrogen.proto.maquette;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class AssistanteDirectionPerpective implements IPerspectiveFactory {

	public static final String ID="org.akrogen.proto.maquette.AssistanteDirectionPerpective";
	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		//layout.addStandaloneView(viewId, showTitle, relationship, ratio, refId)
		layout.addStandaloneView("org.akrogen.proto.maquette.CoWorkersSearch",false, IPageLayout.TOP, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addStandaloneView("org.akrogen.proto.maquette.ResultatRecherche",false, IPageLayout.BOTTOM, 0.5f, "org.akrogen.proto.maquette.CoWorkersSearch");
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
