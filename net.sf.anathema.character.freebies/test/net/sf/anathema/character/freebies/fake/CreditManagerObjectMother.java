package net.sf.anathema.character.freebies.fake;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

import org.easymock.EasyMock;

public class CreditManagerObjectMother {

  public static ICreditManager createCreditManager(ICharacterId characterId, String creditId, int credit) {
    ICreditManager creditManager = EasyMock.createMock(ICreditManager.class);
    EasyMock.expect(creditManager.getCredit(characterId, creditId)).andReturn(credit).anyTimes();
    EasyMock.replay(creditManager);
    return creditManager;
  }

  public static ICreditManager createNiceManager() {
    ICreditManager creditManager = EasyMock.createNiceMock(ICreditManager.class);
    EasyMock.replay(creditManager);
    return creditManager;
  }
}