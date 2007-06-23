package net.sf.anathema.character.trait;

import net.sf.anathema.character.basics.ICharacterBasics;
import net.sf.anathema.character.trait.rules.ITraitRules;
import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class DisplayTrait extends ChangeManagement implements IDisplayTrait {

  private final IBasicTrait basicTrait;
  private final ICharacterBasics basics;
  private final ChangeControl changeControl = new ChangeControl();
  private final IChangeListener creationListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      if (!basics.isExperienced()) {
        changeControl.fireChangedEvent();
      }
    }
  };
  private final IChangeListener experiencedListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      if (basics.isExperienced()) {
        changeControl.fireChangedEvent();
      }
    }
  };
  private final ITraitRules traitRules;

  public DisplayTrait(IBasicTrait basicTrait, ICharacterBasics basics, ITraitRules traitRules) {
    this.basicTrait = basicTrait;
    this.basics = basics;
    this.traitRules = traitRules;
    basicTrait.getCreationModel().addValueChangeListener(creationListener);
    basicTrait.getExperiencedModel().addValueChangeListener(experiencedListener);
  }

  @Override
  public int getValue() {
    if (basics.isExperienced() && basicTrait.getExperiencedModel().getValue() > -1) {
      return basicTrait.getExperiencedModel().getValue();
    }
    return basicTrait.getCreationModel().getValue();
  }

  @Override
  public int getMaximalValue() {
    return traitRules.getMaximalValue();
  }

  @Override
  public IIdentificate getTraitType() {
    return basicTrait.getTraitType();
  }

  @Override
  public void addValueChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeValueChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }

  @Override
  public void setValue(int value) {
    int correctedValue = traitRules.getCorrectedValue(value);
    if (basics.isExperienced()) {
      basicTrait.getExperiencedModel().setValue(correctedValue);
    }
    else {
      basicTrait.getCreationModel().setValue(correctedValue);
    }
  }

  @Override
  public void dispose() {
    basicTrait.getCreationModel().removeValueChangeListener(creationListener);
    basicTrait.getExperiencedModel().removeValueChangeListener(experiencedListener);
    changeControl.clear();
  }
}