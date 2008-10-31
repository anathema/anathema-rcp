package net.sf.anathema.character.caste.trait;

import java.util.Collections;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.util.IIdentificate;

public final class CasteStatusUpdater implements IChangeListener {

  private final ICasteModel model;
  private final ITraitCollectionProvider traitProvider;
  private final ICharacterId id;

  public CasteStatusUpdater(ICasteModel model, ITraitCollectionProvider traitProvider, ICharacterId id) {
    this.model = model;
    this.traitProvider = traitProvider;
    this.id = id;
  }

  @Override
  public void stateChanged() {
    ITraitCollectionModel traitCollectionModel = traitProvider.getModel(id);
    boolean isClean = !traitCollectionModel.isDirty();
    List< ? extends IIdentificate> traitIds = Collections.emptyList();
    ICaste caste = model.getCaste();
    if (caste != null) {
      traitIds = model.getCaste().getTraitTypes();
    }
    traitCollectionModel.setStatusFor(new CasteStatus(), traitIds);
    if (isClean) {
      traitCollectionModel.setClean();
    }
  }
}