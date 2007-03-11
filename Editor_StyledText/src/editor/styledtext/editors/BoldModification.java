package editor.styledtext.editors;


import net.sf.anathema.lib.textualdescription.ITextFormat;

public class BoldModification extends AbstractTextModification {

  @Override
  protected boolean isActive(ITextFormat format) {
    return format.getFontStyle().isBold();
  }
}