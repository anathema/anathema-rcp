package net.sf.anathema.lib.ui;

import java.util.ArrayList;
import java.util.List;

public class AggregatedDisposable implements IDisposable {

  private final List<IDisposable> disposables = new ArrayList<IDisposable>();

  public void addDisposable(IDisposable disposable) {
    disposables.add(disposable);
  }

  @Override
  public void dispose() {
    for (IDisposable disposable : disposables) {
      disposable.dispose();
    }
  }
}