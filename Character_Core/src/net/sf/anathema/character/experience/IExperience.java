package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.lib.control.change.IChangeable;

public interface IExperience extends IChangeable, IModel {

  public static final String MODEL_ID = "net.sf.anathema.character.experience.model"; //$NON-NLS-1$

  public boolean isExperienced();

  public void setExperienced(boolean experienced);
}