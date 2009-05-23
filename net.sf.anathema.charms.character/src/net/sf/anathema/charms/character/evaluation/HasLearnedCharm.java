package net.sf.anathema.charms.character.evaluation;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.IProperty;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.ICharmId;

public class HasLearnedCharm extends UnconfiguredExecutableExtension implements IProperty {

  @Override
  public boolean has(ICharacter character, String fullCharmId) {
    IExperience experience = (IExperience) character.getModel(IExperience.MODEL_ID);
    ICharmModel charms = (ICharmModel) character.getModel(ICharmModel.MODEL_ID);
    boolean creationLearned = contains(charms.getCreationLearnedCharms(), fullCharmId);
    if (creationLearned) {
      return true;
    }
    if (!experience.isExperienced()) {
      return false;
    }
    return contains(charms.getExperienceLearnedCharms(), fullCharmId);
  }

  private boolean contains(Iterable<ICharmId> charms, String fullCharmId) {
    for (ICharmId charmId : charms) {
      if (fullCharmId.equals(charmId.getId())) {
        return true;
      }
    }
    return false;
  }
}