package net.sf.anathema.charms.character.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;

public class CharmModel extends AbstractModel implements ICharmModel {

  private final List<String> creationLearnedCharms = new ArrayList<String>();
  private final List<String> experienceLearnedCharms = new ArrayList<String>();
  private final ChangeControl changeControl = new ChangeControl();

  public static ICharmModel getFrom(IModelCollection modelCollection, ICharacterId characterId) {
    ModelIdentifier identifier = new ModelIdentifier(characterId, ICharmModel.MODEL_ID);
    return (ICharmModel) modelCollection.getModel(identifier);
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }

  @Override
  public boolean isLearned(String charmId) {
    return creationLearnedCharms.contains(charmId) || experienceLearnedCharms.contains(charmId);
  }

  @Override
  public boolean isCreationLearned(String charmId) {
    return creationLearnedCharms.contains(charmId);
  }

  @Override
  public boolean isExperienceLearned(String charmId) {
    return isLearned(charmId) && !isCreationLearned(charmId);
  }

  @Override
  public Iterable<String> getCreationLearnedCharms() {
    return creationLearnedCharms;
  }

  @Override
  public Iterable<String> getExperienceLearnedCharms() {
    return experienceLearnedCharms;
  }

  @Override
  public void toggleCreationLearned(String charmId) {
    toggleLearnedInList(charmId, creationLearnedCharms);
  }

  @Override
  public void toggleExperiencedLearned(String charmId) {
    toggleLearnedInList(charmId, experienceLearnedCharms);
  }

  private void toggleLearnedInList(String charmId, List<String> charmList) {
    if (charmList.contains(charmId)) {
      charmList.remove(charmId);
    }
    else {
      charmList.add(charmId);
    }
    setDirty(true);
    changeControl.fireChangedEvent();
  }
}