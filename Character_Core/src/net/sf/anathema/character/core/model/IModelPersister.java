package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.model.template.IModelTemplate;

public interface IModelPersister <T extends IModelTemplate, M extends IModel> extends ISingleFileItemPersister<M> {

  public abstract M createNew(T template);
}