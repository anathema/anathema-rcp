package net.sf.anathema.character.trait.interactive;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.trait.display.IDisplayFavorization;
import net.sf.anathema.lib.ui.IDisposable;

public interface IInteractiveFavorization extends IDisposable, IDisplayFavorization {

  public void addFavorableChangeListener(IChangeListener listener);

  public void addFavoredChangeListener(IChangeListener listener);

  public void toggleFavored();
}