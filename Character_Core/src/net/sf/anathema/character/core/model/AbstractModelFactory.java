package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;

public abstract class AbstractModelFactory<T extends IModelTemplate> extends AbstractExecutableExtension implements IModelFactory {

  @Override
  public IModel create(IContentHandle modelContent) throws PersistenceException, CoreException {
    IModelPersister<T, ?> persister = getPersister();
    if (!modelContent.exists()) {
      return persister.createNew(createModelTemplate());
    }
    Document document = DocumentUtilities.read(modelContent.getContents());
    return persister.load(document);
  }
  
  protected abstract T createModelTemplate();

  protected abstract IModelPersister<T, ?> getPersister();
}