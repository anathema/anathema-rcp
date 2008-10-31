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
import net.sf.anathema.character.trait.model.TypeTraitModelIdProvider;
import net.sf.anathema.character.trait.model.ITraitModelIdProvider;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;

public class CasteModelFactory extends AbstractModelFactory<CasteTemplate, ICasteModel> {
  private final CasteModelPersister persister = new CasteModelPersister();
  private final ICasteCollection provider;
  private final ITraitModelIdProvider traitModelIdProvider;

  public CasteModelFactory() {
    this(new CasteCollection(), new TypeTraitModelIdProvider());
  }

  public CasteModelFactory(ICasteCollection provider, ITraitModelIdProvider traitModelIdProvider) {
    this.provider = provider;
    this.traitModelIdProvider = traitModelIdProvider;
  }

  @Override
  public CasteTemplate createModelTemplate(ICharacterTemplate template) {
    String characterTypeId = template.getCharacterTypeId();
    String traitModelId = traitModelIdProvider.getTraitModelId(characterTypeId);
    return new CasteTemplate(traitModelId , provider.getCastes(characterTypeId));
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