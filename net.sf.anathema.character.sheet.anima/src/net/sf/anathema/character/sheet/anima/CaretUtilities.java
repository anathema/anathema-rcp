package net.sf.anathema.character.sheet.anima;

import java.awt.Color;

import net.sf.anathema.character.sheet.common.IEncodeContext;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public class CaretUtilities {

  private static final String SYMBOL = "\u00A8  "; //$NON-NLS-1$
  private static final int FONT_SIZE = IEncodeContext.FONT_SIZE - 1;

  public static Chunk createCaretSymbolChunk(BaseFont basefont) {
    Font font = new Font(basefont, FONT_SIZE, Font.NORMAL, Color.BLACK);
    return new Chunk(SYMBOL, font);
  }

  public static float getCaretSymbolWidth(BaseFont basefont) {
    return basefont.getWidthPoint(SYMBOL, FONT_SIZE);
  }
}