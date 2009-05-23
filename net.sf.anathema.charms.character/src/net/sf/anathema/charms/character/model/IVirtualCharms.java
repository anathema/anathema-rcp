package net.sf.anathema.charms.character.model;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.charms.tree.ICharmId;

public interface IVirtualCharms {

  public boolean isVirtual(final String pattern);

  public boolean isVirtual(ICharmId charmId);

  public boolean isLearnedVirtualCharm(ICharmId charmId, ICharacter character);
}