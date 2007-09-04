package net.sf.anathema.character.trait;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.rules.ITraitTemplate;
import net.sf.anathema.character.trait.rules.internal.IRuleTrait;
import net.sf.anathema.character.trait.rules.internal.RuleTrait;
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
  private final IFavorizationHandler favorizationHandler;

  public DisplayTrait(
      IBasicTrait basicTrait,
      IExperience experience,
      IFavorizationHandler favorizationHandler,
      ITraitTemplate traitTemplate) {
    this.favorizationHandler = favorizationHandler;
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitTemplate);
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

  @Override
  public boolean isFavorable() {
    return favorizationHandler.isFavorable();
  }

  @Override
  public void toggleFavored() {
    favorizationHandler.toogleFavored(getTraitType());
  }

  @Override
  public void addFavoredChangeListener(IChangeListener listener) {
    // TODO Listener anmelden 
  }

  @Override
  public boolean isFavored() {
    return basicTrait.getFavoredModel().getValue();
  }

  @Override
  public void removeFavoredChangeListener(IChangeListener listener) {
    // TODO Listener abmelden 
  }
}