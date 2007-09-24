package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;

public class ContentEncoderProvider implements IContentEncoderProvider {

  private final IContentEncoderProvider registeredEncoders;

  public ContentEncoderProvider(IContentEncoderProvider registeredEncoders) {
    this.registeredEncoders = registeredEncoders;
  }

  @Override
  public IPdfContentBoxEncoder getContentEncoder(String encoderName, IModelContainer character) {
    IPdfContentBoxEncoder registeredEncoder = registeredEncoders.getContentEncoder(encoderName, character);
    if (registeredEncoder != null) {
      return registeredEncoder;
    }
    return new PdfHorizontalLineContentEncoder(1, encoderName);
  }

  @Override
  public IDynamicPdfContentBoxEncoder getDynamicContentEncoder(final String encoderName, IModelContainer character) {
    IDynamicPdfContentBoxEncoder registeredEncoder = registeredEncoders.getDynamicContentEncoder(encoderName, character);
    if (registeredEncoder != null) {
      return registeredEncoder;
    }
    return new DynamicPdfHorizontalLineContentEncoder(encoderName);
  }
}