package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.lib.control.ChangeManagement;

public abstract class AbstractModel extends ChangeManagement implements IModel{

  @Override
  public void updateToDependencies() {
    // nothing to do
  }
}