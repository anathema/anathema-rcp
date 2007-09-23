package net.sf.anathema.character.sheet.print;

import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class NullContentEncoderProvider implements IContentEncoderProvider {

  private final class NullDynamicContentBoxEncoder implements IDynamicPdfContentBoxEncoder {
    final IPdfContentBoxEncoder encoder;

    private NullDynamicContentBoxEncoder(String encoderName) {
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

  @Override
  public IPdfContentBoxEncoder getContentEncoder(String encoderName, ICharacter modelProvider) {
    return new PdfHorizontalLineContentEncoder(1, encoderName);
  }

  @Override
  public IDynamicPdfContentBoxEncoder getDynamicContentEncoder(final String encoderName, ICharacter character) {
    return new NullDynamicContentBoxEncoder(encoderName);
  }
}