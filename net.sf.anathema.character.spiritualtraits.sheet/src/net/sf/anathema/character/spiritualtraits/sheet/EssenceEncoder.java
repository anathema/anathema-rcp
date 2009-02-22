package net.sf.anathema.character.spiritualtraits.sheet;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.AbstractPdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;
import net.sf.anathema.lib.util.Identificate;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class EssenceEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final PdfTraitEncoder largeTraitEncoder = PdfTraitEncoder.createLargeTraitEncoder();

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = new SpiritualTraitDisplayGroupFactory().createDisplayTraitGroups(character);
    DisplayTraitList<IDisplayTrait> displayTraitList = new DisplayTraitList<IDisplayTrait>(displayGroups);
    int value = displayTraitList.getTrait(new Identificate("Essence")).getValue();
    Position essencePosition = new Position(bounds.x, bounds.y + bounds.height - largeTraitEncoder.getTraitHeight());
    int maxEssence = context.getMaxEssence();
    largeTraitEncoder.encodeDotsCenteredAndUngrouped(directContent, essencePosition, bounds.width, value, maxEssence);
    float poolHeight = bounds.height - largeTraitEncoder.getTraitHeight() - IVoidStateFormatConstants.TEXT_PADDING;
    float poolLineHeight = poolHeight / 2;
    Position personalPosition = new Position(bounds.x, essencePosition.y - poolLineHeight);
    encodePool(directContent, "Personal Pool", "0", personalPosition, bounds.width);
    Position peripheralPosition = new Position(bounds.x, essencePosition.y - 2 * poolLineHeight);
    encodePool(directContent, "Peripheral Pool", "0", peripheralPosition, bounds.width);
  }

  private void encodePool(
      PdfContentByte directContent,
      String label,
      String poolValue,
      Position poolPosition,
      float width) {
    drawText(directContent, label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " Available";
    float availableTextWidth = getDefaultTextWidth(availableString);
    Position availablePosition = new Position(poolPosition.x + width, poolPosition.y);
    drawText(directContent, availableString, availablePosition, PdfContentByte.ALIGN_RIGHT);
    float lineLength = 10;
    Position lineStartPoint = new Position(
        (int) (availablePosition.x - availableTextWidth - lineLength),
        poolPosition.y);
    drawMissingTextLine(directContent, lineStartPoint, lineLength);
    String totalString = poolValue + " Total / ";
    drawText(directContent, totalString, lineStartPoint, PdfContentByte.ALIGN_RIGHT);
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Essence";
  }
}