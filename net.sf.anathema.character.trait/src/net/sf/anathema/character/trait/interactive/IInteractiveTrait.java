package net.sf.anathema.character.trait.interactive;

import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.lib.control.IChangeManagement;
import net.sf.anathema.lib.ui.IDisposable;

public interface IInteractiveTrait extends IIntValueModel, IDisplayTrait, IChangeManagement, IDisposable {

  public IInteractiveFavorization getFavorization();
}