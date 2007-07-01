package net.sf.anathema.lib.control.change;

public interface IChangeable {

  public abstract void addChangeListener(IChangeListener listener);

  public abstract void removeChangeListener(IChangeListener listener);

}