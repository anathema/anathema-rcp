package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.*;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplate;

import org.easymock.EasyMock;
import org.junit.Test;

public class CasteModelFactoryTest {

  @Test
  public void createdTemplateReturnsIdsAsOptions() throws Exception {
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(template.getCharacterTypeId()).andReturn("test.type").anyTimes(); //$NON-NLS-1$
    EasyMock.replay(template);
    CasteTemplate casteTemplate = new CasteModelFactory().createModelTemplate(template);
    assertArrayEquals(new String[] {"test.caste"}, casteTemplate.getCastes()); //$NON-NLS-1$
  }
}