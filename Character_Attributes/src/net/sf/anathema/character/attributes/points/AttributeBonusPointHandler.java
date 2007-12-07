package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AttributeBonusPointHandler extends AbstractPointHandler {

  private static final String HANDLER_TYPE = "attributes"; //$NON-NLS-1$

  public AttributeBonusPointHandler() {
    super(HANDLER_TYPE);
  }

  public AttributeBonusPointHandler(IModelCollection modelCollection, IModelResourceHandler resourceHandler) {
    super(modelCollection, resourceHandler, HANDLER_TYPE);
  }

  @Override
  protected int calculatePoints(ITraitCollectionModel attributes, ICharacterId characterId) {
    return new AttributeBonusPointCalculator(attributes).calculate();
  }
}