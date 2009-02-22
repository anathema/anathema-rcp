package net.sf.anathema.character.sheet.common;

import java.awt.Color;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public interface IEncodeContext {

  public static final BaseFont BASEFONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);

  public int getMaxEssence();

  public BaseFont getBaseFont();
}