package net.sf.anathema.character.sheet.common;

import com.lowagie.text.pdf.BaseFont;

public interface IEncodeContext {

  public BaseFont getBaseFont();
  
  public int getMaxEssence();
}