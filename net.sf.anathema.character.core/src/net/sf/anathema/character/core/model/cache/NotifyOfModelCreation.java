package net.sf.anathema.character.core.model.cache;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelChangeListener;

public final class NotifyOfModelCreation implements IClosure<IModelChangeListener> {
  private final IModelIdentifier identifier;

  public NotifyOfModelCreation(IModelIdentifier identifier) {
    this.identifier = identifier;
  }

  @Override
  public void execute(IModelChangeListener listener) throws RuntimeException {
    listener.modelCreated(identifier);
  }
}