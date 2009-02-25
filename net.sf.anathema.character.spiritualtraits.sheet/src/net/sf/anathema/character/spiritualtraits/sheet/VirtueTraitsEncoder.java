package net.sf.anathema.character.spiritualtraits.sheet;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitLabelMap;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.pdf.PdfContentByte;

public class VirtueTraitsEncoder {

  private final IGraphicalEncoder graphicalEncoder;

  public VirtueTraitsEncoder(IGraphicalEncoder graphicalEncoder) {
    this.graphicalEncoder = graphicalEncoder;
  }

  public void encodeVirtues(Bounds bounds, DisplayTraitList<IDisplayTrait> virtues) {
    float virtuePadding = bounds.width / 8;
    float leftVirtueX = bounds.x + virtuePadding / 2;
    float width = (bounds.width - 2 * virtuePadding) / 2;
    float rightVirtueX = (int) (bounds.x + width + virtuePadding * 1.5);
    float upperY = (int) bounds.getMaxY();
    float centerY = (int) bounds.getCenterY();
    encodeVirtue(virtues.getTrait(COMPASSION_ID), new Position(leftVirtueX, upperY), width);
    encodeVirtue(virtues.getTrait(TEMPERANCE_ID), new Position(rightVirtueX, upperY), width);
    encodeVirtue(virtues.getTrait(CONVICTION_ID), new Position(leftVirtueX, centerY), width);
    encodeVirtue(virtues.getTrait(VALOR_ID), new Position(rightVirtueX, centerY), width);
  }

  private void encodeVirtue(IDisplayTrait trait, Position position, float width) {
    PdfTraitEncoder traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(graphicalEncoder);
    float yPosition = position.y;
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT - 3;
    String label = SpiritualTraitLabelMap.getLabel(trait.getTraitType().getId());
    float labelX = position.x + width / 2;
    graphicalEncoder.drawText(label, new Position(labelX, yPosition), PdfContentByte.ALIGN_CENTER);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    Position traitPosition = new Position(position.x, yPosition);
    int value = trait.getValue();
    traitEncoder.encodeDotsCenteredAndUngrouped(traitPosition, width, value, 5);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    traitEncoder.encodeSquaresCenteredAndUngrouped(new Position(position.x, yPosition), width, 0, 5);
  }
}