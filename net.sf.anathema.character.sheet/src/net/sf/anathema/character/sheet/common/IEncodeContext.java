package net.sf.anathema.character.sheet.common;

import java.awt.Color;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public interface IEncodeContext {

  public static final int FONT_SIZE = 7;
  public static final BaseFont BASEFONT = new Font(Font.HELVETICA, FONT_SIZE, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
  public static final BaseFont SYMBOLFONT = new Font(Font.SYMBOL, FONT_SIZE, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(false);

  public int getMaxEssence();

  public BaseFont getBaseFont();
}