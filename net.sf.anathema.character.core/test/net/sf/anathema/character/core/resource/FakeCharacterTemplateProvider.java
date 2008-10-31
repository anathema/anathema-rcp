package net.sf.anathema.character.core.resource;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.template.CharacterTemplate;

public final class FakeCharacterTemplateProvider implements ICharacterTemplateProvider {
  @Override
  public ICharacterTemplate getTemplate(ICharacterId characterId) {
    return new CharacterTemplate(null, "Test", null); //$NON-NLS-1$
  }

  @Override
  public ICharacterTemplate getTemplate(String templateId) {
    return null;
  }

  @Override
  public boolean isTemplateAvailable(ICharacterId characterId) {
    return false;
  }
}