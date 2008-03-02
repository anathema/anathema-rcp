package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.model.TraitCollectionContext;

public class AttributesContext {

  public static ITraitCollectionContext create(ICharacterId characterId, IModelCollection modelCollection) {
    return TraitCollectionContext.create(
        characterId,
        modelCollection,
        IAttributesPluginConstants.MODEL_ID,
        new AttributeGroupConfiguration());
  }
}