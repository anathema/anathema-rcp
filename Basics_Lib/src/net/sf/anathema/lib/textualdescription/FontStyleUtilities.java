package net.sf.anathema.lib.textualdescription;

import net.disy.commons.core.text.font.FontStyle;

public class FontStyleUtilities {

  public static FontStyle combineFontStyles(FontStyle first, FontStyle second) {
    boolean isBold = (first.isBold() || second.isBold())
        && !(first.isBold() && second.isBold());
    boolean isItalic = (first.isItalic() || second.isItalic())
        && !(first.isItalic() && second.isItalic());
    return FontStyle.getStyle(isBold, isItalic);
  }
}