package net.sf.anathema.character.description;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.ui.IDisposable;

public final class TextListenerDisposable implements IDisposable {
  private final IObjectValueChangedListener<String> nameListener;
  private final ITextualDescription name;

  TextListenerDisposable(IObjectValueChangedListener<String> nameListener, ITextualDescription name) {
    this.nameListener = nameListener;
    this.name = name;
  }

  @Override
  public void dispose() {
    name.removeTextChangeListener(nameListener);
  }
}