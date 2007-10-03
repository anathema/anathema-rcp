package net.sf.anathema.character.core.type;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;
import net.sf.anathema.character.core.type.internal.CharacterTypeExtensionPoint;

public class CharacterTypeFinder implements ICharacterTypeFinder {

  private final ICharacterTemplateProvider templateProvider;
  private final ICharacterTypeCollection typeProvider;

  public CharacterTypeFinder() {
    this(new CharacterTemplateProvider(), new CharacterTypeExtensionPoint());
  }

  public CharacterTypeFinder(ICharacterTemplateProvider templateProvider, ICharacterTypeCollection characterTypeProvider) {
    this.templateProvider = templateProvider;
    this.typeProvider = characterTypeProvider;
  }

  public ICharacterType getCharacterType(ICharacterId characterId) {
    ICharacterTemplate characterTemplate = templateProvider.getTemplate(characterId);
    String characterTypeId = characterTemplate.getCharacterTypeId();
    return typeProvider.getCharacterTypeById(characterTypeId);
  }
}