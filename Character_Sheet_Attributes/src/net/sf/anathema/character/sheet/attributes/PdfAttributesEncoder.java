package net.sf.anathema.character.sheet.attributes;

import java.awt.Color;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributeFavorizationHandler;
import net.sf.anathema.character.attributes.model.AttributeMessages;
import net.sf.anathema.character.attributes.model.AttributeTemplateProvider;
import net.sf.anathema.character.attributes.model.AttributesContext;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.trait.PdfTraitEncoder;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.TraitGroupToDisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveFavorization;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.preference.TraitPreferenceFactory;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfAttributesEncoder extends AbstractExecutableExtension implements IPdfContentBoxEncoder {

  private static final BaseFont BASEFONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
  private PdfTraitEncoder smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(BASEFONT);
  private final int essenceMax = 10;

  @Override
  public String getHeader(ICharacter character) {
    return "Attributes"; //$NON-NLS-1$
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    IDisplayTraitGroup[] displayGroups = getDisplayAttributeGroups(character);
    encodeAttributes(directContent, bounds, displayGroups);
  }

  private IDisplayTraitGroup[] getDisplayAttributeGroups(ICharacter character) {
    AttributesContext context = new AttributesContext(character, character);
    IFavorizationHandler favorizationHandler = new AttributeFavorizationHandler(
        character,
        new AttributeTemplateProvider().getAttributeTemplate(character.getTemplateId()));
    return ArrayUtilities.transform(
        context.getTraitGroups(),
        IDisplayTraitGroup.class,
        new TraitGroupToDisplayTraitGroupTransformer(context, favorizationHandler, TraitPreferenceFactory.create()));
  }

  public final void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      IDisplayTraitGroup[] attributeGroups) {
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - groupSpacing;
    for (IDisplayTraitGroup group : attributeGroups) {
      y -= groupSpacing;
      for (IInteractiveTrait trait : group.getTraits()) {
        y = encodeTrait(trait, directContent, contentBounds, y);
      }
    }
  }

  private float encodeTrait(IInteractiveTrait trait, PdfContentByte directContent, Bounds contentBounds, float y) {
    float newY = y;
    IInteractiveFavorization favorization = trait.getFavorization();
    String traitLabel = AttributeMessages.get(trait.getTraitType().getId());
    int value = trait.getValue();
    Position position = new Position(contentBounds.x, newY);
    if (!favorization.isFavorable()) {
      newY -= smallTraitEncoder.encodeWithText(
          directContent,
          traitLabel,
          position,
          contentBounds.width,
          value,
          essenceMax);
    }
    else {
      boolean favored = favorization.isFavored();
      newY -= smallTraitEncoder.encodeWithTextAndRectangle(
          directContent,
          traitLabel,
          position,
          contentBounds.width,
          value,
          favored,
          essenceMax);
    }
    return newY;
  }
}