package net.sf.anathema.charms.character.modify;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.preference.ExperienceCharmTreatment;
import net.sf.anathema.charms.character.preference.ICharmPreferences;
import net.sf.anathema.charms.character.special.IVirtualCharms;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmLearner implements ICharmLearner {

  private final IVirtualCharms virtualCharms;
  private final IExperience experience;
  private final ICharmModel charmModel;
  private final ICharacter character;
  private final ICharmPreferences preferences;

  public CharmLearner(IVirtualCharms virtualCharms, ICharmPreferences preferences, ICharacter character) {
    this.preferences = preferences;
    this.character = character;
    this.experience = (IExperience) character.getModel(IExperience.MODEL_ID);
    this.charmModel = (ICharmModel) character.getModel(ICharmModel.MODEL_ID);
    this.virtualCharms = virtualCharms;
  }

  @Override
  public boolean knows(ICharmId charmId) {
    if (virtualCharms.isVirtual(charmId)) {
      return virtualCharms.isFullfilled(charmId, character);
    }
    return experience.isExperienced() ? charmModel.isLearned(charmId) : charmModel.isCreationLearned(charmId);
  }

  @Override
  public void learn(ICharmId charmId) {
    if (virtualCharms.isVirtual(charmId)) {
      return;
    }
    ExperienceCharmTreatment treatment = preferences.getExperienceTreatment();
    if (!experience.isExperienced()) {
      if (treatment == ExperienceCharmTreatment.Forget && charmModel.isExperienceLearned(charmId)) {
        charmModel.toggleExperiencedLearned(charmId);
      }
      charmModel.toggleCreationLearned(charmId);
    }
    if (experience.isExperienced() && !charmModel.isCreationLearned(charmId)) {
      charmModel.toggleExperiencedLearned(charmId);
    }
  }
}