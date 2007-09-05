package net.sf.anathema.character.trait;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.rules.ITraitTemplate;
import net.sf.anathema.character.trait.rules.internal.IRuleTrait;
import net.sf.anathema.character.trait.rules.internal.RuleTrait;
import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;
import net.sf.anathema.lib.util.IIdentificate;

public class DisplayTrait extends ChangeManagement implements IDisplayTrait {

  private final IBasicTrait basicTrait;
  private final IRuleTrait ruleTrait;
  private final ChangeControl changeControl = new ChangeControl();
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      changeControl.fireChangedEvent();
    }
  };
  private final IFavorizationHandler favorizationHandler;
  private AggregatedDisposable allDisposables = new AggregatedDisposable();

  public DisplayTrait(
      final IBasicTrait basicTrait,
      final IExperience experience,
      IFavorizationHandler favorizationHandler,
      ITraitTemplate traitTemplate) {
    this.favorizationHandler = favorizationHandler;
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitTemplate);
    this.basicTrait = basicTrait;
    basicTrait.getCreationModel().addChangeListener(changeListener);
    basicTrait.getExperiencedModel().addChangeListener(changeListener);
    experience.addChangeListener(changeListener);
    allDisposables.addDisposable(new ChangeableModelDisposable(basicTrait.getCreationModel(), changeListener));
    allDisposables.addDisposable(new ChangeableModelDisposable(basicTrait.getExperiencedModel(), changeListener));
    allDisposables.addDisposable(changeControl);
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
  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }

  @Override
  public void setValue(int value) {
    ruleTrait.setValue(value);
  }

  @Override
  public void dispose() {
    allDisposables.dispose();
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
    basicTrait.getFavoredModel().addChangeListener(listener);
    allDisposables.addDisposable(new ChangeableModelDisposable(basicTrait.getFavoredModel(), listener));
  }

  @Override
  public boolean isFavored() {
    return basicTrait.getFavoredModel().getValue();
  }
}