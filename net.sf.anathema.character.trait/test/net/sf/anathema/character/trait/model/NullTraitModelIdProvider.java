package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.trait.model.IMainTraitModelProvider;

public class NullTraitModelIdProvider implements IMainTraitModelProvider {

  @Override
  public String getFor(String characterTypeId) {
    return null;
  }
}