package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public abstract class AbstractModelFactory extends AbstractExecutableExtension implements IModelFactory {

  @Override
  public final IModel create(IFile modelFile) throws PersistenceException, CoreException {
    IModelPersister<?> persister = getPersister();
    if (!modelFile.exists()) {
      return persister.createNew();
    }
    Document document = DocumentUtilities.read(modelFile.getContents());
    return persister.load(document);
  }

  protected abstract IModelPersister<?> getPersister();
}