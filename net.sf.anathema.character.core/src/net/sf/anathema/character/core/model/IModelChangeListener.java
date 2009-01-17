package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.IModelIdentifier;

public interface IModelChangeListener {

  public void modelChanged(IModelIdentifier identifier);
}