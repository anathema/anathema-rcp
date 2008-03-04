package net.sf.anathema.character.points.trait;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class ExperienceHandler extends AbstractExecutableExtension implements IPointHandler {

  private final String modelId;

  public ExperienceHandler(String modelId) {
    this.modelId = modelId;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelIdentifier identifier = new ModelIdentifier(characterId, modelId);
    ITraitCollectionModel attributes = (ITraitCollectionModel) ModelCache.getInstance().getModel(identifier);
    return new TraitCollectionExperienceCalculator(attributes).calculate();
  }
}