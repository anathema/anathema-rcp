package net.sf.anathema.charms.character.evaluation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmCharacter implements ICharmCharacter {

  private final IModelContainer modelContainer;

  public CharmCharacter(IModelContainer modelContainer) {
    this.modelContainer = modelContainer;
  }

  public Set<ICharmId> getAllLearnedCharms() {
    ICharmModel model = (ICharmModel) modelContainer.getModel(ICharmModel.MODEL_ID);
    IExperience experience = (IExperience) modelContainer.getModel(IExperience.MODEL_ID);
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