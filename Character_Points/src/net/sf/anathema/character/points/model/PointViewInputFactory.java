package net.sf.anathema.character.points.model;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IPointConfiguration;
import net.sf.anathema.character.core.model.IPointConfigurationProvider;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

public class PointViewInputFactory {

  private final IPointConfigurationProvider configurationProvider;
  private final ICharacterTemplateProvider templateProvider;

  public PointViewInputFactory(IPointConfigurationProvider provider, ICharacterTemplateProvider templateProvider) {
    this.configurationProvider = provider;
    this.templateProvider = templateProvider;
  }

  public IPointViewInput create(ICharacterId characterId, boolean experienced) {
    return new PointViewInput(characterId, getPointConfigurations(characterId, experienced));
  }

  private IPointConfiguration[] getPointConfigurations(ICharacterId characterId, boolean experienced) {
    if (experienced) {
      return configurationProvider.getExperiencePointConfigurations(templateProvider, characterId);
    }
    return configurationProvider.getBonusPointConfigurations(templateProvider, characterId);
  }
}