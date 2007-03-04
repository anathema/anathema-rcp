package net.sf.anathema.basics.jface.text;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.lib.textualdescription.ITextFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;

public class StyleRangeFactory {

  public StyleRange createStyleRange(final int startIndex, int length, ITextFormat format) {
    FontStyle fontStyleEnum = format.getFontStyle();
    StyleRange styleRange = new StyleRange(startIndex, length, null, null, convertToSwt(fontStyleEnum));
    styleRange.underline = format.isUnderline();
    return styleRange;
  }

  private static int convertToSwt(FontStyle fontStyle) {
    int swtStyle = SWT.NORMAL;
    if (fontStyle.isBold()) {
      swtStyle |= SWT.BOLD;
    }
    if (fontStyle.isItalic()) {
      swtStyle |= SWT.ITALIC;
    }
    return swtStyle;
  }
}