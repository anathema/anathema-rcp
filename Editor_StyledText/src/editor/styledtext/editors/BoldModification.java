package editor.styledtext.editors;


import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextFormat;
import net.sf.anathema.lib.textualdescription.TextAspect;

public class BoldModification extends AbstractTextModification {

  @Override
  protected boolean isActive(ITextFormat format) {
    return format.getFontStyle().isBold();
  }

  @Override
  public void perform(IStyledTextualDescription content, int offset, int length) {
    content.toggleFontStyle(offset, length, TextAspect.Bold);
  }
}