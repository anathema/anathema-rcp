package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;

public abstract class AbstractModelFactory extends AbstractExecutableExtension implements IModelFactory {

  @Override
  public IModel create(IContentHandle modelContent) throws PersistenceException, CoreException {
    IModelPersister<?> persister = getPersister();
    if (!modelContent.exists()) {
      return persister.createNew();
    }
    Document document = DocumentUtilities.read(modelContent.getContents());
    return persister.load(document);
  }

  protected abstract IModelPersister<?> getPersister();
}