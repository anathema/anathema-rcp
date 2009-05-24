package net.sf.anathema.character.caste.model;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.model.AbstractModel;

public class CasteModel extends AbstractModel implements ICasteModel {

  private ICaste caste;
  private final CasteTemplate casteTemplate;

  public CasteModel(CasteTemplate casteTemplate) {
    this.casteTemplate = casteTemplate;
  }

  public void setCasteById(String id) {
    setCaste(id == null ? null : ArrayUtilities.getFirst(casteTemplate.getCastes(), new CasteIdPredicate(id)));
  }

  public void setCaste(ICaste caste) {
    if (ObjectUtilities.equals(this.caste, caste)) {
      return;
    }
    this.caste = caste;
    setDirty(true);
    fireChangedEvent();
  }

  public ICaste getCaste() {
    return caste;
  }

  public ICaste[] getOptions() {
    return casteTemplate.getCastes();
  }

  @Override
  public String getTraitModelId() {
    return casteTemplate.getTraitModelId();
  }

  @Override
  public CasteMemento getSaveState() {
    CasteMemento memento = new CasteMemento();
    memento.caste = caste;
    return memento;
  }

  @Override
  protected void loadFromFromSaveState(Object memento) {
    CasteMemento casteMemento = (CasteMemento) memento;
    caste = casteMemento.caste;
  }
}