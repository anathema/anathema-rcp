package net.sf.anathema.lib.ui;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;

public class ChangeableModelDisposable implements IDisposable {

  private final IChangeableModel model;
  private final IChangeListener listener;

  public ChangeableModelDisposable(IChangeableModel model, IChangeListener listener) {
    this.model = model;
    this.listener = listener;
  }
  
  @Override
  public void dispose() {
    model.removeChangeListener(listener);
  }
}