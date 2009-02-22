package net.sf.anathema.character.sheet.common;

import com.lowagie.text.pdf.BaseFont;

public interface IEncodeContext {

  public int getMaxEssence();

  public BaseFont getBaseFont();
}