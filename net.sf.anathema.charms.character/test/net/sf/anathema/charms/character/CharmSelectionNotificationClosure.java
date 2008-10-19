/**
 * 
 */
package net.sf.anathema.charms.character;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.charms.view.ICharmSelectionListener;

public final class CharmSelectionNotificationClosure implements IClosure<ICharmSelectionListener> {
  private final String charmId;

  CharmSelectionNotificationClosure(String charmId) {
    this.charmId = charmId;
  }

  @Override
  public void execute(ICharmSelectionListener listener) throws RuntimeException {
    listener.charmSelected(charmId);
  }
}