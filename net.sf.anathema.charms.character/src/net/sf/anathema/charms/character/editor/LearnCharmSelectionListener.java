package net.sf.anathema.charms.character.editor;

import net.sf.anathema.charms.character.modify.ICharmLearner;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.view.ICharmSelectionListener;

public final class LearnCharmSelectionListener implements ICharmSelectionListener {
  private final ICharmLearner charmCharacter;

  public LearnCharmSelectionListener(ICharmLearner charmCharacter) {
    this.charmCharacter = charmCharacter;
  }

  @Override
  public void charmSelected(ICharmId charmId) {
    charmCharacter.learn(charmId);
  }
}