package net.sf.anathema.character.trait.interactive;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.display.DisplayTrait;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.template.ITraitTemplate;
import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;
import net.sf.anathema.lib.util.IIdentificate;

public class InteractiveTrait extends ChangeManagement implements IInteractiveTrait {

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
  private final AggregatedDisposable allDisposables = new AggregatedDisposable();
  private final IBasicTrait basicTrait;
  private final IDisplayTrait displayTrait;
  private final IInteractiveFavorization favorization;
  private final ITraitPreferences traitPreferences;
  private final ITraitTemplate traitTemplate;
  private final IExperience experience;

  public InteractiveTrait(
      final IBasicTrait basicTrait,
      final IExperience experience,
      IInteractiveFavorization favorization,
      ITraitTemplate traitTemplate,
      ITraitPreferences traitPreferences) {
    this.experience = experience;
    this.traitTemplate = traitTemplate;
    this.traitPreferences = traitPreferences;
    this.displayTrait = new DisplayTrait(favorization, basicTrait, experience, traitTemplate);
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
    return displayTrait.getValue();
  }

  @Override
  public int getMaximalValue() {
    return displayTrait.getMaximalValue();
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
    int correctedValue = getCorrectedValue(value);
    if (experience.isExperienced()) {
      basicTrait.getExperiencedModel().setValue(correctedValue);
    }
    else {
      basicTrait.getCreationModel().setValue(correctedValue);
    }
  }

  private int getCorrectedValue(int value) {
    if (experience.isExperienced() && value < basicTrait.getCreationModel().getValue()) {
      return basicTrait.getCreationModel().getValue();
    }
    if (value < traitTemplate.getMinimalValue()) {
      return traitTemplate.getMinimalValue();
    }
    if (value > getMaximalValue()) {
      return getMaximalValue();
    }
    return value;
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