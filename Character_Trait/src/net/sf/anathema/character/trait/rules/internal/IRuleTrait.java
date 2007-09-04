package net.sf.anathema.character.trait.rules.internal;

public interface IRuleTrait {

  public int getMaximalValue();

  public int getValue();

  public boolean isFavorable();

  public void setValue(int value);
}