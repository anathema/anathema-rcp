package net.sf.anathema.lib.textualdescription;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface ITextView {

  public void addTextChangedListener(IObjectValueChangedListener<String> listener);

  public void setEnabled(boolean enabled);

  public void setText(String text);
}