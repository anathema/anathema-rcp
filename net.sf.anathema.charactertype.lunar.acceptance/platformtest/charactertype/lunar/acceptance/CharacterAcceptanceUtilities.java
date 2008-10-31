package charactertype.lunar.acceptance;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

import org.eclipse.core.resources.IFolder;

public class CharacterAcceptanceUtilities {

  public static final ITraitCollectionModel getAbilitiesModel(IFolder folder) {
    CharacterId characterId = new CharacterId(folder);
    ModelIdentifier abilitiesIdentifier = new ModelIdentifier(characterId, IAbilitiesPluginConstants.MODEL_ID);
    ModelCache modelCache = ModelCache.getInstance();
    return (ITraitCollectionModel) modelCache.getModel(abilitiesIdentifier);
  }

}