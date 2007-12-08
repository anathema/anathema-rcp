package net.sf.anathema.lib.textualdescription;

import net.sf.anathema.lib.control.IChangeManagement;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface ITextualDescription extends IChangeManagement {

  public boolean isEmpty();

  public void setText(String text);

  public void addTextChangedListener(IObjectValueChangedListener<String> listener);

  public void removeTextChangeListener(IObjectValueChangedListener<String> listener);

  public int getTextChangeListenerCount();
  
  public String getText();
}