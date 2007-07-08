package net.sf.anathema.basics.eclipse.ui;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;

public class PartListenerDisposable implements IDisposable{

  private final IPartListener listener;
  private final IPartService partService;

  public PartListenerDisposable(IPartListener listener, IPartService partService) {
    this.listener = listener;
    this.partService = partService;
    partService.addPartListener(listener);
  }
  
  @Override
  public void dispose() {
    partService.removePartListener(listener);
  }
}