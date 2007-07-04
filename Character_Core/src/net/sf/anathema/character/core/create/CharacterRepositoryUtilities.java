package net.sf.anathema.character.core.create;

import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

public class CharacterRepositoryUtilities {

  public static IItemType getCharacterItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.Character"); //$NON-NLS-1$
  }
}