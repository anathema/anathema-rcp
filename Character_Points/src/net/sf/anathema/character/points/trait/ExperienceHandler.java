package net.sf.anathema.character.points.trait;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class ExperienceHandler extends AbstractExecutableExtension implements IPointHandler {

  private final String modelId;
  private final int baseCost;
  private final int newCost;

  public ExperienceHandler(String modelId, int baseCost, int newCost) {
    this.modelId = modelId;
    this.baseCost = baseCost;
    this.newCost = newCost;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelIdentifier identifier = new ModelIdentifier(characterId, modelId);
    ITraitCollectionModel attributes = (ITraitCollectionModel) ModelCache.getInstance().getModel(identifier);
    return new TraitCollectionExperienceCalculator(attributes, baseCost, newCost).calculate();
  }
}