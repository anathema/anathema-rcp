/**
 * 
 */
package net.sf.anathema.editor.styledtext;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.ui.IDisposable;

public final class TextChangeListenerDisposable implements IDisposable {
  private final ITextualDescription description;
  private final IObjectValueChangedListener<String> listener;

  public TextChangeListenerDisposable(ITextualDescription description, IObjectValueChangedListener<String> listener) {
    this.description = description;
    this.listener = listener;
  }

  @Override
  public void dispose() {
    description.removeTextChangeListener(listener);
  }
}