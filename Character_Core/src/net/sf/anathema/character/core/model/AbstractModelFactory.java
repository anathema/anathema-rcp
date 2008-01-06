package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;

public abstract class AbstractModelFactory<T extends IModelTemplate> extends AbstractExecutableExtension implements IModelFactory {

  @Override
  public IModel create(IContentHandle modelContent, ICharacterTemplate template) throws PersistenceException, CoreException {
    IModelPersister<T, ?> persister = getPersister();
    T modelTemplate = createModelTemplate(template);
    if (!modelContent.exists()) {
      return persister.createNew(modelTemplate);
    }
    Document document = DocumentUtilities.read(modelContent.getContents());
    return persister.load(document, modelTemplate);
  }
  
  protected abstract T createModelTemplate(ICharacterTemplate template);

  protected abstract IModelPersister<T, ?> getPersister();
}