package net.sf.anathema.charms.view;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;

public final class SelectionListenerDisposable implements IDisposable {
  private final ISelectionChangedListener listener;
  private final ISelectionProvider provider;

  public SelectionListenerDisposable(ISelectionChangedListener listener, ISelectionProvider provider) {
    this.listener = listener;
    this.provider = provider;
  }

  @Override
  public void dispose() {
    provider.removeSelectionChangedListener(listener);
  }
}