package net.sf.anathema.character.spiritualtraits.sheet;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.pdf.PdfContentByte;

public class EssenceTraitEncoder {

  private final PdfContentByte directContent;
  private final int essenceMaximum;

  public EssenceTraitEncoder(PdfContentByte directContent, int essenceMaximum) {
    this.directContent = directContent;
    this.essenceMaximum = essenceMaximum;
  }

  public int encode(ICharacter character, Bounds bounds) {
    final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createLargeTraitEncoder(new PdfEncoder(directContent));
    encodeEssenceDots(traitEncoder, bounds, getEssenceValue(character), essenceMaximum);
    return traitEncoder.getTraitHeight();
  }

  private void encodeEssenceDots(final PdfTraitEncoder traitEncoder, Bounds bounds, int essenceValue, int essenceMax) {
    Position essencePosition = new Position(bounds.getMinX(), (bounds.getMaxY() - traitEncoder.getTraitHeight()));
    traitEncoder.encodeDotsCenteredAndUngrouped(essencePosition, bounds.width, essenceValue, essenceMax);
  }

  private int getEssenceValue(ICharacter character) {
    List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = new SpiritualTraitDisplayGroupFactory().createDisplayTraitGroups(character);
    DisplayTraitList<IDisplayTrait> displayTraitList = new DisplayTraitList<IDisplayTrait>(displayGroups);
    return displayTraitList.getTrait(IPluginConstants.ESSENCE_TRAIT_ID).getValue();
  }
}