package net.sf.anathema.character.sheet.content;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;

public interface IContentEncoderProvider {

  public IPdfContentBoxEncoder getContentEncoder(String encoderName, IModelContainer character);

  public IDynamicPdfContentBoxEncoder getDynamicContentEncoder(String encoderName, IModelContainer character);
}