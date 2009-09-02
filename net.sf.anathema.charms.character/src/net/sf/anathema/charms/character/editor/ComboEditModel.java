package net.sf.anathema.charms.character.editor;

import java.util.Set;

import net.disy.commons.core.model.AbstractChangeableModel;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.collection.ListOrderedSet;

public class ComboEditModel extends AbstractChangeableModel {
  private final Set<ICharmId> comboCharmIds = new ListOrderedSet<ICharmId>();
  private String name;
  private String description;

  public void addCharmToCombo(ICharmId charm) {
    comboCharmIds.add(charm);
    fireChangeEvent();
  }

  public void removeCharmFromCombo(ICharmId element) {
    comboCharmIds.remove(element);
    fireChangeEvent();
  }
  
  public boolean isValid() {
    return comboCharmIds.size() > 1;
  }

  public ICharmId[] getComboedCharms() {
    return comboCharmIds.toArray(new ICharmId[comboCharmIds.size()]);
  }
  
  public void setName(String name) {
    if (ObjectUtilities.equals(this.name, name)) {
      return;
    }
    this.name = name;
    fireChangeEvent();
  }
  
  public void setDescription(String description) {
    if (ObjectUtilities.equals(this.description, description)) {
      return;
    }
    this.description = description;
    fireChangeEvent();
  }

  public Combo createComboAndClear() {
    Combo combo = new Combo();
    combo.charms.addAll(comboCharmIds);
    combo.description = description;
    combo.name = name;
    clear();
    return combo;
  }

  private void clear() {
    comboCharmIds.clear();
    fireChangeEvent();
  }
}