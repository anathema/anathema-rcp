package net.sf.anathema.character.trait.preference;

import net.sf.anathema.character.trait.BasicTraitUtilities;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IIntValueModel;

public enum ExperienceTraitTreatment {

  LeaveUnchanged {
    @Override
    public void adjust(IBasicTrait basicTrait) {
      // nothing to do
    }
  },
  IncreaseWithCreation {
    @Override
    public void adjust(IBasicTrait basicTrait) {
      if (!BasicTraitUtilities.isExperiencedValueSet(basicTrait)) {
        return;
      }
      int creationValue = basicTrait.getCreationModel().getValue();
      IIntValueModel experiencedModel = basicTrait.getExperiencedModel();
      if (experiencedModel.getValue() < creationValue) {
        experiencedModel.setValue(creationValue);
      }
    }
  };

  public abstract void adjust(IBasicTrait basicTrait);
}