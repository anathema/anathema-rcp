package net.sf.anathema.character.spiritualtraits.sheet;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.AbstractPdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class EssenceBoxEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    int essenceValue = getEssenceValue(character);
    int essenceMax = context.getMaxEssence();
    final PdfTraitEncoder traitEncoder = PdfTraitEncoder.createLargeTraitEncoder(directContent);
    int traitHeight = traitEncoder.getTraitHeight();
    Position essencePosition = new Position(bounds.getMinX(), (bounds.getMaxY() - traitHeight));
    traitEncoder.encodeDotsCenteredAndUngrouped(essencePosition, bounds.width, essenceValue, essenceMax);
    EssencePoolEncoder poolEncoder = new EssencePoolEncoder(directContent);
    Bounds poolBounds = new Bounds(bounds.x, bounds.y, bounds.width, (bounds.height - traitHeight));
    poolEncoder.encode(poolBounds);
  }

  private int getEssenceValue(ICharacter character) {
    List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = new SpiritualTraitDisplayGroupFactory().createDisplayTraitGroups(character);
    DisplayTraitList<IDisplayTrait> displayTraitList = new DisplayTraitList<IDisplayTrait>(displayGroups);
    return displayTraitList.getTrait(IPluginConstants.ESSENCE_TRAIT_ID).getValue();
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Essence";
  }
}