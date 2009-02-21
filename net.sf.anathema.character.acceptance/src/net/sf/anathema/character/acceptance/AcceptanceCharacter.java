package net.sf.anathema.character.acceptance;

import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.freebies.configuration.CreditManager;

import org.eclipse.core.resources.IFolder;

public class AcceptanceCharacter {

  public static AcceptanceCharacter FromFolderNameAndTemplateId(String folderName, String templateId) {
    return new AcceptanceCharacter(folderName, templateId);
  }

  private final IFolder folder;
  private final CharacterId characterId;

  private AcceptanceCharacter(String folderName, String templateId) {
    folder = new CharacterFactory().createNewCharacter(templateId, folderName);
    characterId = new CharacterId(folder);
  }

  public void clear() throws Exception {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
  }

  public final IModel getModel(String modelId) {
    return ModelCache.getInstance().getModel(new ModelIdentifier(characterId, modelId));
  }

  public final int getCredit(String creditId) {
    return new CreditManager().getCredit(characterId, creditId);
  }

  public IFolder getFolder() {
    return folder;
  }

  public ICharacterTemplate getTemplate() {
    return new CharacterTemplateProvider().getTemplate(characterId);
  }

  public InteractionTraitList createTraitInteraction(String modelId) throws Exception {
    return new InteractionTraitList(TraitInteractionUtilties.createDisplayGroups(getFolder(), modelId));
  }
}