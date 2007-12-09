package net.sf.anathema.character.attributes.util;

import java.util.List;

import net.sf.anathema.character.attributes.model.AttributeFavorizationHandler;
import net.sf.anathema.character.attributes.model.AttributeTemplateProvider;
import net.sf.anathema.character.attributes.model.AttributesContext;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.display.DisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.template.ITraitTemplate;
import net.sf.anathema.character.trait.template.StaticTraitTemplate;
import net.sf.anathema.lib.collection.CollectionUtilities;

public class AttributeDisplayUtilties {

  public static List<IDisplayTraitGroup<IDisplayTrait>> getDisplayAttributeGroups(ICharacter character) {
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
}