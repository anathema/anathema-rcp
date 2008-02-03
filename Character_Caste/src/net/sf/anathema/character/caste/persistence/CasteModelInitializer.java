package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.caste.trait.CasteStatusUpdater;
import net.sf.anathema.character.caste.trait.ITraitCollectionProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelInitializer;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public final class CasteModelInitializer extends ModelInitializer {

  public CasteModelInitializer(IContentHandle file, IModelIdentifier modelIdentifier, ICasteModel model) {
    super(model, file, modelIdentifier);
  }

  @Override
  public void initialize() {
    super.initialize();
    ITraitCollectionProvider traitProvider = getTraitCollectionProvider();
    ICasteModel model = (ICasteModel) getModel();
    ICharacterId characterId = getModelIdentifier().getCharacterId();
    CasteStatusUpdater casteStatusUpdater = new CasteStatusUpdater(model, traitProvider, characterId);
    model.addChangeListener(casteStatusUpdater);
    casteStatusUpdater.stateChanged();
  }

  private ITraitCollectionProvider getTraitCollectionProvider() {
    // TODO: Case 108: Richtigen ITraitCollectionProvider verwenden (ExtensionPoint einführen)
    return new ITraitCollectionProvider() {
      @Override
      public ITraitCollectionModel getModel(ICharacterId id) {
        return (ITraitCollectionModel) ModelCache.getInstance().getModel(
            new ModelIdentifier(id, "net.sf.anathema.character.attributes.model")); //$NON-NLS-1$
      }
    };
  }
}