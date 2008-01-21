package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.caste.model.CasteProvider;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.caste.trait.CasteStatusUpdater;
import net.sf.anathema.character.caste.trait.ITraitCollectionProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;

public class CasteModelFactory extends AbstractModelFactory<CasteTemplate> {
  private final CasteModelPersister persister = new CasteModelPersister();
  private final ICasteProvider provider;

  public CasteModelFactory() {
    this(new CasteProvider());
  }

  public CasteModelFactory(ICasteProvider provider) {
    this.provider = provider;
  }

  @Override
  public CasteTemplate createModelTemplate(ICharacterTemplate template) {
    return new CasteTemplate(provider.getCastes(template.getCharacterTypeId()));
  }

  @Override
  public IModel create(IContentHandle modelContent, ICharacterTemplate template, ICharacterId characterId)
      throws PersistenceException,
      CoreException {
    ICasteModel model = (ICasteModel) super.create(modelContent, template, characterId);
    ITraitCollectionProvider traitProvider = new ITraitCollectionProvider() {
      @Override
      public ITraitCollectionModel getModel(ICharacterId id) {
        return (ITraitCollectionModel) ModelCache.getInstance().getModel(
            new ModelIdentifier(id, "net.sf.anathema.character.attributes.model")); //$NON-NLS-1$
      }
    };
    CasteStatusUpdater casteStatusUpdater = new CasteStatusUpdater(model, traitProvider, characterId);
    model.addChangeListener(casteStatusUpdater);
    casteStatusUpdater.stateChanged();
    return model;
  }

  @Override
  protected IModelPersister<CasteTemplate, ? > getPersister() {
    return persister;
  }
}