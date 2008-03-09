package net.sf.anathema.character.abilities.util;

import java.util.List;

import net.sf.anathema.character.abilities.model.AbilitiesGroupTemplate;
import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.display.DisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.groupeditor.FavorizationHandler;
import net.sf.anathema.character.trait.model.ITraitTemplateFactory;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.character.trait.template.ITraitTemplate;
import net.sf.anathema.character.trait.template.StaticTraitTemplate;
import net.sf.anathema.lib.collection.CollectionUtilities;

public class AbilitiesDisplayUtilties {

  private static final class StaticExportTemplateFactory implements ITraitTemplateFactory {
    @Override
    public ITraitTemplate getTraitTemplate(String traitId) {
      return new StaticTraitTemplate(10);
    }
  }

  public static List<IDisplayTraitGroup<IDisplayTrait>> getDisplayAttributeGroups(ICharacter character) {
    String modelId = IAbilitiesPluginConstants.MODEL_ID;
    ICharacterTemplate characterTemplate = new CharacterTemplateProvider().getTemplate(character.getTemplateId());
    TraitCollectionContext context = new TraitCollectionContext(
        character,
        character,
        modelId,
        new AbilitiesGroupTemplate(characterTemplate),
        new StaticExportTemplateFactory());
    IFavorizationHandler favorizationHandler = new FavorizationHandler(
        character,
        new AbilitiesTemplateProvider().getTraitTemplate(character.getTemplateId()),
        modelId);
    TraitGroup[] traitGroups = context.getTraitGroups();
    return CollectionUtilities.transform(traitGroups, new DisplayTraitGroupTransformer(context, favorizationHandler));
  }
}