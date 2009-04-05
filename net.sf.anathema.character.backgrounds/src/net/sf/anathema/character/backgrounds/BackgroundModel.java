package net.sf.anathema.character.backgrounds;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;

public class BackgroundModel extends AbstractModel implements IBackgroundModel {
  private final ChangeControl changeControl = new ChangeControl();
  private final List<String> backgrounds = new ArrayList<String>();

  @Override
  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }

  @Override
  public List<String> getBackgrounds() {
    return backgrounds;
  }

  @Override
  public void addBackground(String background) {
    backgrounds.add(background);
    changeControl.fireChangedEvent();
    setDirty(true);
  }
}