package net.sf.anathema.character.attributes.util;

import java.util.List;

import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.attributes.model.AttributesTemplateProvider;
import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.FavorizationInteraction;
import net.sf.anathema.character.trait.display.DisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.lib.collection.CollectionUtilities;

public class AttributeDisplayUtilties {

  public static List<IDisplayTraitGroup<IDisplayTrait>> getDisplayAttributeGroups(ICharacter character) {
    String modelId = IAttributesPluginConstants.MODEL_ID;
    TraitCollectionContext context = new TraitCollectionContext(character, modelId, new AttributeGroupTemplate());
    String templateId = character.getTemplate().getId();
    ITraitCollectionTemplate attributesTemplate = new AttributesTemplateProvider().getTraitTemplate(templateId);
    IFavorizationTemplate favorizationTemplate = attributesTemplate.getFavorizationTemplate();
    IFavorizationInteraction favorizationHandler = new FavorizationInteraction(character, favorizationTemplate, modelId);
    TraitGroup[] traitGroups = context.getTraitGroups();
    return CollectionUtilities.transform(traitGroups, new DisplayTraitGroupTransformer(context, favorizationHandler));
  }
}