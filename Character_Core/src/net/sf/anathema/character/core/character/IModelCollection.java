package net.sf.anathema.character.core.character;

public interface IModelCollection {

  public abstract IModel getModel(IModelIdentifier identifier);

  public boolean contains(IModelIdentifier identifier);
}