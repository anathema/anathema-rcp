package net.sf.anathema.character.trait.status;

import net.disy.commons.core.model.IChangeableModel;

public interface ITraitStatusModel extends IChangeableModel {

  public void setStatus(ITraitStatus status);

  public ITraitStatus getStatus();

  public int getListenerCount();

  public void toggleStatus();
}