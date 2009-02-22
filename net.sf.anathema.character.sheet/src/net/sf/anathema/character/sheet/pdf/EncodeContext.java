package net.sf.anathema.character.sheet.pdf;

import java.awt.Color;

import net.sf.anathema.character.sheet.common.IEncodeContext;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

public class EncodeContext implements IEncodeContext {

  public static final BaseFont BASEFONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);

  @Override
  public BaseFont getBaseFont() {
    return EncodeContext.BASEFONT;
  }

  @Override
  public int getMaxEssence() {
    return 7;
  }
}