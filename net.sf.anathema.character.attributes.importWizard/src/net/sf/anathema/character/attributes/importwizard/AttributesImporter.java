package net.sf.anathema.character.attributes.importwizard;

import java.io.IOException;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.character.importwizard.ModelImporter;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

public class AttributesImporter extends AbstractExecutableExtension implements IModelImporter {

  private final ModelImporter importer = new ModelImporter(new AttributesConverter(), IAttributesPluginConstants.MODEL_ID);

  @Override
  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException {
    return importer.runImport(container, document);
  }
}