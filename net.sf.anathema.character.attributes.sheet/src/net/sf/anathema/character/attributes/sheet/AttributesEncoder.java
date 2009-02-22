package net.sf.anathema.character.attributes.sheet;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributeMessages;
import net.sf.anathema.character.attributes.util.AttributeDisplayGroupFactory;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.trait.display.IDisplayFavorization;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.sheet.PdfTraitEncoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class AttributesEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(ICharacter character) {
    return "Attributes"; //$NON-NLS-1$
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = new AttributeDisplayGroupFactory().createDisplayTraitGroups(character);
    encodeAttributes(directContent, bounds, displayGroups);
  }

  public final void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      Iterable<IDisplayTraitGroup<IDisplayTrait>> attributeGroups) {
    final PdfTraitEncoder smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(directContent);
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - groupSpacing;
    for (IDisplayTraitGroup< ? extends IDisplayTrait> group : attributeGroups) {
      y -= groupSpacing;
      for (IDisplayTrait trait : group) {
        y = encodeTrait(smallTraitEncoder, trait, contentBounds, y);
      }
    }
  }

  private float encodeTrait(PdfTraitEncoder traitEncoder, IDisplayTrait trait, Bounds contentBounds, float y) {
    float newY = y;
    IDisplayFavorization favorization = trait.getFavorization();
    String traitLabel = AttributeMessages.get(trait.getTraitType().getId());
    int value = trait.getValue();
    Position position = new Position(contentBounds.x, newY);
    if (!favorization.isFavorable()) {
      newY -= traitEncoder.encodeWithText(traitLabel, position, contentBounds.width, value, trait.getMaximalValue());
    }
    else {
      boolean favored = favorization.isFavored();
      int maximalValue = trait.getMaximalValue();
      float width = contentBounds.width;
      newY -= traitEncoder.encodeWithTextAndRectangle(
          traitLabel,
          position,
          width,
          value,
          favored,
          maximalValue);
    }
    return newY;
  }
}