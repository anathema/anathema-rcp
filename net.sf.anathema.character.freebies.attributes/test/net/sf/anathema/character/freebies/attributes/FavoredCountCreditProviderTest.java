package net.sf.anathema.character.freebies.attributes;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.DummyAttributeTemplate;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FavoredCountCreditProviderTest {

  private FavoredCountCreditProvider favoredCountCreditProvider;

  @Before
  public void createProvider() throws Exception {
    ITraitCollectionTemplateProvider provider = EasyMock.createNiceMock(ITraitCollectionTemplateProvider.class);
    EasyMock.expect(provider.getTraitTemplate("myTemplate")).andReturn(new DummyAttributeTemplate(2)); //$NON-NLS-1$
    EasyMock.expect(provider.getTraitTemplate("yourTemplate")).andReturn(new DummyAttributeTemplate(0)); //$NON-NLS-1$
    EasyMock.replay(provider);
    favoredCountCreditProvider = new FavoredCountCreditProvider(provider);
  }

  @Test
  public void returnsNumberOfFavoredAttributePicksAsCreditForDefinedTemplate() throws Exception {
    assertThat(favoredCountCreditProvider.getCredit("myTemplate"), is(2)); //$NON-NLS-1$
  }

  @Test
  public void returnsNullAsCreditForUndefinedTemplate() throws Exception {
    assertThat(favoredCountCreditProvider.getCredit("yourTemplate"), is(nullValue())); //$NON-NLS-1$
  }
}