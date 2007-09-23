package net.sf.anathema.character.sheet.util;

import net.sf.anathema.character.sheet.ICharacter;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;

public interface IContentEncoderProvider {

  public IPdfContentBoxEncoder getContentEncoder(String encoderName, ICharacter modelProvider);

  public IDynamicPdfContentBoxEncoder getDynamicContentEncoder(String encoderName, ICharacter character);
}