package net.sf.anathema.basics.eclipse.ui;

import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.ui.part.ViewPart;

public abstract class DisposableViewPart extends ViewPart {

  private final AggregatedDisposable aggregatedDisposable = new AggregatedDisposable();

  protected void addDisposable(IDisposable disposable) {
    aggregatedDisposable.addDisposable(disposable);
  }

  @Override
  public void dispose() {
    aggregatedDisposable.dispose();
    super.dispose();
  }
}