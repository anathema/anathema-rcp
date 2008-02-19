package net.sf.anathema.character.caste.model;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;

public class CasteModel extends AbstractModel implements ICasteModel {

  private ChangeControl changeControl = new ChangeControl();
  private ICaste caste;
  private final CasteTemplate casteTemplate;

  public CasteModel(CasteTemplate casteTemplate) {
    this.casteTemplate = casteTemplate;
  }

  public void setCasteById(String id) {
    setCaste(id == null ? null : ArrayUtilities.getFirst(casteTemplate.getCastes(), new CasteIdPredicate(id)));
  }

  public void setCasteByPrintName(final String printName) {
    setCaste(printName == null ? null : ArrayUtilities.getFirst(casteTemplate.getCastes(), new CastePrintNamePredicate(
        printName)));
  }

  private void setCaste(ICaste caste) {
    if (ObjectUtilities.equals(this.caste, caste)) {
      return;
    }
    this.caste = caste;
    setDirty(true);
    changeControl.fireChangedEvent();
  }

  public ICaste getCaste() {
    return caste;
  }

  public String[] getPrintNameOptions() {
    return ArrayUtilities.transform(casteTemplate.getCastes(), String.class, new CasteToPrintNameTransformer());
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