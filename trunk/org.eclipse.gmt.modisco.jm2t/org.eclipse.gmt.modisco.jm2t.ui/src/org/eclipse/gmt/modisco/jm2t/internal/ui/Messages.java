/*******************************************************************************
 * Copyright (c) 2010 Angelo ZERR.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:      
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.gmt.modisco.jm2t.internal.ui;

import org.eclipse.osgi.util.NLS;

/**
 * Messages used in the Plug-In JM2TUi.
 * 
 */
public class Messages extends NLS {

	public static String columnType;
	public static String columnName;
	public static String preferenceGeneratorConfigurationsDescription;
	public static String preferenceGeneratorConfigurationsTable;
	public static String preferenceGeneratorTypes;
	public static String errorDialogTitle;

	// Buttons label
	public static String addButton;
	public static String removeButton;
	public static String editButton;
	public static String browseButton;
	public static String createNewFolderButton;

	// Wizard
	public static String wizNewGeneratorConfigurationWizardTitle;		
	public static String wizSelectGeneratorTypeTitle;
	public static String wizSelectGeneratorTypeDescription;
	public static String wizSelectModelConverterTypeTitle;
	public static String wizSelectModelConverterTypeDescription;
	public static String wizNewGeneratorConfigurationTitle;
	public static String wizNewGeneratorConfigurationDescription;	
	public static String wizEditGeneratorConfigurationWizardTitle;
	
	// Fields of the NewGeneratorConfigurationComposite
	public static String NewGeneratorConfigurationComposite_generatorType;
	public static String NewGeneratorConfigurationComposite_modelConverterType;
	public static String NewGeneratorConfigurationComposite_name;
	public static String NewGeneratorConfigurationComposite_templatePath;
	public static String NewGeneratorConfigurationComposite_targetPath;
	
	// TemplateFileSelection
	public static String TemplateFileSelectionDialog_title;
	public static String TemplateFileSelectionDialog_message;
	public static String FolderSelectionDialog_title;
	public static String FolderSelectionDialog_message;	
	
	public static String GeneratorConfigurationDialog_name;
	public static String GeneratorConfigurationDialog_error_enterName;
	public static String GeneratorConfigurationDialog_templateFile;
	public static String GeneratorConfigurationDialog_targetContainer;

	public static String GeneratorTypeComposite_title;
	public static String ModelConverterTypeComposite_title;

	public static String savingSettingsError;
	public static String ContributionItemForJM2TMenu_0;
	public static String MenuListenerHandler_0;
	
	
	
	private static final String BUNDLE_NAME = "org.eclipse.gmt.modisco.jm2t.internal.ui.messages"; //$NON-NLS-1$
	

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
