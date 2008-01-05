package net.sf.anathema.character.caste.model;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;

public class CasteModel extends AbstractModel implements ICasteModel {

  private ChangeControl changeControl = new ChangeControl();
  private String caste;
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      setDirty(true);
    }
  };
  
  public CasteModel() {
    changeControl.addChangeListener(changeListener);
  }

  public void setCaste(String caste) {
    if (ObjectUtilities.equals(this.caste, caste)) {
      return;
    }
    this.caste = caste;
    changeControl.fireChangedEvent();
  }

  public String getCaste() {
    return caste;
  }

  public String[] getOptions() {
    return new String[] { "Dawn", "Zenith", "Twilight", "Night", "Eclipse" };
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }
}