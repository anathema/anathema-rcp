package net.sf.anathema.character.sheet.anima.util;

import static com.lowagie.text.Font.*;
import static java.awt.Color.*;
import static net.sf.anathema.character.sheet.common.IEncodeContext.*;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;

public class CaretSymbol {

  private static final String SYMBOL = "\u00A8  "; //$NON-NLS-1$
  private final int fontSize;

  public CaretSymbol(int fontSize) {
    this.fontSize = fontSize;
  }

  public Chunk createChunk() {
    Font font = new Font(SYMBOLFONT, fontSize, NORMAL, BLACK);
    return new Chunk(SYMBOL, font);
  }

  public float getSymbolWidth() {
    return SYMBOLFONT.getWidthPoint(SYMBOL, fontSize);
  }
}