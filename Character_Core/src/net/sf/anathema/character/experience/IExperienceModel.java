package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.IModel;

public interface IExperienceModel extends IModel {

  public static final String MODEL_ID = "net.sf.anathema.character.basics.model"; //$NON-NLS-1$

  public boolean isExperienced();
}