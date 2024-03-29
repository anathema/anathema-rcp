package net.sf.anathema.character.core.type;

import static net.sf.anathema.character.core.type.CharacterTypeObjectMother.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.ICharacterTypeFinder;
import net.sf.anathema.character.core.fake.TemplateProviderObjectMother;
import net.sf.anathema.character.core.template.CharacterTemplate;

import org.easymock.EasyMock;
import org.junit.Test;

public class CharacterTypeFinderTest {

  @Test
  public void findCorrectCharacterType() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    ICharacterTemplate template = new CharacterTemplate("templateId", null, "typeId"); //$NON-NLS-1$ //$NON-NLS-2$
    ICharacterTemplateProvider templateProvider = TemplateProviderObjectMother.createTemplateProvider(
        characterId,
        template);
    ICharacterType characterType = createCharacterType("typeId"); //$NON-NLS-1$
    ICharacterTypeCollection typeProvider = createProviderWithType(characterType);
    ICharacterTypeFinder typeFinder = new CharacterTypeFinder(templateProvider, typeProvider);
    assertSame(characterType, typeFinder.getCharacterType(characterId));
  }
}