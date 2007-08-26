package net.sf.anathema.lib.ui;

import java.util.ArrayList;
import java.util.List;

public class AggregatedDisposable implements IDisposable {

  private final List<IDisposable> disposables = new ArrayList<IDisposable>();

  public final<T extends IDisposable> T addDisposable(T disposable) {
    disposables.add(disposable);
    return disposable;
  }

  @Override
  public void dispose() {
    for (IDisposable disposable : disposables) {
      disposable.dispose();
    }
  }
}