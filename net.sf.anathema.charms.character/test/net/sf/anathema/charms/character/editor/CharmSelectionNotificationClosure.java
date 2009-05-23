/**
 * 
 */
package net.sf.anathema.charms.character.editor;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.view.ICharmSelectionListener;

public final class CharmSelectionNotificationClosure implements IClosure<ICharmSelectionListener> {
  private final ICharmId charmId;

  public CharmSelectionNotificationClosure(ICharmId charmId) {
    this.charmId = charmId;
  }

  @Override
  public void execute(ICharmSelectionListener listener) throws RuntimeException {
    listener.charmSelected(charmId);
  }
}