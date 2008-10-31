package net.sf.anathema.view.valuelist;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.ui.IUpdatable;

public interface IViewUpdateHandler extends IDisposable {

  public String getTitle();

  public void init(IPartContainer partContainer, IUpdatable updatable);
}