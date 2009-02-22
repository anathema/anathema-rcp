package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;

public abstract class AbstractModelFactory<T extends IModelTemplate, M extends IModel> extends
    UnconfiguredExecutableExtension implements IModelFactory<M> {

  protected final M create(IContentHandle modelContent, ICharacterTemplate template)
      throws PersistenceException,
      CoreException {
    IModelPersister<T, M> persister = getPersister();
    T modelTemplate = createModelTemplate(template);
    if (!modelContent.exists()) {
      return persister.createNew(modelTemplate);
    }
    Document document = DocumentUtilities.read(modelContent.getContents());
    return persister.load(document, modelTemplate);
  }

  protected abstract T createModelTemplate(ICharacterTemplate template);

  protected abstract IModelPersister<T, M> getPersister();

  @Override
  public IModelInitializer createInitializer(
      IContentHandle handle,
      ICharacterTemplate template,
      ICharacterType characterType,
      IModelIdentifier identifier) throws PersistenceException, CoreException {
    M model = create(handle, template);
    return new ModelInitializer(model, handle, identifier);
  }
}