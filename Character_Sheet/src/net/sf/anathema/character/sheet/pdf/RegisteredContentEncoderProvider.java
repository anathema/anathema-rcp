package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;

public class RegisteredContentEncoderProvider implements IContentEncoderProvider {

  @Override
  public IPdfContentBoxEncoder getContentEncoder(String encoderName, ICharacter character) {
    return null;
  }

  @Override
  public IDynamicPdfContentBoxEncoder getDynamicContentEncoder(String encoderName, ICharacter character) {
    return null;
  }
}