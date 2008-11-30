package net.sf.anathema.charms.character;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.view.ICharmSelectionListener;

public final class LearningCharmSelectionListener implements ICharmSelectionListener {
  private final ICharmModel charmModel;
  private final IExperience experience;

  public LearningCharmSelectionListener(ICharmModel charmModel, IExperience experience) {
    this.charmModel = charmModel;
    this.experience = experience;
  }

  @Override
  public void charmSelected(String charmId) {
    if (!experience.isExperienced() && !charmModel.isExperienceLearned(charmId)) {
      charmModel.toggleCreationLearned(charmId);
    }
    if (experience.isExperienced() && !charmModel.isCreationLearned(charmId)) {
      charmModel.toggleExperiencedLearned(charmId);
    }
  }
}