package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.IChangeManagement;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.util.IIdentificate;

public interface IDisplayTrait extends IIntValueModel, IChangeManagement, IDisposable {

  public int getMaximalValue();

  public boolean isFavorable();

  public IIdentificate getTraitType();

  public void toggleFavored();

  public void addFavoredChangeListener(IChangeListener favoredChangeListener);

  public boolean isFavored();
}