package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class DefaultHeightDynamicEncoder extends UnconfiguredExecutableExtension implements IDynamicPdfContentBoxEncoder {

  private final IPdfContentBoxEncoder encoder;

  public DefaultHeightDynamicEncoder(IPdfContentBoxEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public float getHeight(ICharacter character) {
    return 77;
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds) throws DocumentException {
    encoder.encode(directContent, context, character, bounds);
  }

  @Override
  public String getHeader(ICharacter character) {
    return encoder.getHeader(character);
  }
}