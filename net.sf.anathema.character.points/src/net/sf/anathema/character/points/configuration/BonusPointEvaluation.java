package net.sf.anathema.character.points.configuration;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;

public class BonusPointEvaluation {

  private final ICharacterTemplateProvider templateProvider;
  private final IPointConfigurationProvider configurationProvider;

  public static BonusPointEvaluation FromExtensionPoints() {
    return new BonusPointEvaluation(new CharacterTemplateProvider(), new PointConfigurationExtensionPoint());
  }

  public BonusPointEvaluation(
      ICharacterTemplateProvider templateProvider,
      IPointConfigurationProvider configurationProvider) {
    this.templateProvider = templateProvider;
    this.configurationProvider = configurationProvider;
  }

  public int getSpentBonusPoints(ICharacterId characterId, String configurationName) throws Exception {
    ICharacterTemplate template = templateProvider.getTemplate(characterId);
    for (IPointConfiguration configuration : configurationProvider.getBonusPointConfigurations(template)) {
      String currentName = configuration.getName();
      if (currentName.equals(configurationName)) {
        return configuration.getPoints(characterId);
      }
    }
    return 0;
  }
}
