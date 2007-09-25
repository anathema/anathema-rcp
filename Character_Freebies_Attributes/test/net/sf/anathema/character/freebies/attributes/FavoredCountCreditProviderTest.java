package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.IAttributeTemplateProvider;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FavoredCountCreditProviderTest {

  private FavoredCountCreditProvider favoredCountCreditProvider;

  @Before
  public void createProvider() throws Exception {
    IAttributeTemplateProvider provider = EasyMock.createNiceMock(IAttributeTemplateProvider.class);
    EasyMock.expect(provider.getAttributeTemplate("myTemplate")).andReturn(new AttributeTemplate(2)); //$NON-NLS-1$
    EasyMock.expect(provider.getAttributeTemplate("yourTemplate")).andReturn(new AttributeTemplate(0)); //$NON-NLS-1$
    EasyMock.replay(provider);
    favoredCountCreditProvider = new FavoredCountCreditProvider(provider);
  }

  @Test
  public void returnsNumberOfFavoredAttributePicksAsCreditForDefinedTemplate() throws Exception {
    assertEquals(2, favoredCountCreditProvider.getCredit("myTemplate")); //$NON-NLS-1$
  }

  @Test
  public void returnsNullAsCreditForUndefinedTemplate() throws Exception {
    assertNull(favoredCountCreditProvider.getCredit("yourTemplate")); //$NON-NLS-1$
  }
}