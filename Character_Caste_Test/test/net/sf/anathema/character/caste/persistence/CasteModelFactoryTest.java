package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.*;
import net.sf.anathema.character.caste.CasteObjectMother;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.model.ICaste;
import net.sf.anathema.character.core.character.ICharacterTemplate;

import org.easymock.EasyMock;
import org.junit.Test;

public class CasteModelFactoryTest {

  private static final String CHARACTER_TYPE_ID = "my.id"; //$NON-NLS-1$

  @Test
  public void createdTemplateReturnsIdsAsOptions() throws Exception {
    ICaste[] castes = new ICaste[] { CasteObjectMother.createCaste("TollerHengst", "Toller Hengst") }; //$NON-NLS-1$ //$NON-NLS-2$
    ICasteProvider provider = createCasteProvider(castes);
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(template.getCharacterTypeId()).andReturn(CHARACTER_TYPE_ID).anyTimes();
    EasyMock.replay(template);
    CasteTemplate casteTemplate = new CasteModelFactory(provider).createModelTemplate(template);
    assertArrayEquals(new String[] { "Toller Hengst" }, new CasteModel(casteTemplate).getPrintNameOptions()); //$NON-NLS-1$
  }

  private ICasteProvider createCasteProvider(ICaste[] castes) {
    ICasteProvider provider = EasyMock.createMock(ICasteProvider.class);
    EasyMock.expect(provider.getCastes(CHARACTER_TYPE_ID)).andStubReturn(castes);
    EasyMock.replay(provider);
    return provider;
  }
}