package net.sf.anathema.charms.character.preference;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;

public enum ExperienceCharmTreatment {

  Remember {
    @Override
    public void adjust(IBasicTrait basicTrait) {
      // nothing to do
    }
  },
  Forget {
    @Override
    public void adjust(IBasicTrait basicTrait) {
      if (!basicTrait.isExperiencedValueSet()) {
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