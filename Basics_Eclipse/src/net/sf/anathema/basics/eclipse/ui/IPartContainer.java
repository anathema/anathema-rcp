package net.sf.anathema.basics.eclipse.ui;

import org.eclipse.ui.IPartListener;

public interface IPartContainer {

  public void addPartListener(IPartListener listener);

  public void removePartListener(IPartListener listener);

  public IEditorInputProvider getEditorInputProvider();
}