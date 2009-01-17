package net.sf.anathema.character.core.model;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.IClosure;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.lib.control.GenericControl;

public class OverallModelChangeListener implements IChangeListener {

  private final IModelIdentifier identifier;
  private final GenericControl<IModelChangeListener> modelChangeListeners;

  public OverallModelChangeListener(
      IModelIdentifier identifier,
      GenericControl<IModelChangeListener> modelChangeListeners) {
    this.identifier = identifier;
    this.modelChangeListeners = modelChangeListeners;
  }

  @Override
  public void stateChanged() {
    modelChangeListeners.forAllDo(new IClosure<IModelChangeListener>() {

      @Override
      public void execute(IModelChangeListener listener) throws RuntimeException {
        listener.modelChanged(identifier);
      }
    });
  }
}