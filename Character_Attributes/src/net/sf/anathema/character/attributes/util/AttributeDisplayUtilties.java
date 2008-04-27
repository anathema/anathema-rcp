package net.sf.anathema.character.attributes.util;

import java.util.List;

import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.attributes.model.AttributesTemplateProvider;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.display.DisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.groupeditor.FavorizationHandler;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.lib.collection.CollectionUtilities;

public class AttributeDisplayUtilties {

  public static List<IDisplayTraitGroup<IDisplayTrait>> getDisplayAttributeGroups(ICharacter character) {
    String modelId = IAttributesPluginConstants.MODEL_ID;
    TraitCollectionContext context = new TraitCollectionContext(
        character.getTemplateId(),
        character,
        character,
        modelId,
        new AttributeGroupTemplate());
    IFavorizationHandler favorizationHandler = new FavorizationHandler(
        character,
        new AttributesTemplateProvider().getTraitTemplate(character.getTemplateId()),
        modelId);
    TraitGroup[] traitGroups = context.getTraitGroups();
    return CollectionUtilities.transform(traitGroups, new DisplayTraitGroupTransformer(context, favorizationHandler));
  }
}