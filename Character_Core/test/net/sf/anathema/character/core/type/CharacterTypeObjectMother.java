package net.sf.anathema.character.core.type;

import org.easymock.EasyMock;

public class CharacterTypeObjectMother {

  public static ICharacterType createCharacterType(String typeId) {
    ICharacterType characterType = EasyMock.createMock(ICharacterType.class);
    EasyMock.expect(characterType.getId()).andReturn(typeId).anyTimes();
    EasyMock.replay(characterType);
    return characterType;
  }

  public static ICharacterTypeProvider createProviderWithType(ICharacterType type) {
    ICharacterTypeProvider typeProvider = EasyMock.createMock(ICharacterTypeProvider.class);
    EasyMock.expect(typeProvider.getCharacterTypeById(type.getId())).andReturn(type);
    EasyMock.replay(typeProvider);
    return typeProvider;
  }
}