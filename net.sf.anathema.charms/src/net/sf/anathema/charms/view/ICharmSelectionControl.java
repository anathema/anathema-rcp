package net.sf.anathema.charms.view;

public interface ICharmSelectionControl {

  public void addSelectionListener(ICharmSelectionListener listener);

  public void removeSelectionListener(ICharmSelectionListener selectionListener);
}