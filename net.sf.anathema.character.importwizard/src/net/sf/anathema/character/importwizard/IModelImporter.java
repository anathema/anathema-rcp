package net.sf.anathema.character.importwizard;

import java.io.IOException;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

public interface IModelImporter {

  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException;
}