package net.sf.anathema.character.spiritualtraits.sheet;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class EssenceBoxEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    encode(new PdfEncoder(directContent), context, character, bounds);
  }

  public void encode(IGraphicalEncoder graphicalEncoder, IEncodeContext context, ICharacter character, Bounds bounds) {
    EssenceTraitEncoder traitEncoder = new EssenceTraitEncoder(graphicalEncoder, context.getMaxEssence());
    EssencePoolEncoder poolEncoder = new EssencePoolEncoder(graphicalEncoder);
    int usedHeight = traitEncoder.encode(character, bounds);
    Bounds poolBounds = new Bounds(bounds.x, bounds.y, bounds.width, (bounds.height - usedHeight));
    poolEncoder.encode(poolBounds);
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Essence";
  }
}