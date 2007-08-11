package net.sf.anathema.character.freebies.view;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.view.valuelist.IUpdatable;
import net.sf.anathema.view.valuelist.IViewUpdateHandler;
import net.sf.anathema.view.valuelist.UpdateRunnable;

public class FreebiesViewUpdateHandler implements IViewUpdateHandler {

  private AggregatedDisposable disposables = new AggregatedDisposable();

  @Override
  public String getTitle() {
    return null;
  }

  @Override
  public void init(IPartContainer partContainer, IUpdatable updateable) {
    UpdateRunnable runnable = new UpdateRunnable(updateable);
    TopPartListener topPartListener = new TopPartListener(runnable);
    disposables.addDisposable(new PartListening(topPartListener, partContainer));
  }
  
  @Override
  public void dispose() {
    disposables.dispose();
  }
}