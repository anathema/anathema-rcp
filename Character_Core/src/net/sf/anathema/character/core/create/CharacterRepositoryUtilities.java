package net.sf.anathema.character.core.create;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IFolder;

public class CharacterRepositoryUtilities {

  public static IItemType getCharacterItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.Character"); //$NON-NLS-1$
  }

  public static List<ICharacterId> getAllCharacterIds() {
    ICharacterTemplateProvider templateProvider = new CharacterTemplateProvider();
    List<ICharacterId> characterIds = new ArrayList<ICharacterId>();
    for (IFolder folder : RepositoryUtilities.getItemFolders(getCharacterItemType())) {
      CharacterId characterId = new CharacterId(folder);
      if (templateProvider.isTemplateAvailable(characterId)) {
        characterIds.add(characterId);
      }
    }
    return characterIds;
  }
}