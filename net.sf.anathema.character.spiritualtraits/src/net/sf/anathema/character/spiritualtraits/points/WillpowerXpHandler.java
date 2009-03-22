package net.sf.anathema.character.spiritualtraits.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class WillpowerXpHandler extends AbstractPointHandler<ITraitCollectionModel>{

  public WillpowerXpHandler() {
    super(IPluginConstants.MODEL_ID);
  }
  
  @Override
  protected int calculatePoints(ITraitCollectionModel model, ICharacterId characterId) {
    IBasicTrait willpower = model.getTrait(IPluginConstants.WILLPOWER_ID);
    int basicValue = willpower.getCreationModel().getValue();
    int experiencedValue = willpower.getExperiencedModel().getValue();
    return (experiencedValue-basicValue)*2;
  }
}