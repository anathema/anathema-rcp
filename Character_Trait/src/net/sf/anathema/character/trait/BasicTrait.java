package net.sf.anathema.character.trait;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.interactive.IntValueModel;
import net.sf.anathema.lib.util.IIdentificate;

public class BasicTrait implements IBasicTrait {

  private final IIdentificate traitType;
  private final IntValueModel creationModel = new IntValueModel();
  private final IntValueModel experiencedModel = new IntValueModel();
  private final BooleanModel favoredModel = new BooleanModel();

  public BasicTrait(IIdentificate traitType) {
    this.traitType = traitType;
    creationModel.setValue(0);
    experiencedModel.setValue(-1);
  }

  @Override
  public IIntValueModel getCreationModel() {
    return creationModel;
  }

  @Override
  public IIntValueModel getExperiencedModel() {
    return experiencedModel;
  }

  @Override
  public IIdentificate getTraitType() {
    return traitType;
  }
  
  public BooleanModel getFavoredModel() {
    return favoredModel;
  }

  public int getListenerCount() {
    return creationModel.getListenerCount() + experiencedModel.getListenerCount();
  }

  @Override
  public boolean isExperiencedValueSet() {
    return getExperiencedModel().getValue() > 0;
  }
}