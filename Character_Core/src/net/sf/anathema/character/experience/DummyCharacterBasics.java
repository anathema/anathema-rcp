package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.AbstractModel;

public class DummyCharacterBasics extends AbstractModel implements ICharacterBasics {

  private final boolean experienced;

  public DummyCharacterBasics() {
    this(false);
  }
  
  public DummyCharacterBasics(boolean experienced) {
    this.experienced = experienced;
  }
  
  @Override
  public boolean isExperienced() {
    return experienced;
  }
}