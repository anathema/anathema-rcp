package net.sf.anathema.view.valuelist;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.ui.IUpdatable;

public class EditorDependentUpdateHandler implements IDisposable {

  private final AggregatedDisposable disposables = new AggregatedDisposable();
  private final UpdateRunnable runnable = new UpdateRunnable();

  public void init(IPartContainer partContainer) {
    runnable.run();
    TopPartListener topPartListener = new TopPartListener(runnable);
    disposables.addDisposable(new PartListening(topPartListener, partContainer));
  }

  public void addUpdatable(IUpdatable updatable) {
    if (updatable instanceof IDisposable) {
      disposables.addDisposable((IDisposable) updatable);
    }
    runnable.addUpdatables(updatable);
  }

  @Override
  public void dispose() {
    disposables.dispose();
  }

  public void addDisposable(IDisposable disposable) {
    disposables.addDisposable(disposable);
  }
}