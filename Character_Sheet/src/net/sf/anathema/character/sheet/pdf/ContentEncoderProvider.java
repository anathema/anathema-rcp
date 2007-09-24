package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class ContentEncoderProvider implements IContentEncoderProvider {

  private final class HorizontalLineDynamicContentBoxEncoder extends AbstractExecutableExtension implements
      IDynamicPdfContentBoxEncoder {
    final IPdfContentBoxEncoder encoder;

    private HorizontalLineDynamicContentBoxEncoder(String encoderName) {
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
  private IPdfContentBoxEncoder getRegisteredEncoder(String encoderName) {
    // TODO Registered Provider
    return null;
  }
  

  @Override
  public IPdfContentBoxEncoder getContentEncoder(String encoderName, ICharacter character) {
    IPdfContentBoxEncoder registeredEncoder = getRegisteredEncoder(encoderName);
    if (registeredEncoder != null) {
      return registeredEncoder;
    }
    return new PdfHorizontalLineContentEncoder(1, encoderName);
  }

  @Override
  public IDynamicPdfContentBoxEncoder getDynamicContentEncoder(final String encoderName, ICharacter character) {
    IPdfContentBoxEncoder registeredEncoder = getRegisteredEncoder(encoderName);
    if (registeredEncoder != null) {
      // TODO if not is dynamic ummanteln mit fester Größe
      return (IDynamicPdfContentBoxEncoder) registeredEncoder;
    }
    return new HorizontalLineDynamicContentBoxEncoder(encoderName);
  }
}