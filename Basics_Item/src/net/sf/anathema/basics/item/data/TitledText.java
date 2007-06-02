package net.sf.anathema.basics.item.data;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.SimpleTextualDescription;
import net.sf.anathema.lib.textualdescription.StyledTextualDescription;

public class TitledText implements ITitledText {

  private final ITextualDescription name;
  private final IStyledTextualDescription content;

  public TitledText() {
    this(""); //$NON-NLS-1$
  }

  public TitledText(String initialName) {
    this.name = new SimpleTextualDescription(initialName);
    this.content = new StyledTextualDescription();
  }

  public ITextualDescription getName() {
    return name;
  }

  public IStyledTextualDescription getContent() {
    return content;
  }

  public void setClean() {
    name.setClean();
    content.setClean();
  }

  public boolean isDirty() {
    return name.isDirty() || content.isDirty();
  }

  public void addDirtyListener(IChangeListener changeListener) {
    name.addDirtyChangeListener(changeListener);
    content.addDirtyChangeListener(changeListener);
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    name.removeDirtyChangeListener(changeListener);
    content.removeDirtyChangeListener(changeListener);
  }
}