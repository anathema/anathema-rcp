package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeBonusPointHandler extends AbstractPointHandler {

  public AttributeBonusPointHandler() {
    super(IAttributesPluginConstants.MODEL_ID);
  }

  public AttributeBonusPointHandler(IModelCollection modelCollection, IModelResourceHandler resourceHandler) {
    super(modelCollection, IAttributesPluginConstants.MODEL_ID);
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel attributes, ICharacterId characterId) {
    return new AttributeBonusPointCalculator(attributes).calculate();
  }
}