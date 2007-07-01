package net.sf.anathema.character.trait;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.rules.IRuleTrait;
import net.sf.anathema.character.trait.rules.ITraitTemplate;
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
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      changeControl.fireChangedEvent();
    }
  };

  public DisplayTrait(IBasicTrait basicTrait, IExperience experience, ITraitTemplate traitRules) {
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitRules);
    this.basicTrait = basicTrait;
    this.experience = experience;
    basicTrait.getCreationModel().addValueChangeListener(changeListener);
    basicTrait.getExperiencedModel().addValueChangeListener(changeListener);
    experience.addChangeListener(changeListener);
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
    basicTrait.getCreationModel().removeValueChangeListener(changeListener);
    basicTrait.getExperiencedModel().removeValueChangeListener(changeListener);
    experience.removeChangeListener(changeListener);
    changeControl.clear();
  }
}