package net.sf.anathema.character.abilities.sheet;

import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.pdf.PdfContentByte;

public abstract class AbstractTraitSubsectionEncoder implements ISubSectionEncoder {
  private static final int SUBSECTION_FONT_SIZE = 8;
  private final PdfTraitEncoder traitEncoder;
  private final int lineCount;

  public AbstractTraitSubsectionEncoder(PdfTraitEncoder traitEncoder, int lineCount) {
    this.traitEncoder = traitEncoder;
    this.lineCount = lineCount;
  }

  protected int drawNamedTraitSection(
      PdfContentByte directContent,
      String title,
      IDisplayTrait[] traits,
      Position position,
      float width,
      int dotCount) {
    int height = drawSubsectionHeader(directContent, title, position, width);
    for (int index = 0; index < lineCount && index < traits.length; index++) {
      IDisplayTrait trait = traits[index];
      String name = trait.getTraitType().getId();
      Position traitPosition = new Position(position.x, position.y - height);
      int value = trait.getValue();
      traitEncoder.encodeWithText(name, traitPosition, width, value, dotCount);
      height += traitEncoder.getTraitHeight();
    }
    for (int index = traits.length; index < lineCount; index++) {
      Position traitPosition = new Position(position.x, position.y - height);
      traitEncoder.encodeWithLine(traitPosition, width, 0, dotCount);
      height += traitEncoder.getTraitHeight();
    }
    return height;
  }

  private final int drawSubsectionHeader(PdfContentByte directContent, String text, Position position, float width) {
    setSubsectionFont(directContent);
    new PdfEncoder(directContent).drawText(
        text,
        new Position(position.x + width / 2, position.y),
        PdfContentByte.ALIGN_CENTER);
    return (int) (SUBSECTION_FONT_SIZE * 1.5);
  }

  protected final void setSubsectionFont(PdfContentByte directContent) {
    directContent.setFontAndSize(IEncodeContext.BASEFONT, SUBSECTION_FONT_SIZE);
  }
}