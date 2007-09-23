package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;

public interface IModelPersister <M extends IModel> extends ISingleFileItemPersister<M> {

  public abstract M createNew();
}