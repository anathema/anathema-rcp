package net.sf.anathema.character.abilities.sheet;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.pdf.PdfContentByte;

public class EmptySubsectionEncoder extends AbstractTraitSubsectionEncoder {

  private final String title;
  private final int dotCount;

  public EmptySubsectionEncoder(PdfTraitEncoder traitEncoder, String title, int dotCount, int lineCount) {
    super(traitEncoder, lineCount);
    this.title = title;
    this.dotCount = dotCount;
  }

  @Override
  public int encode(PdfContentByte directContent, ICharacter character, Position position, float width) {
    return drawNamedTraitSection(directContent, title, new IDisplayTrait[0], position, width, dotCount);
  }
}