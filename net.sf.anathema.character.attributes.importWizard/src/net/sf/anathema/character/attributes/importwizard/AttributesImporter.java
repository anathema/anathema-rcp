package net.sf.anathema.character.attributes.importwizard;

import java.io.IOException;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.character.importwizard.ModelImporter;
import net.sf.anathema.character.importwizard.XslDocumentConverter;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

public class AttributesImporter extends AbstractExecutableExtension implements IModelImporter {
  private static final String ATTRIBUTE_STYLESHEET = "xsl/AttributeCreation.xsl"; //$NON-NLS-1$
  private final ModelImporter importer = new ModelImporter(new XslDocumentConverter(
      ATTRIBUTE_STYLESHEET,
      AttributesPlugin.ID,
      Messages.AttributesImporter_ErrorMessage), IAttributesPluginConstants.MODEL_ID);

  @Override
  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException {
    return importer.runImport(container, document);
  }
}