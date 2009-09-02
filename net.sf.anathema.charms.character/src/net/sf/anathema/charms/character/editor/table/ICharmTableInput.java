package net.sf.anathema.charms.character.editor.table;

import net.disy.commons.core.model.IChangeableModel;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.ui.IDisposable;

public interface ICharmTableInput extends IChangeableModel, IDisposable {

  public ICharmId[] getAllCharms();
}