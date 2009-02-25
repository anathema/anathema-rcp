package net.sf.anathema.character.spiritualtraits.sheet;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.pdf.PdfContentByte;

public class VirtuesBoxEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(ICharacter character) {
    return "Virtues";
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds) {
    PdfEncoder graphicalEncoder = new PdfEncoder(directContent);
    PdfTraitEncoder traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(graphicalEncoder);
    encodeVirtues(graphicalEncoder, traitEncoder, bounds, getVirtues(character));
  }

  protected DisplayTraitList<IDisplayTrait> getVirtues(ICharacter character) {
    List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = new SpiritualTraitDisplayGroupFactory().createDisplayTraitGroups(character);
    return new DisplayTraitList<IDisplayTrait>(displayGroups);
  }

  public void encodeVirtues(
      PdfEncoder graphicalEncoder,
      PdfTraitEncoder traitEncoder,
      Bounds bounds,
      DisplayTraitList<IDisplayTrait> virtues) {
    float virtuePadding = bounds.width / 8;
    float leftVirtueX = bounds.x + virtuePadding / 2;
    float width = (bounds.width - 2 * virtuePadding) / 2;
    float rightVirtueX = (int) (bounds.x + width + virtuePadding * 1.5);
    float upperY = (int) bounds.getMaxY();
    float centerY = (int) bounds.getCenterY();
    encodeVirtue(
        graphicalEncoder,
        traitEncoder,
        virtues.getTrait(COMPASSION_ID),
        new Position(leftVirtueX, upperY),
        width);
    encodeVirtue(
        graphicalEncoder,
        traitEncoder,
        virtues.getTrait(TEMPERANCE_ID),
        new Position(rightVirtueX, upperY),
        width);
    encodeVirtue(
        graphicalEncoder,
        traitEncoder,
        virtues.getTrait(CONVICTION_ID),
        new Position(leftVirtueX, centerY),
        width);
    encodeVirtue(graphicalEncoder, traitEncoder, virtues.getTrait(VALOR_ID), new Position(rightVirtueX, centerY), width);
  }

  private void encodeVirtue(
      IGraphicalEncoder graphicalEncoder,
      PdfTraitEncoder traitEncoder,
      IDisplayTrait trait,
      Position position,
      float width) {
    float yPosition = position.y;
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT - 3;
    String label = trait.getTraitType().getId();
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