package net.sf.anathema.character.trait.experience;

import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.experience.IExperienceModel;

public class DummyExperienceModel extends AbstractModel implements IExperienceModel {

  private final boolean experienced;

  public DummyExperienceModel() {
    this(false);
  }
  
  public DummyExperienceModel(boolean experienced) {
    this.experienced = experienced;
  }
  
  @Override
  public boolean isExperienced() {
    return experienced;
  }
}