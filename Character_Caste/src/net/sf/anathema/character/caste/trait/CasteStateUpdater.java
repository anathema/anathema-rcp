package net.sf.anathema.character.caste.trait;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public final class CasteStateUpdater implements IChangeListener {

  private final ICasteModel model;
  private final ITraitCollectionProvider traitProvider;
  private final ICharacterId id;

  public CasteStateUpdater(ICasteModel model, ITraitCollectionProvider traitProvider, ICharacterId id) {
    this.model = model;
    this.traitProvider = traitProvider;
    this.id = id;
  }

  @Override
  public void stateChanged() {
    ITraitCollectionModel traitCollectionModel = traitProvider.getModel(id);
    traitCollectionModel.setStatusFor(new CasteStatus(), model.getCaste().getTraitTypes());
  }
}