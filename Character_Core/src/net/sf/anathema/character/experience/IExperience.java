package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.IModel;

public interface IExperience extends IModel {

  public static final String MODEL_ID = "net.sf.anathema.character.experience.model"; //$NON-NLS-1$

  public boolean isExperienced();

  public void setExperienced(boolean experienced);
}