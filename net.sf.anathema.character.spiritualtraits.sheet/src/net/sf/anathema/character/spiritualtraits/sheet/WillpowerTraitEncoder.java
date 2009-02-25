package net.sf.anathema.character.spiritualtraits.sheet;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

public class WillpowerTraitEncoder {
  private static final float PADDING = IVoidStateFormatConstants.PADDING / 2f;
  private final PdfTraitEncoder traitEncoder;

  public WillpowerTraitEncoder(final IGraphicalEncoder graphicalEncoder) {
    this.traitEncoder = PdfTraitEncoder.createMediumTraitEncoder(graphicalEncoder);
  }

  public void encode(final ICharacter character, final Bounds bounds) {
    final float width = bounds.width - 2 * PADDING;
    final float leftX = bounds.x + PADDING;
    final int value = getWillpowerValue(character);
    final float entryHeight = Math.max((bounds.height - PADDING) / 2, traitEncoder.getTraitHeight());
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