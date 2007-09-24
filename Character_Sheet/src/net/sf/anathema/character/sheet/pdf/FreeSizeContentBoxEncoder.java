package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class FreeSizeContentBoxEncoder extends AbstractExecutableExtension implements IDynamicPdfContentBoxEncoder {

  @Override
  public float getHeight() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void encode(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException {
    // TODO Auto-generated method stub

  }

  @Override
  public String getHeader() {
    // TODO Auto-generated method stub
    return null;
  }

}
