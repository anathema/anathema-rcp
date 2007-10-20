package net.sf.anathema.character.points.problems;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;

public class BonusPointsProvider implements IBonusPointProvider {

  private final IModelCollection modelCollection;
  private ICharacterTemplateProvider templateProvider;
  private IBonusPointContainer bonusPointContainer;
  private final IPointConfigurationProvider pointConfigurationProvider;

  public BonusPointsProvider(
      IModelCollection modelCollection,
      ICharacterTemplateProvider templateProvider,
     IBonusPointContainer bonusPointContainer,
     IPointConfigurationProvider pointConfigurationProvider) {
    this.modelCollection = modelCollection;
    this.templateProvider = templateProvider;
    this.bonusPointContainer = bonusPointContainer;
    this.pointConfigurationProvider = pointConfigurationProvider;
  }

  @Override
  public int getAvailableBonusPoints(ICharacterId characterId) {
    IExperience experience = getExperienceModel(characterId);
    if (experience.isExperienced()) {
      return 0;
    }
    ICharacterTemplate template = templateProvider.getTemplate(characterId);
    int bonusPointCredit = bonusPointContainer.getBonusPoints(template.getId());
    for (IPointConfiguration configuration : pointConfigurationProvider.getBonusPointConfigurations(template)) {
      bonusPointCredit -= configuration.getPoints(characterId);
    }
    return bonusPointCredit;
  }

  private IExperience getExperienceModel(ICharacterId characterId) {
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, IExperience.MODEL_ID);
    return (IExperience) modelCollection.getModel(modelIdentifier);
  }
}