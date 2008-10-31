package net.sf.anathema.character.freebies.abilities.credit;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

import org.easymock.EasyMock;

public class CreditManagerObjectMother {

  public static ICreditManager create(int favoredCredit, int unrestrictedCredit, ICharacterId characterId) {
    ICreditManager creditManager = EasyMock.createMock(ICreditManager.class);
    new FavoredCreditBehaviour(characterId, favoredCredit).configure(creditManager);
    EasyMock.expect(creditManager.getCredit(characterId, IAbilityFreebiesConstants.UNRESTRICTED_CREDIT)).andReturn(
        unrestrictedCredit).anyTimes();
    EasyMock.replay(creditManager);
    return creditManager;
  }
}