package net.sf.anathema.charms.character.combo;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.model.AbstractModel;

public class ComboModel extends AbstractModel {

  private final List<Combo> comboList = new ArrayList<Combo>();

  public void addCombo(Combo combo) {
    comboList.add(combo);
    fireChangedEvent();
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void loadFromFromSaveState(Object saveState) {
    List<Combo> savedCombos = (List<Combo>) saveState;
    synchronize(savedCombos, comboList);
  }

  @Override
  public List<Combo> getSaveState() {
    List<Combo> memento = new ArrayList<Combo>();
    for (Combo combo : comboList) {
      memento.add(Combo.CreateFrom(combo));
    }
    return memento;
  }
}