package character.acceptance;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.points.configuration.PointEvaluation;

import org.eclipse.core.resources.IFolder;

public class AcceptanceCharacter {

  public static AcceptanceCharacter FromFolderNameAndTemplateId(final String folderName, final String templateId) {
    return new AcceptanceCharacter(folderName, templateId);
  }

  private final IFolder folder;
  private final CharacterId characterId;

  private AcceptanceCharacter(final String folderName, final String templateId) {
    folder = new CharacterFactory().createNewCharacter(templateId, folderName);
    Ensure.ensureNotNull(folder);
    characterId = new CharacterId(folder);
  }

  public void clear() throws Exception {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
  }

  public final IModel getModel(final String modelId) {
    return ModelCache.getInstance().getModel(new ModelIdentifier(characterId, modelId));
  }

  public final int getCredit(final String creditId) {
    return new CreditManager().getCredit(characterId, creditId);
  }

  public IFolder getFolder() {
    return folder;
  }

  public ICharacterTemplate getTemplate() {
    return new CharacterTemplateProvider().getTemplate(characterId);
  }

  public InteractionTraitList createTraitInteraction(final String modelId) throws Exception {
    return new InteractionTraitList(TraitInteractionUtilties.createDisplayGroups(getFolder(), modelId));
  }

  public int getBonusPoints(final String configurationName) throws Exception {
    final PointEvaluation evaluation = PointEvaluation.FromExtensionPoints();
    return evaluation.getSpentBonusPoints(characterId, configurationName);
  }

  public int getXpSpent(final String configurationName) throws Exception {
    final PointEvaluation evaluation = PointEvaluation.FromExtensionPoints();
    return evaluation.getSpentXp(characterId, configurationName);
  }

  public void setExperienced() {
    final IExperience experience = (IExperience) getModel(IExperience.MODEL_ID);
    experience.setExperienced(true);
  }

  public void setCreation() {
    final IExperience experience = (IExperience) getModel(IExperience.MODEL_ID);
    experience.setExperienced(false);
  }
}