package net.sf.anathema.character.experience;

import net.disy.commons.core.model.IChangeableModel;
import net.sf.anathema.character.core.character.IModel;

public interface IExperience extends IChangeableModel, IModel {

  public static final String MODEL_ID = "net.sf.anathema.character.experience.model"; //$NON-NLS-1$

  public boolean isExperienced();

  public void setExperienced(boolean experienced);
}