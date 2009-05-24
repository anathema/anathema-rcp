package net.sf.anathema.character.core.fake;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelChangeListener;

public final class StaticDummyModelCollection implements IModelCollection {

  private final IModel model;

  public StaticDummyModelCollection(IModel model) {
    this.model = model;
  }

  @Override
  public IModel getModel(IModelIdentifier identifier) {
    return model;
  }

  @Override
  public boolean contains(IModelIdentifier identifier) {
    return true;
  }

  @Override
  public void addModelChangeListener(IModelChangeListener listener) {
    // nothing to do
  }

  @Override
  public void removeModelChangeListener(IModelChangeListener listener) {
    // nothing to do
  }
}