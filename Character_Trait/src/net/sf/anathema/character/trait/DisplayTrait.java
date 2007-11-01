package net.sf.anathema.character.trait;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
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
  private AggregatedDisposable allDisposables = new AggregatedDisposable();
  private IDisplayFavorization favorization;
  private final ITraitPreferences traitPreferences;

  public DisplayTrait(
      final IBasicTrait basicTrait,
      final IExperience experience,
      IDisplayFavorization favorization,
      ITraitTemplate traitTemplate,
      ITraitPreferences traitPreferences) {
    this.traitPreferences = traitPreferences;
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitTemplate);
    this.favorization = favorization;
    this.basicTrait = basicTrait;
    basicTrait.getCreationModel().addChangeListener(changeListener);
    basicTrait.getExperiencedModel().addChangeListener(changeListener);
    experience.addChangeListener(changeListener);
    allDisposables.addDisposable(new ChangeableModelDisposable(basicTrait.getCreationModel(), changeListener));
    allDisposables.addDisposable(new ChangeableModelDisposable(basicTrait.getExperiencedModel(), changeListener));
    allDisposables.addDisposable(changeControl);
    allDisposables.addDisposable(favorization);
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
  public IDisplayFavorization getFavorization() {
    return favorization;
  }
}