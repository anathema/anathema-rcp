package net.sf.anathema.charms.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmModel extends AbstractModel implements ICharmModel {

  private final List<ICharmId> creationLearnedCharms = new ArrayList<ICharmId>();
  private final List<ICharmId> experienceLearnedCharms = new ArrayList<ICharmId>();

  public static ICharmModel getFrom(IModelCollection modelCollection, ICharacterId characterId) {
    ModelIdentifier identifier = new ModelIdentifier(characterId, ICharmModel.MODEL_ID);
    return (ICharmModel) modelCollection.getModel(identifier);
  }

  @Override
  public boolean isLearned(ICharmId charmId) {
    return creationLearnedCharms.contains(charmId) || experienceLearnedCharms.contains(charmId);
  }

  @Override
  public boolean isCreationLearned(ICharmId charmId) {
    return creationLearnedCharms.contains(charmId);
  }

  @Override
  public boolean isExperienceLearned(ICharmId charmId) {
    return isLearned(charmId) && !isCreationLearned(charmId);
  }

  @Override
  public Iterable<ICharmId> getCreationLearnedCharms() {
    return creationLearnedCharms;
  }

  @Override
  public Iterable<ICharmId> getExperienceLearnedCharms() {
    return experienceLearnedCharms;
  }

  @Override
  public void toggleCreationLearned(ICharmId charmId) {
    toggleLearnedInList(charmId, creationLearnedCharms);
  }

  @Override
  public void toggleExperiencedLearned(ICharmId charmId) {
    toggleLearnedInList(charmId, experienceLearnedCharms);
  }

  private void toggleLearnedInList(ICharmId charmId, List<ICharmId> charmList) {
    if (charmList.contains(charmId)) {
      charmList.remove(charmId);
    }
    else {
      charmList.add(charmId);
    }
    setDirty(true);
    fireChangedEvent();
  }

  @Override
  public CharmModelMemento getSaveState() {
    CharmModelMemento memento = new CharmModelMemento();
    memento.creationLearnedCharms.addAll(creationLearnedCharms);
    memento.experienceLearnedCharms.addAll(experienceLearnedCharms);
    return memento;
  }

  @Override
  protected void loadFromFromSaveState(Object saveState) {
    CharmModelMemento memento = (CharmModelMemento) saveState;
    creationLearnedCharms.clear();
    creationLearnedCharms.addAll(memento.creationLearnedCharms);
    experienceLearnedCharms.clear();
    experienceLearnedCharms.addAll(memento.experienceLearnedCharms);
  }
}