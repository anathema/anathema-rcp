package net.sf.anathema.charms.character;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;

public class CharmModel extends AbstractModel implements ICharmModel {

  public static final String ID = "net.sf.anathema.charms.character.modelId"; //$NON-NLS-1$
  private final List<String> learnedCharms = new ArrayList<String>();
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
    return learnedCharms.contains(charmId);
  }

  @Override
  public void toggleLearned(String charmId) {
    if (learnedCharms.contains(charmId)) {
      learnedCharms.remove(charmId);
    }
    else {
      learnedCharms.add(charmId);
    }
    setDirty(true);
    changeControl.fireChangedEvent();
  }
}