package net.sf.anathema.character.sheet.pdf;


import net.sf.anathema.character.sheet.common.IEncodeContext;

import com.lowagie.text.pdf.BaseFont;

public class EncodeContext implements IEncodeContext {

  @Override
  public BaseFont getBaseFont() {
    return IEncodeContext.BASEFONT;
  }

  @Override
  public int getMaxEssence() {
    return 7;
  }
}