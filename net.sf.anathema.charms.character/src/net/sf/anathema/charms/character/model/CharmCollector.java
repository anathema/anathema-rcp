package net.sf.anathema.charms.character.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmCollector {

  public Set<ICharmId> collectLearnedCharms(ICharacter character) {
    ICharmModel model = (ICharmModel) character.getModel(ICharmModel.MODEL_ID);
    IExperience experience = (IExperience) character.getModel(IExperience.MODEL_ID);
    Set<ICharmId> learnedCharms = new HashSet<ICharmId>();
    addCharms(learnedCharms, model.getCreationLearnedCharms());
    if (experience.isExperienced()) {
      addCharms(learnedCharms, model.getExperienceLearnedCharms());
    }
    return learnedCharms;
  }

  private void addCharms(Collection<ICharmId> learnedCharms, Iterable<ICharmId> charms) {
    for (ICharmId id : charms) {
      learnedCharms.add(id);
    }
  }
}