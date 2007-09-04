package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

import org.easymock.EasyMock;
import org.junit.Test;

public class AttributeFavorizationHandlerTest {

  @Test
  public void isNotFavorableForNoCredit() throws Exception {
    assertFalse(createFavorizationHandlerWithCredit(0).isFavorable());
  }

  @Test
  public void isFavorableForPositiveCredit() throws Exception {
    assertTrue(createFavorizationHandlerWithCredit(1).isFavorable());
  }

  private AttributeFavorizationHandler createFavorizationHandlerWithCredit(int credit) {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    ICreditManager creditManager = EasyMock.createMock(ICreditManager.class);
    EasyMock.expect(creditManager.getCredit(characterId, "net.sf.anathema.character.attributes.favored")).andReturn(credit); //$NON-NLS-1$
    EasyMock.replay(creditManager);
    return new AttributeFavorizationHandler(characterId, creditManager);
  }
}