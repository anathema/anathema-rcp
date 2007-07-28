package net.sf.anathema.character.points;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.internal.IPointConfigurationProvider;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

public class PointViewInputFactory {

  private final IPointConfigurationProvider configurationProvider;
  private final ICharacterTemplateProvider templateProvider;

  public PointViewInputFactory(IPointConfigurationProvider provider, ICharacterTemplateProvider templateProvider) {
    this.configurationProvider = provider;
    this.templateProvider = templateProvider;
  }

  public IPointViewInput create(ICharacterId characterId, boolean experienced) {
    if (experienced) {
      return new PointViewInput(characterId, configurationProvider.getExperiencePointConfigurations(
          templateProvider,
          characterId));
    }
    return new PointViewInput(characterId, configurationProvider.getBonusPointConfigurations(
        templateProvider,
        characterId));
  }
}