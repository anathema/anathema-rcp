package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.trait.model.ITraitModelIdProvider;

public class NullTraitModelIdProvider implements ITraitModelIdProvider {

  @Override
  public String getTraitModelId(String characterTypeId) {
    return null;
  }
}