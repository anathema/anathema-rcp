package net.sf.anathema.character.trait.status;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;

public interface ITraitStatusModel extends IChangeableModel {

  public void addPriorityChangeListener(IChangeListener listener);

  public void setStatus(ITraitStatus status);

  public ITraitStatus getStatus();

  public int getListenerCount();

  public void toggleStatus();
}