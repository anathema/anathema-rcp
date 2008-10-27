package net.sf.anathema.character.freebies.abilities;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FavoredFreebiesBonusPointReducer extends AbstractPointHandler {
  private final ICreditManager creditManager;
  private final IModelCollection modelCollection;

  public FavoredFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public FavoredFreebiesBonusPointReducer(
      IModelCollection modelCollection,
      ICreditManager creditManager) {
    super(modelCollection, IAbilitiesPluginConstants.MODEL_ID);
    this.modelCollection = modelCollection;
    this.creditManager = creditManager;
  }

  @Override
  public int calculatePoints(ITraitCollectionModel abilities, ICharacterId characterId) {
    int credit = creditManager.getCredit(characterId, IAbilityFreebiesConstants.FAVORED_CREDIT);
    FavoredFreebiesHandler favoredFreebiesHandler = new FavoredFreebiesHandler(modelCollection);
    return -favoredFreebiesHandler.getPoints(abilities, credit);
  }
}