package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.*;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.fake.DummyCaste;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.trait.model.NullTraitModelIdProvider;

import org.easymock.EasyMock;
import org.junit.Test;

public class CasteModelFactoryTest {

  private static final String CHARACTER_TYPE_ID = "my.id"; //$NON-NLS-1$

  @Test
  public void createdTemplateReturnsIdsAsOptions() throws Exception {
    DummyCaste dummyCaste = new DummyCaste("TollerHengst"); //$NON-NLS-1$
    ICaste[] castes = new ICaste[] { dummyCaste };
    ICasteCollection provider = createCasteProvider(castes);
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(template.getCharacterTypeId()).andReturn(CHARACTER_TYPE_ID).anyTimes();
    EasyMock.replay(template);
    CasteTemplate casteTemplate = new CasteModelFactory(provider, new NullTraitModelIdProvider()).createModelTemplate(template);
    assertArrayEquals(new ICaste[] { dummyCaste }, new CasteModel(casteTemplate).getOptions());
  }

  private ICasteCollection createCasteProvider(ICaste[] castes) {
    ICasteCollection provider = EasyMock.createMock(ICasteCollection.class);
    EasyMock.expect(provider.getCastes(CHARACTER_TYPE_ID)).andStubReturn(castes);
    EasyMock.replay(provider);
    return provider;
  }
}