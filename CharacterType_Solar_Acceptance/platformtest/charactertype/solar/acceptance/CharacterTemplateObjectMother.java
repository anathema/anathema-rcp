package charactertype.solar.acceptance;

import net.sf.anathema.character.core.character.ICharacterTemplate;

import org.easymock.EasyMock;

public class CharacterTemplateObjectMother {

  public static ICharacterTemplate createForCharacterType(String characterType) {
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(template.getCharacterTypeId()).andReturn(characterType).anyTimes();
    EasyMock.replay(template);
    return template;
  }

  public static ICharacterTemplate createSolarTemplate() {
    return createForCharacterType("net.sf.anathema.character.type.solar"); //$NON-NLS-1$
  }
}