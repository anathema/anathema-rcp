package net.sf.anathema.framework.item.data;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextualDescription;

public interface IItemDescription {

  public ITextualDescription getName();

  public IStyledTextualDescription getContent();

  public void setClean();

  public boolean isDirty();

  public void addDirtyListener(IChangeListener changeListener);

  public void removeDirtyListener(IChangeListener changeListener);
}