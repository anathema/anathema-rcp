package net.sf.anathema.character.points;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.internal.IPointConfigurationProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

public class PointViewInputFactory {

  private IPointConfigurationProvider configurationProvider;
  private final CharacterTemplateProvider templateProvider = new CharacterTemplateProvider();

  public PointViewInputFactory(IPointConfigurationProvider provider) {
    this.configurationProvider = provider;
  }

  public IPointViewInput create(ICharacterId characterId) {
    return new PointViewInput(characterId, configurationProvider.getExperiencePointConfigurations(
        templateProvider,
        characterId));
  }
}