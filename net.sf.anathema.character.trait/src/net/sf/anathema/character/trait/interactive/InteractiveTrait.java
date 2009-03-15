package net.sf.anathema.character.trait.interactive;

import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.display.DisplayTrait;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.ValidationUtilities;
import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;
import net.sf.anathema.lib.util.IIdentificate;

public class InteractiveTrait extends ChangeManagement implements IInteractiveTrait {

  private final class ValidationTrigger implements IChangeListener {

    @Override
    public void stateChanged() {
      if (favorization.isFavored()) {
        setValue(getValue());
      }
    }
  }

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
  private final IExperience experience;
  private final List<IValidator> valueValidators;

  public InteractiveTrait(
      IBasicTrait basicTrait,
      IExperience experience,
      IInteractiveFavorization favorization,
      List<IValidator> valueValidators,
      ITraitPreferences traitPreferences,
      int maxValue) {
    this.experience = experience;
    this.traitPreferences = traitPreferences;
    this.favorization = favorization;
    this.valueValidators = valueValidators;
    this.basicTrait = basicTrait;
    displayTrait = new DisplayTrait(favorization, basicTrait, experience, maxValue);
    basicTrait.getCreationModel().addChangeListener(changeListener);
    basicTrait.getCreationModel().addChangeListener(experienceTreatmentListener);
    basicTrait.getExperiencedModel().addChangeListener(changeListener);
    experience.addChangeListener(changeListener);
    addDisposables();
    favorization.addFavoredChangeListener(new ValidationTrigger());
  }

  private void addDisposables() {
    IIntValueModel creationModel = basicTrait.getCreationModel();
    IIntValueModel experiencedModel = basicTrait.getExperiencedModel();
    allDisposables.addDisposable(new ChangeableModelDisposable(creationModel, changeListener));
    allDisposables.addDisposable(new ChangeableModelDisposable(experiencedModel, changeListener));
    allDisposables.addDisposable(new ChangeableModelDisposable(creationModel, experienceTreatmentListener));
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
    int correctedValue = ValidationUtilities.getCorrectedValue(valueValidators, value);
    if (experience.isExperienced()) {
      basicTrait.getExperiencedModel().setValue(correctedValue);
    }
    else {
      basicTrait.getCreationModel().setValue(correctedValue);
    }
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