package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class ValidationDto {

  public String templateId;
  public IModelContainer container;
  public String modelId;
  public IBasicTrait trait;
  public String charactertype;

  public ITraitCollectionModel getModel() {
    return (ITraitCollectionModel) container.getModel(modelId);
  }
}