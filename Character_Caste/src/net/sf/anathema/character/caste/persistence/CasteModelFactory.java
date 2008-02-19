package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.caste.model.CasteCollection;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelInitializer;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;

public class CasteModelFactory extends AbstractModelFactory<CasteTemplate, ICasteModel> {
  private final CasteModelPersister persister = new CasteModelPersister();
  private final ICasteCollection provider;

  public CasteModelFactory() {
    this(new CasteCollection());
  }

  public CasteModelFactory(ICasteCollection provider) {
    this.provider = provider;
  }

  @Override
  public CasteTemplate createModelTemplate(ICharacterTemplate template) {
    return new CasteTemplate(provider.getCastes(template.getCharacterTypeId()));
  }

  @Override
  public IModelInitializer createInitializer(
      IContentHandle contentHandler,
      ICharacterTemplate template,
      IModelIdentifier identifier) throws PersistenceException, CoreException {
    ICasteModel model = create(contentHandler, template);
    return new CasteModelInitializer(contentHandler, identifier, model);
  }

  @Override
  protected IModelPersister<CasteTemplate, ICasteModel> getPersister() {
    return persister;
  }
}