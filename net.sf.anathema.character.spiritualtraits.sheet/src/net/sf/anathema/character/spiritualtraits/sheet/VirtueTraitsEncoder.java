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

  public VirtueTraitsEncoder(final IGraphicalEncoder graphicalEncoder) {
    this.graphicalEncoder = graphicalEncoder;
  }

  public void encodeVirtues(final Bounds bounds, final DisplayTraitList<IDisplayTrait> virtues) {
    final VirtuePositioning positioning = new VirtuePositioning(bounds);
    final float width = positioning.getCellWidth();
    encodeVirtue(virtues.getTrait(COMPASSION_ID), positioning.getCompassionPosition(), width);
    encodeVirtue(virtues.getTrait(TEMPERANCE_ID), positioning.getTemperancePosition(), width);
    encodeVirtue(virtues.getTrait(CONVICTION_ID), positioning.getConvictionPosition(), width);
    encodeVirtue(virtues.getTrait(VALOR_ID), positioning.getValorPosition(), width);
  }

  private void encodeVirtue(final IDisplayTrait trait, final Position position, final float width) {
    final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(graphicalEncoder);
    float yPosition = position.y;
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT - 3;
    final String label = SpiritualTraitLabelMap.getLabel(trait.getTraitType().getId());
    final float labelX = position.x + width / 2;
    graphicalEncoder.drawText(label, new Position(labelX, yPosition), PdfContentByte.ALIGN_CENTER);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    final Position traitPosition = new Position(position.x, yPosition);
    final int value = trait.getValue();
    traitEncoder.encodeDotsCenteredAndUngrouped(traitPosition, width, value, 5);
    yPosition -= traitEncoder.getTraitHeight() - 1;
    traitEncoder.encodeSquaresCenteredAndUngrouped(new Position(position.x, yPosition), width, 0, 5);
  }
}