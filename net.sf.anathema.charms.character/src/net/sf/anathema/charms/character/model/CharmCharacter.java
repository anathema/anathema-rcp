package net.sf.anathema.charms.character.model;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmCharacter implements ICharmCharacter {

  private final IVirtualCharms virtualCharms;
  private final IExperience experience;
  private final ICharmModel charmModel;
  private final ICharacter character;

  public CharmCharacter(IVirtualCharms virtualCharms, ICharacter character) {
    this.character = character;
    this.experience = (IExperience) character.getModel(IExperience.MODEL_ID);
    this.charmModel = (ICharmModel) character.getModel(ICharmModel.MODEL_ID);
    this.virtualCharms = virtualCharms;
  }

  @Override
  public boolean isLearned(ICharmId charmId) {
    if (virtualCharms.isVirtual(charmId)) {
      return virtualCharms.isLearnedVirtualCharm(charmId, character);
    }
    return experience.isExperienced() ? charmModel.isLearned(charmId) : charmModel.isCreationLearned(charmId);
  }

  @Override
  public boolean isVirtual(ICharmId charmId) {
    return virtualCharms.isVirtual(charmId);
  }
}