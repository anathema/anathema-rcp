package editor.styledtext.editors;

import net.sf.anathema.lib.textualdescription.ITextFormat;

public class UnderlineModification extends AbstractTextModification {

  @Override
  protected boolean isActive(ITextFormat format) {
    return format.isUnderline();
  }
}