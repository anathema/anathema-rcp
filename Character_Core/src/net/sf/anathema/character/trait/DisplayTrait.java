package net.sf.anathema.character.trait;

import net.sf.anathema.character.basics.ICharacterBasics;
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

  public DisplayTrait(IBasicTrait basicTrait, ICharacterBasics basics) {
    this.basicTrait = basicTrait;
    this.basics = basics;
    basicTrait.addCreationChangeListener(creationListener);
    basicTrait.addExperienceChangeListener(experiencedListener);
  }

  @Override
  public int getValue() {
    if (basics.isExperienced() && basicTrait.getExperiencedValue() > -1) {
      return basicTrait.getExperiencedValue();
    }
    return basicTrait.getCreationValue();
  }

  @Override
  public int getMaximalValue() {
    return 5;
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
    if (basics.isExperienced()) {
      basicTrait.setExperiencedValue(value);
    }
    else {
      basicTrait.setCreationValue(value);
    }
  }

  @Override
  public void dispose() {
    basicTrait.removeCreationChangeListener(creationListener);
    basicTrait.removeExperienceChangeListener(experiencedListener);
    changeControl.clear();
  }
}