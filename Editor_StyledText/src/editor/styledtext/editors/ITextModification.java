package editor.styledtext.editors;

import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;

public interface ITextModification {

  public boolean isActive(IStyledTextualDescription description, int offset, int length);

  public void perform(IStyledTextualDescription description, int offset, int length);
}