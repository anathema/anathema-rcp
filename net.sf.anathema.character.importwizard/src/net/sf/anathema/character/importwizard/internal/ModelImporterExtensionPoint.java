package net.sf.anathema.character.importwizard.internal;

import java.text.MessageFormat;
import java.util.ArrayList;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.importwizard.IDocumentConverter;
import net.sf.anathema.character.importwizard.IExecutableConverter;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.character.importwizard.ModelImporter;
import net.sf.anathema.character.importwizard.XslDocumentConverter;
import net.sf.anathema.character.importwizard.plugin.CharacterImportWizardPluginConstants;

public class ModelImporterExtensionPoint {

  private static final Logger LOGGER = new Logger(CharacterImportWizardPluginConstants.PLUGIN_ID);
  private static final String EXTENSION_POINT_MODELIMPORTERS = "modelimporters"; //$NON-NLS-1$
  private static final String TAG_JAVACONVERTER = "javaconverter"; //$NON-NLS-1$
  private static final String TAG_XSLCONVERTER = "xslconverter"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_ID = "modelid"; //$NON-NLS-1$
  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private final EclipseExtensionPoint point;

  public ModelImporterExtensionPoint() {
    this.point = new EclipseExtensionPoint(
        CharacterImportWizardPluginConstants.PLUGIN_ID,
        EXTENSION_POINT_MODELIMPORTERS);
  }

  public Iterable<IModelImporter> getImporters() {
    ArrayList<IModelImporter> importers = new ArrayList<IModelImporter>();
    for (IPluginExtension extension : point.getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        String modelId = element.getAttribute(ATTRIB_MODEL_ID);
        IDocumentConverter converter = createConverter(element);
        if (converter != null) {
          importers.add(new ModelImporter(converter, modelId));
        }
      }
    }
    return importers;
  }

  private IDocumentConverter createConverter(IExtensionElement element) {
    IExtensionElement javaElement = element.getElement(TAG_JAVACONVERTER);
    if (javaElement == null) {
      return new XslDocumentConverter(element.getElement(TAG_XSLCONVERTER));
    }
    try {
      return javaElement.getAttributeAsObject(ATTRIB_CLASS, IExecutableConverter.class);
    }
    catch (ExtensionException e) {
      String message = MessageFormat.format("Failed to instantiate importer class {0}", //$NON-NLS-1$
          javaElement.getAttribute(ATTRIB_CLASS));
      LOGGER.error(message, e);
      return null;
    }
  }
}