package net.sf.anathema.character.backgrounds;

import java.util.List;

import net.sf.anathema.character.core.character.IModel;

public interface IBackgroundModel extends IModel {

  public String MODEL_ID = "net.sf.anathema.character.backgrounds.model"; //$NON-NLS-1$

  List<String> getBackgrounds();

  void addBackground(String string);
}