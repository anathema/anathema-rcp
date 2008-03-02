package net.sf.anathema.character.caste.persistence;

public class NullTraitModelIdProvider implements ITraitModelIdProvider {

  @Override
  public String getTraitModelId(String characterTypeId) {
    return null;
  }
}