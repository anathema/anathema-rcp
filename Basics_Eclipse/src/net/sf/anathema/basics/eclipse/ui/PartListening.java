package net.sf.anathema.basics.eclipse.ui;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;

public class PartListening implements IDisposable{

  private final IPartListener listener;
  private final IPartService partService;

  public PartListening(IPartListener listener, IPartService partService) {
    this.listener = listener;
    this.partService = partService;
    partService.addPartListener(listener);
  }
  
  @Override
  public void dispose() {
    partService.removePartListener(listener);
  }
}