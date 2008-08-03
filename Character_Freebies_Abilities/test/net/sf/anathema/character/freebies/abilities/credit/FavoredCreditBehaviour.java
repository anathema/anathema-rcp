package net.sf.anathema.character.freebies.abilities.credit;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.test.behaviour.IMockBehaviour;

import org.easymock.EasyMock;

public class FavoredCreditBehaviour implements IMockBehaviour<ICreditManager> {

  private final ICharacterId id;
  private final int credit;

  public FavoredCreditBehaviour(ICharacterId id, int credit) {
    this.id = id;
    this.credit = credit;
  }

  @Override
  public void configure(ICreditManager object) {
    EasyMock.expect(object.getCredit(id, IAbilityFreebiesConstants.FAVORED_CREDIT)).andReturn(credit).anyTimes();
  }
}