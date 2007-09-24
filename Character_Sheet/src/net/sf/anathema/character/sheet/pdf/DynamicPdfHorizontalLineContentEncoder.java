package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public final class DynamicPdfHorizontalLineContentEncoder extends AbstractExecutableExtension implements
    IDynamicPdfContentBoxEncoder {
  final IPdfContentBoxEncoder encoder;

  DynamicPdfHorizontalLineContentEncoder(String encoderName) {
    this.encoder = new PdfHorizontalLineContentEncoder(1, encoderName);
  }

  @Override
  public float getHeight() {
    return 42;
  }

  @Override
  public void encode(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException {
    encoder.encode(directContent, character, bounds);
  }

  @Override
  public String getHeader() {
    return encoder.getHeader();
  }
}