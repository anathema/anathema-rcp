package net.sf.anathema.character.trait.groupeditor.dynamic;

import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.ui.IDisposable;

public interface IDynamicEditor {

  public void decorate(IInteractiveTrait trait, IExtendableIntValueView view);

  public <T extends IDisposable> T addDisposable(T disposable);

  public void redraw();
}