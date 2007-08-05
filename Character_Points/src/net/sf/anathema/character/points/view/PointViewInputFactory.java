package net.sf.anathema.character.points.view;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;

public class PointViewInputFactory {

  private final IPointConfigurationProvider configurationProvider;
  private final ICharacterTemplateProvider templateProvider;

  public PointViewInputFactory(IPointConfigurationProvider provider, ICharacterTemplateProvider templateProvider) {
    this.configurationProvider = provider;
    this.templateProvider = templateProvider;
  }

  public ICharacterValueEntryFactory create(ICharacterId characterId, boolean experienced) {
    return new PointViewInput(characterId, getPointConfigurations(characterId, experienced));
  }

  private IPointConfiguration[] getPointConfigurations(ICharacterId characterId, boolean experienced) {
    if (experienced) {
      return configurationProvider.getExperiencePointConfigurations(templateProvider, characterId);
    }
    return configurationProvider.getBonusPointConfigurations(templateProvider, characterId);
  }
}