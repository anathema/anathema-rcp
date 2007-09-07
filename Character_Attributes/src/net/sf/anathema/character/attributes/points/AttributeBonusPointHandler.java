package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeBonusPointHandler extends AbstractExecutableExtension implements IPointHandler {

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelIdentifier identifier = new ModelIdentifier(characterId, Attributes.MODEL_ID);
    ITraitCollectionModel attributes = (ITraitCollectionModel) ModelCache.getInstance().getModel(identifier);
    return new AttributeBonusPointCalculator(attributes).calculate();
  }
}