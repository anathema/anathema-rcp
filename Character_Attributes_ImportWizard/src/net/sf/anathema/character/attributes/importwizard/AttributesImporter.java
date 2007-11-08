package net.sf.anathema.character.attributes.importwizard;

import java.io.IOException;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.importwizard.IModelImporter;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class AttributesImporter extends AbstractExecutableExtension implements IModelImporter {

  @Override
  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException {
    // TODO Auto-generated method stub
    return Status.OK_STATUS;
  }
}