package net.sf.anathema.character.sheet.page;

import net.sf.anathema.character.sheet.ICharacter;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfPageEncoder {

  public void encode(Document document, PdfContentByte directContent, ICharacter character)
      throws DocumentException;
}