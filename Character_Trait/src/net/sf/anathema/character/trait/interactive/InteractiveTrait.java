package net.sf.anathema.character.trait.interactive;

import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.display.DisplayTrait;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.ValidatorFactory;
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
  private final IExperience experience;
  private final List<IValidator> valueValidators;

  public InteractiveTrait(
      final IBasicTrait basicTrait,
      final IModelContainer container,
      final IInteractiveFavorization favorization,
      final int minimalValue,
      ITraitPreferences traitPreferences) {
    experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    this.traitPreferences = traitPreferences;
    this.favorization = favorization;
    this.basicTrait = basicTrait;
    displayTrait = new DisplayTrait(favorization, basicTrait, container, 5);
    valueValidators = new ValidatorFactory().create(container, basicTrait, minimalValue);
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
    favorization.addFavoredChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        if (favorization.isFavored()) {
          setValue(getValue());
        }
      }
    });
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
    int correctedValue = value;
    for (IValidator validator : valueValidators) {
      correctedValue = validator.getValidValue(correctedValue);
    }
    return correctedValue;
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