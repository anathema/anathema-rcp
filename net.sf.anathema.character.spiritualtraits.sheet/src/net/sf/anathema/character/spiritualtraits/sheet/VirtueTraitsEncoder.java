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
    final String label = SpiritualTraitLabelMap.getLabel(trait.getTraitType().getId());
    final float centerX = position.x + width / 2;
    final Position labelPosition = new Position(centerX, position.y - IVoidStateFormatConstants.LINE_HEIGHT + 3);
    graphicalEncoder.drawText(label, labelPosition, PdfContentByte.ALIGN_CENTER);
    final Position traitPosition = new Position(position.x, labelPosition.y - traitEncoder.getTraitHeight() + 1);
    traitEncoder.encodeDotsCenteredAndUngrouped(traitPosition, width, trait.getValue(), 5);
    final Position squarePosition = new Position(position.x, (traitPosition.y - traitEncoder.getTraitHeight() + 1));
    traitEncoder.encodeSquaresCenteredAndUngrouped(squarePosition, width, 0, 5);
  }
}