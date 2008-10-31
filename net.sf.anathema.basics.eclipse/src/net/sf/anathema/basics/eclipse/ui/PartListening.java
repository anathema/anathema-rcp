package net.sf.anathema.basics.eclipse.ui;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.ui.IPartListener;

public class PartListening implements IDisposable{

  private final IPartListener listener;
  private final IPartContainer partContainer;

  public PartListening(IPartListener listener, IPartContainer partContainer) {
    this.listener = listener;
    this.partContainer = partContainer;
    partContainer.addPartListener(listener);
  }
  
  @Override
  public void dispose() {
    partContainer.removePartListener(listener);
  }
}