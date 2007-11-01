package net.sf.anathema.character.trait.interactive;

import net.sf.anathema.lib.control.IChangeManagement;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.util.IIdentificate;

public interface IInteractiveTrait extends IIntValueModel, IChangeManagement, IDisposable {

  public int getMaximalValue();

  public IIdentificate getTraitType();

  public IInteractiveFavorization getFavorization();
}