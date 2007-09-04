package net.sf.anathema.character.trait.rules;

public interface ITraitTemplate {
  
  public boolean isFavorable();

  public int getMaximalValue();
  
  public int getMinimalValue();
}