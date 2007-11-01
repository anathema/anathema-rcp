package net.sf.anathema.character.sheet.attributes;

import java.awt.Color;
import java.util.List;

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
import net.sf.anathema.character.trait.display.DisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.display.IDisplayFavorization;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.template.ITraitTemplate;
import net.sf.anathema.character.trait.template.StaticTraitTemplate;
import net.sf.anathema.lib.collection.CollectionUtilities;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfAttributesEncoder extends AbstractExecutableExtension implements IPdfContentBoxEncoder {

  private static final BaseFont BASEFONT = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
  private PdfTraitEncoder smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(BASEFONT);

  @Override
  public String getHeader(ICharacter character) {
    return "Attributes"; //$NON-NLS-1$
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    List<IDisplayTraitGroup<IDisplayTrait>> displayGroups = getDisplayAttributeGroups(character);
    encodeAttributes(directContent, bounds, displayGroups);
  }

  private List<IDisplayTraitGroup<IDisplayTrait>> getDisplayAttributeGroups(ICharacter character) {
    AttributesContext context = new AttributesContext(character, character) {
      @Override
      public ITraitTemplate getTraitTemplate() {
        return new StaticTraitTemplate(10);
      }
    };
    IFavorizationHandler favorizationHandler = new AttributeFavorizationHandler(
        character,
        new AttributeTemplateProvider().getAttributeTemplate(character.getTemplateId()));
    TraitGroup[] traitGroups = context.getTraitGroups();
    return CollectionUtilities.transform(traitGroups, new DisplayTraitGroupTransformer(context, favorizationHandler));
  }

  public final void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      Iterable<IDisplayTraitGroup<IDisplayTrait>> attributeGroups) {
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - groupSpacing;
    for (IDisplayTraitGroup< ? extends IDisplayTrait> group : attributeGroups) {
      y -= groupSpacing;
      for (IDisplayTrait trait : group.getTraits()) {
        y = encodeTrait(trait, directContent, contentBounds, y);
      }
    }
  }

  private float encodeTrait(IDisplayTrait trait, PdfContentByte directContent, Bounds contentBounds, float y) {
    float newY = y;
    IDisplayFavorization favorization = trait.getFavorization();
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
          trait.getMaximalValue());
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
          trait.getMaximalValue());
    }
    return newY;
  }
}