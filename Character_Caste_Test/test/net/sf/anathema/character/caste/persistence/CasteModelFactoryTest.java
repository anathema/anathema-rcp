package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;
import net.sf.anathema.character.caste.model.Caste;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.model.ICaste;
import net.sf.anathema.character.core.character.ICharacterTemplate;

import org.easymock.EasyMock;
import org.junit.Test;

public class CasteModelFactoryTest {

  private static final String CHARACTER_TYPE_ID = "my.id"; //$NON-NLS-1$

  @Test
  public void createdTemplateReturnsIdsAsOptions() throws Exception {
    ICaste[] castes = new ICaste[] {createCaste("Toller Hengst")}; //$NON-NLS-1$
    ICasteProvider provider = createCasteProvider(castes);
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(template.getCharacterTypeId()).andReturn(CHARACTER_TYPE_ID).anyTimes();
    EasyMock.replay(template);
    CasteTemplate casteTemplate = new CasteModelFactory(provider).createModelTemplate(template);
    assertArrayEquals(new String[] {"Toller Hengst"}, casteTemplate.getCastes()); //$NON-NLS-1$
  }

  private ICasteProvider createCasteProvider(ICaste[] castes) {
    ICasteProvider provider = EasyMock.createMock(ICasteProvider.class);
    EasyMock.expect(provider.getCastes(CHARACTER_TYPE_ID)).andStubReturn(castes);
    EasyMock.replay(provider);
    return provider;
  }

  private ICaste createCaste(String id) throws ExtensionException {
    MockStringAttribute idAttribute = new MockStringAttribute("casteId", id); //$NON-NLS-1$
    return new Caste(ExtensionObjectMother.createExtensionElementWithAttributes(idAttribute));
  }
}