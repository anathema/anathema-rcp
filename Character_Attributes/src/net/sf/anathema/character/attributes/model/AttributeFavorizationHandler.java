package net.sf.anathema.character.attributes.model;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.lib.util.IIdentificate;

public class AttributeFavorizationHandler implements IFavorizationHandler {

  private final ICharacterId characterId;
  private final ICreditManager creditManager;
  private final IModelProvider modelProvider;

  public AttributeFavorizationHandler(
      ICharacterId characterId,
      IModelProvider modelProvider,
      ICreditManager creditManager) {
    this.characterId = characterId;
    this.modelProvider = modelProvider;
    this.creditManager = creditManager;
  }

  @Override
  public boolean isFavorable() {
    return getCredit() > 0;
  }

  @Override
  public void toogleFavored(IIdentificate traitType) {
    if (!isFavorable()) {
      return;
    }
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, IAttributes.MODEL_ID);
    IAttributes attributes = (IAttributes) modelProvider.getModel(modelIdentifier);
    IBasicTrait trait = attributes.getTrait(traitType.getId());
    BooleanModel favoredModel = trait.getFavoredModel();
    if (isToggleFavoredAllowed(attributes, trait)) {
      favoredModel.setValue(!favoredModel.getValue());
    }
  }

  private boolean isToggleFavoredAllowed(IAttributes attributes, IBasicTrait trait) {
    boolean isFavored = trait.getFavoredModel().getValue();
    if (isFavored) {
      return true;
    }
    int favoredCount = 0;
    for (IBasicTrait attribute : attributes.getTraits()) {
      if (attribute.getFavoredModel().getValue()) {
        favoredCount++;
      }
    }
    return favoredCount < getCredit();
  }

  private int getCredit() {
    return creditManager.getCredit(characterId, "net.sf.anathema.character.attributes.favored"); //$NON-NLS-1$
  }
}