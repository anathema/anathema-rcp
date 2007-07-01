package net.sf.anathema.character.trait;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.rules.IRuleTrait;
import net.sf.anathema.character.trait.rules.ITraitRules;
import net.sf.anathema.character.trait.rules.RuleTrait;
import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class DisplayTrait extends ChangeManagement implements IDisplayTrait {

  private final IBasicTrait basicTrait;
  private final IExperience experience;
  private final IRuleTrait ruleTrait;
  private final ChangeControl changeControl = new ChangeControl();
  private final IChangeListener creationListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      if (!experience.isExperienced()) {
        changeControl.fireChangedEvent();
      }
    }
  };
  private final IChangeListener experiencedListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      if (experience.isExperienced()) {
        changeControl.fireChangedEvent();
      }
    }
  };

  public DisplayTrait(IBasicTrait basicTrait, IExperience experience, ITraitRules traitRules) {
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitRules);
    this.basicTrait = basicTrait;
    this.experience = experience;
    basicTrait.getCreationModel().addValueChangeListener(creationListener);
    basicTrait.getExperiencedModel().addValueChangeListener(experiencedListener);
  }

  @Override
  public int getValue() {
    return ruleTrait.getValue();
  }

  @Override
  public int getMaximalValue() {
    return ruleTrait.getMaximalValue();
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
    ruleTrait.setValue(value);
  }

  @Override
  public void dispose() {
    basicTrait.getCreationModel().removeValueChangeListener(creationListener);
    basicTrait.getExperiencedModel().removeValueChangeListener(experiencedListener);
    changeControl.clear();
  }
}