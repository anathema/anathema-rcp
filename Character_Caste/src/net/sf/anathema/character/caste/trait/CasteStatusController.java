package net.sf.anathema.character.caste.trait;

import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.core.character.ICharacterId;

public class CasteStatusController {

  public CasteStatusController(ICasteModel model, ITraitCollectionProvider traitProvider, ICharacterId id) {
    model.addChangeListener(new CasteStateUpdater(model, traitProvider, id));
  }
}