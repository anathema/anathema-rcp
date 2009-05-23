package net.sf.anathema.charms.character.editor;

import net.sf.anathema.charms.character.modify.ICharmLearner;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.view.ICharmSelectionListener;

public final class LearningCharmSelectionListener implements ICharmSelectionListener {
  private final ICharmLearner charmCharacter;

  public LearningCharmSelectionListener(ICharmLearner charmCharacter) {
    this.charmCharacter = charmCharacter;
  }

  @Override
  public void charmSelected(ICharmId charmId) {
    charmCharacter.learn(charmId);
  }
}