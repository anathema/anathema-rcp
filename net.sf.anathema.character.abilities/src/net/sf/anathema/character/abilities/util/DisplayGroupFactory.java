package net.sf.anathema.character.abilities.util;

import java.util.List;

import net.sf.anathema.character.abilities.model.AbilitiesGroupTemplate;
import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
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

public class DisplayGroupFactory {

  private final ICharacter character;
  private final String modelId;

  public DisplayGroupFactory(ICharacter character, String modelId) {
    this.character = character;
    this.modelId = modelId;
  }

  public List<IDisplayTraitGroup<IDisplayTrait>> create() {
    TraitCollectionContext collectionContext = createCollectionContext();
    return transformGroupsToDisplayGroups(collectionContext);
  }

  private List<IDisplayTraitGroup<IDisplayTrait>> transformGroupsToDisplayGroups(TraitCollectionContext context) {
    TraitGroup[] traitGroups = context.getTraitGroups();
    IFavorizationInteraction favorizationHandler = createFavorizationInteraction();
    DisplayTraitGroupTransformer transformer = new DisplayTraitGroupTransformer(context, favorizationHandler);
    return CollectionUtilities.transform(traitGroups, transformer);
  }

  private IFavorizationInteraction createFavorizationInteraction() {
    ITraitCollectionTemplate traitTemplate = new AbilitiesTemplateProvider().getTraitTemplate(character.getTemplateId());
    IFavorizationTemplate favorizationTemplate = traitTemplate.getFavorizationTemplate();
    return new FavorizationInteraction(character, favorizationTemplate, modelId);
  }

  private TraitCollectionContext createCollectionContext() {
    ICharacterTemplate characterTemplate = new CharacterTemplateProvider().getTemplate(character.getTemplateId());
    String templateId = characterTemplate.getId();
    AbilitiesGroupTemplate groupTemplate = new AbilitiesGroupTemplate(characterTemplate);
    return new TraitCollectionContext(templateId, character, character, modelId, groupTemplate);
  }
}