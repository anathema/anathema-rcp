package net.sf.anathema.character.caste.model;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;

public class CasteModel extends AbstractModel implements ICasteModel {

  private ChangeControl changeControl = new ChangeControl();
  private String caste;
  private final CasteTemplate casteTemplate;

  public CasteModel(CasteTemplate casteTemplate) {
    this.casteTemplate = casteTemplate;
  }

  public void setCaste(String caste) {
    if (ObjectUtilities.equals(this.caste, caste)) {
      return;
    }
    this.caste = caste;
    setDirty(true);
    changeControl.fireChangedEvent();
  }

  public String getCaste() {
    return caste;
  }

  public String[] getOptions() {
    return casteTemplate.getCastes();
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