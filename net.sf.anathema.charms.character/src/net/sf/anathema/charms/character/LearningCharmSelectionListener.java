package net.sf.anathema.charms.character;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.model.IVirtualCharmEvaluation;
import net.sf.anathema.charms.character.preference.ExperienceCharmTreatment;
import net.sf.anathema.charms.character.preference.ICharmPreferences;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.view.ICharmSelectionListener;

public final class LearningCharmSelectionListener implements ICharmSelectionListener {
  private final ICharmModel charmModel;
  private final IExperience experience;
  private final ICharmPreferences preferences;
  private final IVirtualCharmEvaluation virtualEvaluation;

  public LearningCharmSelectionListener(
      ICharmModel charmModel,
      IExperience experience,
      ICharmPreferences preferences,
      IVirtualCharmEvaluation virtualEvaluation) {
    this.charmModel = charmModel;
    this.experience = experience;
    this.preferences = preferences;
    this.virtualEvaluation = virtualEvaluation;
  }

  @Override
  public void charmSelected(ICharmId charmId) {
    if (virtualEvaluation.isVirtual(charmId)) {
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