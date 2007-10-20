package net.sf.anathema.character.points.problems;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.experience.IExperience;

public class BonusPointsProvider implements IBonusPointProvider {

  private final IModelCollection modelCollection;

  public BonusPointsProvider(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }
  
  @Override
  public int getAvailableBonusPoints(ICharacterId characterId) {
    IExperience experience = getExperienceModel(characterId);
    if (experience.isExperienced()) {
      return 0;
    }
//    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterId);
//    String templateId = template.getId();
//    int bonusPointCredit = new BonusPointExtensionPoint().getBonusPoints(templateId);
    return -1;
  }

  private IExperience getExperienceModel(ICharacterId characterId) {
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, IExperience.MODEL_ID);
    return (IExperience) modelCollection.getModel(modelIdentifier);
  }
}