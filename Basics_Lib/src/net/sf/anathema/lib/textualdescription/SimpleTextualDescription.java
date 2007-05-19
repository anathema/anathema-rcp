package net.sf.anathema.lib.textualdescription;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.ObjectValueControl;

public class SimpleTextualDescription extends AbstractTextualDescription implements ITextualDescription {

  private final ObjectValueControl<String> textControl = new ObjectValueControl<String>();
  private String text;

  public SimpleTextualDescription() {
    // Nothing to do
  }

  public SimpleTextualDescription(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    if (ObjectUtilities.equals(this.text, text)) {
      return;
    }
    this.text = text;
    setDirty(true);
    fireTextChangedEvent();
  }

  private void fireTextChangedEvent() {
    textControl.fireValueChangedEvent(text);
  }

  public void addTextChangedListener(IObjectValueChangedListener<String> listener) {
    textControl.addObjectValueChangeListener(listener);
  }

  public void removeTextChangeListener(IObjectValueChangedListener<String> listener) {
    textControl.removeObjectValueChangeListener(listener);
  }

  public boolean isEmpty() {
    return StringUtilities.isNullOrEmpty(getText());
  }
}