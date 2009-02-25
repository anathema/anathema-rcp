package net.sf.anathema.character.spiritualtraits.sheet;

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
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

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
    final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(graphicalEncoder);
    final float padding = IVoidStateFormatConstants.PADDING / 2f;
    final float width = bounds.width - 2 * padding;
    final float leftX = bounds.x + padding;
    final int value = getWillpowerValue(character);
    final float entryHeight = Math.max((bounds.height - padding) / 2, traitEncoder.getTraitHeight());
    final Position dotsPosition = new Position(leftX, bounds.getMaxY() - entryHeight);
    traitEncoder.encodeDotsCenteredAndUngrouped(dotsPosition, width, value, 10);
    final Position squarePosition = new Position(leftX, dotsPosition.y - entryHeight);
    traitEncoder.encodeSquaresCenteredAndUngrouped(squarePosition, width, 0, 10);
  }

  protected int getWillpowerValue(final ICharacter character) {
    final List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = new SpiritualTraitDisplayGroupFactory().createDisplayTraitGroups(character);
    final DisplayTraitList<IDisplayTrait> displayTraitList = new DisplayTraitList<IDisplayTrait>(displayGroups);
    return displayTraitList.getTrait(IPluginConstants.WILLPOWER_ID).getValue();
  }
}