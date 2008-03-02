package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

import org.easymock.EasyMock;

public class AttributeObjectMother {

  public static IModelCollection createModelProvider(ITraitCollectionModel attributes) {
    return CharacterObjectMother.createModelProvider(EasyMock.isA(IModelIdentifier.class), attributes);
  }

  public static IModelCollection createModelProvider(ITraitCollectionModel attributes, ICharacterId characterId) {
    return CharacterObjectMother.createModelProvider(new ModelIdentifier(characterId, IAttributesPluginConstants.MODEL_ID), attributes);
  }
}