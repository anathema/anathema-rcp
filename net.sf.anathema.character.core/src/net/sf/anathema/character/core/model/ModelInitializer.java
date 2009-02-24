package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;

public class ModelInitializer implements IModelInitializer {

  private final IModel modelObject;
  private final IModelIdentifier modelIdentifier;

  public ModelInitializer(IModel modelObject, IModelIdentifier modelIdentifier) {
    this.modelObject = modelObject;
    this.modelIdentifier = modelIdentifier;
  }

  @Override
  public IModel getModel() {
    return modelObject;
  }

  @Override
  public void initialize() {
    getModel().setClean();
  }

  protected final IModelIdentifier getModelIdentifier() {
    return modelIdentifier;
  }
}