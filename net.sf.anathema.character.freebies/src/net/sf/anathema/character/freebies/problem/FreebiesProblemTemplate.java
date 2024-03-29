package net.sf.anathema.character.freebies.problem;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.item.persistence.ISingleFileItemSaver;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;

public class FreebiesProblemTemplate<M extends IModel> {

  public IPredicate<IModelIdentifier> hasProblem;
  public ISingleFileItemSaver<M> persister;
  public String markerType;
  public String description;
  public String errorMessage;
  public String editorOpener;
  public String modelName;
  public String modelId;
}