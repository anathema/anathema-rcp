package net.sf.anathema.character.caste.model;

import net.sf.anathema.character.core.character.IModel;

public interface ICasteModel extends IModel {

  public static final String ID = "net.sf.anathema.character.caste.model"; //$NON-NLS-1$

  public String getCaste();

  public String[] getOptions();

  public void setCaste(String caste);
}