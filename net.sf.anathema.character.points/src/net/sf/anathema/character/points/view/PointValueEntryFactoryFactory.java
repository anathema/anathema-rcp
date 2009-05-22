package net.sf.anathema.character.points.view;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.points.calculation.ExperienceCharacterFactory;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;
import net.sf.anathema.character.points.view.entry.BankedXpConfiguration;
import net.sf.anathema.character.points.view.entry.LifetimeXpConfiguration;

public class PointValueEntryFactoryFactory {

  private final IPointConfigurationProvider configurationProvider;
  private final ICharacterTemplateProvider templateProvider;
  private final IModelCollection modelCollection;

  public PointValueEntryFactoryFactory(
      IModelCollection modelCollection,
      IPointConfigurationProvider provider,
      ICharacterTemplateProvider templateProvider) {
    this.modelCollection = modelCollection;
    this.configurationProvider = provider;
    this.templateProvider = templateProvider;
  }

  public ICharacterValueEntryFactory create(ICharacterId characterId, boolean experienced) {
    return new PointsValueEntryFactory(characterId, getPointConfigurations(characterId, experienced));
  }

  private Iterable<IPointConfiguration> getPointConfigurations(ICharacterId characterId, boolean experienced) {
    ICharacterTemplate template = templateProvider.getTemplate(characterId);
    if (experienced) {
      List<IPointConfiguration> configured = configurationProvider.getExperiencePointConfigurations(template);
      return getDisplayExperienceConfigurations(configured);
    }
    return configurationProvider.getBonusPointConfigurations(template);
  }

  private Iterable<IPointConfiguration> getDisplayExperienceConfigurations(List<IPointConfiguration> configured) {
    ExperienceCharacterFactory factory = new ExperienceCharacterFactory(modelCollection, configured);
    List<IPointConfiguration> displayConfigurations = new ArrayList<IPointConfiguration>();
    displayConfigurations.add(new LifetimeXpConfiguration(factory));
    displayConfigurations.add(new BankedXpConfiguration(factory));
    displayConfigurations.addAll(configured);
    return displayConfigurations;
  }
}