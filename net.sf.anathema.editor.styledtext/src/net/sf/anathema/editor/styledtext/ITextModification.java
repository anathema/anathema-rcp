package net.sf.anathema.editor.styledtext;

import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextAspectToggle;

public interface ITextModification {

  public boolean isActive(IStyledTextualDescription description, int offset, int length);

  public void perform(ITextAspectToggle description, int offset, int length);
}