package net.sf.anathema.campaign.conflictweb;

import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

public class ConflictWebRepositoryUtilities {

  public static IItemType getItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.ConflictWeb"); //$NON-NLS-1$
  }
}