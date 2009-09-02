package net.sf.anathema.charms.character.editor;

import java.util.Set;

import net.disy.commons.core.model.AbstractChangeableModel;
import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
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

  private void clear() {
    comboCharmIds.clear();
    name = null;
    description = null;
    fireChangeEvent();
  }
  
  public Combo createComboAndClear() {
    Combo combo = createCombo();
    clear();
    return combo;
  }

  public Combo createCombo() {
    Combo combo = new Combo();
    combo.charms.addAll(comboCharmIds);
    combo.description = isSet(description) ? description : null;
    combo.name = isSet(name) ? name : null;
    return combo;
  }

  public ICharmId[] getComboedCharms() {
    return comboCharmIds.toArray(new ICharmId[comboCharmIds.size()]);
  }
  
  public String getDescription() {
    return description;
  }
  
  public String getName() {
    return name;
  }

  public boolean isValid() {
    return comboCharmIds.size() > 1;
  }

  public void removeCharmFromCombo(ICharmId element) {
    comboCharmIds.remove(element);
    fireChangeEvent();
  }

  public void setDescription(String description) {
    if (ObjectUtilities.equals(description, this.description)) {
      return;
    }
    this.description = description;
    fireChangeEvent();
  }
  
  public void setName(String name) {
    if (ObjectUtilities.equals(name, this.name)) {
      return;
    }
    this.name = name;
    fireChangeEvent();
  }
  
  public boolean isSet(String text) {
    return !StringUtilities.isNullOrEmpty(text);
  }
}