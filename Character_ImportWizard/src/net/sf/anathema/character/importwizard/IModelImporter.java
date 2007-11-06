package net.sf.anathema.character.importwizard;

import java.io.IOException;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;

public interface IModelImporter extends IExecutableExtension {

  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException;
}