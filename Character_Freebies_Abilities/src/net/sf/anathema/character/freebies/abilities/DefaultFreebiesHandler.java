package net.sf.anathema.character.freebies.abilities;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class DefaultFreebiesHandler extends AbstractExecutableExtension implements IFreebiesHandler {

  private final IModelCollection modelCollection;
  private final ICreditManager creditManager;

  public DefaultFreebiesHandler() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public DefaultFreebiesHandler(IModelCollection modelCollection, ICreditManager creditManager) {
    this.modelCollection = modelCollection;
    this.creditManager = creditManager;
  }

  @Override
  public String getCreditId() {
    return IAbilityFreebiesConstants.UNLIMITED_CREDIT;
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    IModelIdentifier identifier = new ModelIdentifier(id, IAbilitiesPluginConstants.MODEL_ID);
    ITraitCollectionModel abilities = (ITraitCollectionModel) modelCollection.getModel(identifier);
    int totalSpent = 0;
    for (IBasicTrait trait : abilities.getTraits()) {
      totalSpent += Math.min(trait.getCreationModel().getValue(), 3);
    }
    int favoredCredit = creditManager.getCredit(id, IAbilityFreebiesConstants.FAVORED_CREDIT);
    int favoredSpent = new FavoredFreebiesHandler(modelCollection).getPoints(id, favoredCredit);
    return Math.min(credit, totalSpent - favoredSpent);
  }
}