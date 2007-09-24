package net.sf.anathema.character.sheet.attributes;

import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.PdfHorizontalLineContentEncoder;

public class AttributesBoxEncoder extends PdfHorizontalLineContentEncoder implements IPdfContentBoxEncoder {

  public AttributesBoxEncoder() {
    super(2, "Attributes");
  }
}