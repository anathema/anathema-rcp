package net.sf.anathema.character.spiritualtraits.sheet;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.pdf.PdfContentByte;

public class WillpowerBoxEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(final ICharacter character) {
    return "Willpower";
  }

  @Override
  public void encode(
      final PdfContentByte directContent,
      final IEncodeContext context,
      final ICharacter character,
      final Bounds bounds) {
    final IGraphicalEncoder graphicalEncoder = new PdfEncoder(directContent);
    new WillpowerTraitEncoder(graphicalEncoder).encode(character, bounds);
  }
}