package net.sf.anathema.charms.character;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;

public class CharmModel extends AbstractModel implements ICharmModel {

  public static final String ID = "net.sf.anathema.charms.character.modelId"; //$NON-NLS-1$
  private final List<String> creationLearnedCharms = new ArrayList<String>();
  private final List<String> experienceLearnedCharms = new ArrayList<String>();
  private final ChangeControl changeControl = new ChangeControl();

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
    return isLearned(charmId)&& !isCreationLearned(charmId);
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
    toggleLearned(charmId, creationLearnedCharms);
  }

  @Override
  public void toggleExperiencedLearned(String charmId) {
    toggleLearned(charmId, experienceLearnedCharms);
  }

  private void toggleLearned(String charmId, List<String> charmList) {
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