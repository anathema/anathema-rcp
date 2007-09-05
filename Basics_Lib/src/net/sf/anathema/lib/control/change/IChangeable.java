package net.sf.anathema.lib.control.change;

import net.disy.commons.core.model.listener.IChangeListener;

public interface IChangeable {

  public abstract void addChangeListener(IChangeListener listener);

  public abstract void removeChangeListener(IChangeListener listener);

}