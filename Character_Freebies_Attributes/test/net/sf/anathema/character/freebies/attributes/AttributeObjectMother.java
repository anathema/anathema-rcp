package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

import org.easymock.EasyMock;

public class AttributeObjectMother {

  public static IModelCollection createModelProvider(ITraitCollectionModel attributes) {
    IModelIdentifier modelIdentifier = EasyMock.isA(IModelIdentifier.class);
    return createModelProvider(attributes, modelIdentifier);
  }

  public static IModelCollection createModelProvider(ITraitCollectionModel attributes, ICharacterId characterId) {
    return createModelProvider(attributes, new ModelIdentifier(characterId, Attributes.MODEL_ID));
  }

  private static IModelCollection createModelProvider(ITraitCollectionModel attributes, IModelIdentifier identifier) {
    IModelCollection modelProvider = EasyMock.createNiceMock(IModelCollection.class);
    EasyMock.expect(modelProvider.getModel(identifier)).andReturn(attributes).anyTimes();
    EasyMock.replay(modelProvider);
    return modelProvider;
  }
}