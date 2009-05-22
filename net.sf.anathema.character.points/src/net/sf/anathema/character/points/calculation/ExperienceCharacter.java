package net.sf.anathema.character.points.calculation;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.experience.LifetimeXpCalculator;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;

public class ExperienceCharacter implements IExperienceCharacter {

  private final IModelContainer modelContainer;
  private final Iterable<IPointConfiguration> configurations;
  private final ICharacterId characterId;

  public static IExperienceCharacter CreateFromPlatform(ICharacterId id) {
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(id);
    PointConfigurationExtensionPoint extensionPoint = new PointConfigurationExtensionPoint();
    List<IPointConfiguration> configurations = extensionPoint.getExperiencePointConfigurations(template);
    return new ExperienceCharacterFactory(ModelCache.getInstance(), configurations).create(id);
  }

  public ExperienceCharacter(
      IModelContainer modelContainer,
      ICharacterId characterId,
      Iterable<IPointConfiguration> configurations) {
    this.modelContainer = modelContainer;
    this.characterId = characterId;
    this.configurations = configurations;
  }

  public int getLifetimeXp() {
    return new LifetimeXpCalculator(modelContainer).calculate();
  }

  public int getBankedXp() {
    return getLifetimeXp() - getSpentXp();
  }

  private int getSpentXp() {
    int spentPoints = 0;
    for (IPointConfiguration configuration : configurations) {
      spentPoints += configuration.getPoints(characterId);
    }
    return spentPoints;
  }
}