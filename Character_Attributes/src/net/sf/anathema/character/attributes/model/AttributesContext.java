package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AttributesContext {

  public static ITraitCollectionContext create(ICharacterId characterId, IModelCollection modelCollection) {
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterId);
    ITraitCollectionTemplate traitTemplate = new AttributesTemplateProvider().getTraitTemplate(template.getId());
    return TraitCollectionContext.create(
        characterId,
        modelCollection,
        IAttributesPluginConstants.MODEL_ID,
        traitTemplate);
  }
}