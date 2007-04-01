package editor.styledtext.editors;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextFormat;

public class ItalicsModification extends AbstractTextModification {

  @Override
  protected boolean isActive(ITextFormat format) {
    return format.getFontStyle().isItalic();
  }

  @Override
  public void perform(IStyledTextualDescription content, int offset, int length) {
    content.toggleFontStyle(offset, length, FontStyle.ITALIC);
  }
}