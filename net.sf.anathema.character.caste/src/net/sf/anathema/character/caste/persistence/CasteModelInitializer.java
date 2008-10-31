package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.caste.trait.CasteStatusUpdater;
import net.sf.anathema.character.caste.trait.ITraitCollectionProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelInitializer;

public final class CasteModelInitializer extends ModelInitializer {

  public CasteModelInitializer(IContentHandle file, IModelIdentifier modelIdentifier, ICasteModel model) {
    super(model, file, modelIdentifier);
  }

  @Override
  public void initialize() {
    super.initialize();
    ICasteModel model = (ICasteModel) getModel();
    ITraitCollectionProvider traitProvider = getTraitCollectionProvider();
    ICharacterId characterId = getModelIdentifier().getCharacterId();
    CasteStatusUpdater casteStatusUpdater = new CasteStatusUpdater(model, traitProvider, characterId);
    model.addChangeListener(casteStatusUpdater);
    casteStatusUpdater.stateChanged();
  }

  private ITraitCollectionProvider getTraitCollectionProvider() {
    ICasteModel model = (ICasteModel) getModel();
    return new TraitCollectionProvider(ModelCache.getInstance(), model.getTraitModelId());
  }
}