package net.sf.anathema.character.core.character;

import net.sf.anathema.character.core.model.IModelChangeListener;

public interface IModelCollection {

  public abstract IModel getModel(IModelIdentifier identifier);

  public boolean contains(IModelIdentifier identifier);

  public void addModelChangeListener(IModelChangeListener listener);

  public void removeModelChangeListener(IModelChangeListener listener);
}