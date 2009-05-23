package net.sf.anathema.charms.character;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.ICharmCharacter;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.preference.ExperienceCharmTreatment;
import net.sf.anathema.charms.character.preference.ICharmPreferences;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.view.ICharmSelectionListener;

public final class LearningCharmSelectionListener implements ICharmSelectionListener {
  private final ICharmModel charmModel;
  private final IExperience experience;
  private final ICharmPreferences preferences;
  private final ICharmCharacter charmCharacter;

  public LearningCharmSelectionListener(
      ICharmModel charmModel,
      IExperience experience,
      ICharmPreferences preferences,
      ICharmCharacter charmCharacter) {
    this.charmModel = charmModel;
    this.experience = experience;
    this.preferences = preferences;
    this.charmCharacter = charmCharacter;
  }

  @Override
  public void charmSelected(ICharmId charmId) {
    if (charmCharacter.isVirtual(charmId)) {
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