package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.ui.IUpdatable;

public final class ModelChangeDisposable implements IDisposable {
  private final IModelChangeListener modelChangeListener = new IModelChangeListener() {

    @Override
    public void modelChanged(IModelIdentifier identifier) {
      updatable.update();
    }

    @Override
    public void modelCreated(IModelIdentifier identifier) {
      // nothing to do
    }
  };
  private final IUpdatable updatable;
  private final IModelCollection modelProvider;

  public ModelChangeDisposable(IUpdatable updatable, IModelCollection modelProvider) {
    this.updatable = updatable;
    this.modelProvider = modelProvider;
    this.modelProvider.addModelChangeListener(modelChangeListener);
  }

  @Override
  public void dispose() {
    modelProvider.removeModelChangeListener(modelChangeListener);
  }
}