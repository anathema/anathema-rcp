package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.item.persistence.ISingleFileItemSaver;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;

public interface IModelPersister<T extends IModelTemplate, M extends IModel> extends ISingleFileItemSaver<M> {

  public M load(Document document, T template) throws PersistenceException;

  public M createNew(T template);
}