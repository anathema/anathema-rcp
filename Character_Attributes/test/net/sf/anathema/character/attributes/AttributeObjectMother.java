package net.sf.anathema.character.attributes;

import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;

import org.easymock.EasyMock;

public class AttributeObjectMother {

  public static IModelProvider createModelProvider(IAttributes attributes) {
    IModelIdentifier modelIdentifier = EasyMock.isA(IModelIdentifier.class);
    return createModelProvider(attributes, modelIdentifier);
  }

  public static IModelProvider createModelProvider(IAttributes attributes, ICharacterId characterId) {
    return createModelProvider(attributes, new ModelIdentifier(characterId, IAttributes.MODEL_ID));
  }

  private static IModelProvider createModelProvider(IAttributes attributes, IModelIdentifier identifier) {
    IModelProvider modelProvider = EasyMock.createNiceMock(IModelProvider.class);
    EasyMock.expect(modelProvider.getModel(identifier)).andReturn(attributes).anyTimes();
    EasyMock.replay(modelProvider);
    return modelProvider;
  }
}