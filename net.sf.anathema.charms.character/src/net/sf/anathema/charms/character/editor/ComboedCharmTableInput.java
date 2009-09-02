/**
 * 
 */
package net.sf.anathema.charms.character.editor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.charms.character.editor.table.ICharmTableInput;
import net.sf.anathema.charms.tree.ICharmId;

public class ComboedCharmTableInput implements ICharmTableInput {
  
  private final ComboEditModel comboEditModel;

  public ComboedCharmTableInput(ComboEditModel comboEditModel) {
    this.comboEditModel = comboEditModel;
  }

  @Override
  public ICharmId[] getAllCharms() {
    return comboEditModel.getComboedCharms();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    comboEditModel.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    comboEditModel.removeChangeListener(listener);
  }

  @Override
  public void dispose() {
    // nothing to do
  }
}