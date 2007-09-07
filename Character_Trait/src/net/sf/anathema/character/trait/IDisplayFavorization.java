package net.sf.anathema.character.trait;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.lib.ui.IDisposable;

public interface IDisplayFavorization extends IDisposable{

  public void addFavoredChangeListener(IChangeListener listener);

  public boolean isFavorable();

  public boolean isFavored();

  public void toggleFavored();

  public void addFavorableChangeListener(IChangeListener listener);
}