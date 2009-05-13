package net.sf.anathema.character.points.view;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;
import net.sf.anathema.character.points.view.entry.TotalXpConfiguration;

public class PointValueEntryFactoryFactory {

  private final IPointConfigurationProvider configurationProvider;
  private final ICharacterTemplateProvider templateProvider;

  public PointValueEntryFactoryFactory(IPointConfigurationProvider provider, ICharacterTemplateProvider templateProvider) {
    this.configurationProvider = provider;
    this.templateProvider = templateProvider;
  }

  public ICharacterValueEntryFactory create(ICharacterId characterId, boolean experienced) {
    return new PointsValueEntryFactory(characterId, getPointConfigurations(characterId, experienced));
  }

  private Iterable<IPointConfiguration> getPointConfigurations(ICharacterId characterId, boolean experienced) {
    ICharacterTemplate template = templateProvider.getTemplate(characterId);
    if (experienced) {
      List<IPointConfiguration> configurations = configurationProvider.getExperiencePointConfigurations(template);
      configurations.add(0, new TotalXpConfiguration());
      return configurations;
    }
    return configurationProvider.getBonusPointConfigurations(template);
  }
}