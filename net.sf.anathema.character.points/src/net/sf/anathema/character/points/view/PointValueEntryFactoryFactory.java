package net.sf.anathema.character.points.view;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;
import net.sf.anathema.character.points.view.entry.BankedXpConfiguration;
import net.sf.anathema.character.points.view.entry.LifetimeXpConfiguration;

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
      List<IPointConfiguration> configured = configurationProvider.getExperiencePointConfigurations(template);
      IModelCollection modelCollection = ModelCache.getInstance();
      return getDisplayExperienceConfigurations(modelCollection, configured);
    }
    return configurationProvider.getBonusPointConfigurations(template);
  }

  private Iterable<IPointConfiguration> getDisplayExperienceConfigurations(
      IModelCollection modelCollection,
      List<IPointConfiguration> configured) {
    List<IPointConfiguration> displayConfigurations = new ArrayList<IPointConfiguration>();
    displayConfigurations.add(new LifetimeXpConfiguration(modelCollection));
    displayConfigurations.add(new BankedXpConfiguration(modelCollection, configured));
    displayConfigurations.addAll(configured);
    return displayConfigurations;
  }
}