package net.sf.anathema.character.spiritualtraits.sheet;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

public class EssenceTraitEncoder {

  private final int essenceMaximum;
  private final IGraphicalEncoder pdfEncoder;

  public EssenceTraitEncoder(IGraphicalEncoder pdfEncoder, int essenceMaximum) {
    this.pdfEncoder = pdfEncoder;
    this.essenceMaximum = essenceMaximum;
  }

  public int encode(ICharacter character, Bounds bounds) {
    final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createLargeTraitEncoder(pdfEncoder);
    encodeEssenceDots(traitEncoder, bounds, getEssenceValue(character), essenceMaximum);
    return traitEncoder.getTraitHeight();
  }

  private void encodeEssenceDots(final PdfTraitEncoder traitEncoder, Bounds bounds, int essenceValue, int essenceMax) {
    Position essencePosition = new Position(bounds.getMinX(), (bounds.getMaxY() - traitEncoder.getTraitHeight()));
    traitEncoder.encodeDotsCenteredAndUngrouped(essencePosition, bounds.width, essenceValue, essenceMax);
  }

  protected int getEssenceValue(ICharacter character) {
    List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = new SpiritualTraitDisplayGroupFactory().createDisplayTraitGroups(character);
    DisplayTraitList<IDisplayTrait> displayTraitList = new DisplayTraitList<IDisplayTrait>(displayGroups);
    return displayTraitList.getTrait(IPluginConstants.ESSENCE_ID).getValue();
  }
}