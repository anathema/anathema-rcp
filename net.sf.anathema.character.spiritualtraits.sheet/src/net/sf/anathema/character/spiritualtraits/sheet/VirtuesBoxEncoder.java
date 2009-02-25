package net.sf.anathema.character.spiritualtraits.sheet;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.spiritualtraits.display.SpiritualTraitDisplayGroupFactory;
import net.sf.anathema.character.trait.display.DisplayTraitList;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;

import com.lowagie.text.pdf.PdfContentByte;

public class VirtuesBoxEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(final ICharacter character) {
    return "Virtues";

  }

  @Override
  public void encode(
      final PdfContentByte directContent,
      final IEncodeContext context,
      final ICharacter character,
      final Bounds bounds) {
    final PdfEncoder graphicalEncoder = new PdfEncoder(directContent);
    new VirtueTraitsEncoder(graphicalEncoder).encodeVirtues(bounds, getVirtues(character));
  }

  protected DisplayTraitList<IDisplayTrait> getVirtues(final ICharacter character) {
    final SpiritualTraitDisplayGroupFactory groupFactory = new SpiritualTraitDisplayGroupFactory();
    final List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = groupFactory.createDisplayTraitGroups(character);
    return new DisplayTraitList<IDisplayTrait>(displayGroups);
  }
}