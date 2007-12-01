package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeBonusPointHandler extends AbstractPointHandler {

  public AttributeBonusPointHandler() {
    super();
  }

  public AttributeBonusPointHandler(IModelCollection modelCollection, IModelResourceHandler resourceHandler) {
    super(modelCollection, resourceHandler);
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel attributes, ICharacterId characterId) {
    return new AttributeBonusPointCalculator(attributes).calculate();
  }
}