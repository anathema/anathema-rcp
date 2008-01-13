package net.sf.anathema.character.importwizard.internal;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.character.importwizard.plugin.CharacterImportWizardPluginConstants;

public class ModelImporterExtensionPoint {

  private static final String EXTENSION_POINT_MODELIMPORTERS = "modelimporters"; //$NON-NLS-1$

  public Iterable<IModelImporter> getImporters() {
    return new ClassConveyerBelt<IModelImporter>(new EclipseExtensionPoint(
        CharacterImportWizardPluginConstants.PLUGIN_ID,
        EXTENSION_POINT_MODELIMPORTERS), IModelImporter.class).getAllObjects();
  }
}