package net.sf.anathema.charms.character.special;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.charms.tree.ICharmId;

public interface IVirtualCharms {

  public boolean isVirtual(final String pattern);

  public boolean isVirtual(ICharmId charmId);

  public boolean isFullfilled(ICharmId virtualCharmId, ICharacter character);
}