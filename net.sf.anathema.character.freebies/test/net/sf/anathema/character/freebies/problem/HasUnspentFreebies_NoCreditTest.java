package net.sf.anathema.character.freebies.problem;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.freebies.fake.CreditManagerObjectMother;

import org.junit.Before;
import org.junit.Test;

public class HasUnspentFreebies_NoCreditTest {

  private static final String CREDIT_ID = "this.credit"; //$NON-NLS-1$
  private static final int CREDIT = 0;
  private IFreebiesHandler freebiesHandler;
  private HasUnspentFreebies hasUnspentFreebies;
  private ICharacterId characterId;
  private IModelIdentifier identifier;

  @Before
  public void createPredicate() {
    characterId = EasyMockHelper.createMockAndReplay(ICharacterId.class);
    this.identifier = new ModelIdentifier(characterId, null);
    ICreditManager creditManager = CreditManagerObjectMother.createCreditManager(characterId, CREDIT_ID, CREDIT);
    freebiesHandler = createMock(IFreebiesHandler.class);
    expect(freebiesHandler.getCreditId()).andStubReturn(CREDIT_ID);
    hasUnspentFreebies = new HasUnspentFreebies(creditManager, freebiesHandler);
  }

  @Test
  public void hasNoUnspentPointIfSpentFreebiesExceedCredit() throws Exception {
    expect(freebiesHandler.getPoints(characterId, CREDIT)).andReturn(CREDIT - 1);
    replay(freebiesHandler);
    assertThat(hasUnspentFreebies.evaluate(identifier), is(false));
  }
}