package net.sf.anathema.character.freebies.abilities;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class FavoredFreebiesHandler extends AbstractExecutableExtension implements IFreebiesHandler {

  private final IModelCollection modelCollection;

  public FavoredFreebiesHandler() {
    this(ModelCache.getInstance());
  }

  public FavoredFreebiesHandler(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }

  @Override
  public String getCreditId() {
    return IAbilityFreebiesConstants.FAVORED_CREDIT;
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    ITraitCollectionModel abilities = getAbilities(id);
    int dotCount = 0;
    for (IBasicTrait trait : abilities.getTraits()) {
      if (trait.getStatusManager().getStatus().isCheap()) {
        dotCount += trait.getCreationModel().getValue();
      }
    }
    return Math.min(dotCount, credit);
  }

  private ITraitCollectionModel getAbilities(ICharacterId id) {
    return (ITraitCollectionModel) modelCollection.getModel(new ModelIdentifier(id, IAbilitiesPluginConstants.MODEL_ID));
  }
}