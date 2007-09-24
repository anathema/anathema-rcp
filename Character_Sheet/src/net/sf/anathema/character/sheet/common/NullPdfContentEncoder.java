package net.sf.anathema.character.sheet.common;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class NullPdfContentEncoder extends AbstractExecutableExtension implements IPdfContentBoxEncoder {

  private final String header;

  public NullPdfContentEncoder() {
    this("Null"); //$NON-NLS-1$
  }

  public NullPdfContentEncoder(String header) {
    this.header = header;
  }

  public void encode(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException {
    // Nothing to do
  }

  public String getHeader() {
    return header;
  }
}