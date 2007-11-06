package net.sf.anathema.character.importwizard.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.character.importwizard.plugin.CharacterImportWizardPluginConstants;

import org.eclipse.osgi.util.NLS;

public class ModelImporterExtensionPoint {

  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_MODELIMPORTERS = "modelimporters"; //$NON-NLS-1$
  private static final Logger logger = new Logger(CharacterImportWizardPluginConstants.PLUGIN_ID);

  public Iterable<IModelImporter> getImporters() {
    List<IModelImporter> importer = new ArrayList<IModelImporter>();
    for (IPluginExtension extension : new EclipseExtensionPoint(
        CharacterImportWizardPluginConstants.PLUGIN_ID,
        EXTENSION_POINT_MODELIMPORTERS).getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          importer.add(element.getAttributeAsObject(ATTRIB_CLASS, IModelImporter.class));
        }
        catch (ExtensionException e) {
          logger.error(NLS.bind(Messages.ModelImporterExtensionPoint_ErrorMessage, extension.getContributorId()), e);
        }
      }
    }
    return importer;
  }
}