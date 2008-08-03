package net.sf.anathema.character.freebies.abilities;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.abilities.util.TraitListFactory;
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
    ModelIdentifier identifier = new ModelIdentifier(id, IAbilitiesPluginConstants.MODEL_ID);
    ITraitCollectionModel abilities = (ITraitCollectionModel) modelCollection.getModel(identifier);
    return getPoints(abilities, credit);
  }

  public int getPoints(ITraitCollectionModel abilities, int credit) {
    int dotCount = 0;
    for (IBasicTrait trait : new TraitListFactory().create(abilities)) {
      if (trait.getStatusManager().getStatus().isCheap()) {
        dotCount += Math.min(trait.getCreationModel().getValue(), 3);
      }
    }
    return Math.min(dotCount, credit);
  }
}