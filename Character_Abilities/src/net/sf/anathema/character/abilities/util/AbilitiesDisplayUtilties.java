package net.sf.anathema.character.abilities.util;

import java.util.List;

import net.sf.anathema.character.abilities.model.AbilitiesGroupTemplate;
import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.FavorizationHandler;
import net.sf.anathema.character.trait.display.DisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.lib.collection.CollectionUtilities;

public class AbilitiesDisplayUtilties {

  public static List<IDisplayTraitGroup<IDisplayTrait>> getDisplayAttributeGroups(ICharacter character) {
    String modelId = IAbilitiesPluginConstants.MODEL_ID;
    ICharacterTemplate characterTemplate = new CharacterTemplateProvider().getTemplate(character.getTemplateId());
    TraitCollectionContext context = new TraitCollectionContext(
        characterTemplate.getId(),
        character,
        character,
        modelId,
        new AbilitiesGroupTemplate(characterTemplate));
    IFavorizationHandler favorizationHandler = new FavorizationHandler(
        character,
        new AbilitiesTemplateProvider().getTraitTemplate(character.getTemplateId()).getFavorizationTemplate(),
        modelId);
    TraitGroup[] traitGroups = context.getTraitGroups();
    return CollectionUtilities.transform(traitGroups, new DisplayTraitGroupTransformer(context, favorizationHandler));
  }
}