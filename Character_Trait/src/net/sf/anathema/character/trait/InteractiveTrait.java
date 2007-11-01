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

public class InteractiveTrait extends ChangeManagement implements IInteractiveTrait {

  private final IBasicTrait basicTrait;
  private final IRuleTrait ruleTrait;
  private final ChangeControl changeControl = new ChangeControl();
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      changeControl.fireChangedEvent();
    }
  };
  private final IChangeListener experienceTreatmentListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      traitPreferences.getExperienceTreatment().adjust(basicTrait);
    }
  };
  private AggregatedDisposable allDisposables = new AggregatedDisposable();
  private IInteractiveFavorization favorization;
  private final ITraitPreferences traitPreferences;

  public InteractiveTrait(
      final IBasicTrait basicTrait,
      final IExperience experience,
      IInteractiveFavorization favorization,
      ITraitTemplate traitTemplate,
      ITraitPreferences traitPreferences) {
    this.traitPreferences = traitPreferences;
    this.ruleTrait = new RuleTrait(basicTrait, experience, traitTemplate);
    this.favorization = favorization;
    this.basicTrait = basicTrait;
    basicTrait.getCreationModel().addChangeListener(changeListener);
    basicTrait.getCreationModel().addChangeListener(experienceTreatmentListener);
    basicTrait.getExperiencedModel().addChangeListener(changeListener);
    experience.addChangeListener(changeListener);
    allDisposables.addDisposable(new ChangeableModelDisposable(basicTrait.getCreationModel(), changeListener));
    allDisposables.addDisposable(new ChangeableModelDisposable(basicTrait.getExperiencedModel(), changeListener));
    allDisposables.addDisposable(new ChangeableModelDisposable(
        basicTrait.getCreationModel(),
        experienceTreatmentListener));
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
  public IInteractiveFavorization getFavorization() {
    return favorization;
  }
}