package net.sf.anathema.charms.character.fake;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.charms.character.special.IVirtualCharms;
import net.sf.anathema.charms.tree.ICharmId;

public class DummyVirtualCharms implements IVirtualCharms {

  @Override
  public boolean isVirtual(String pattern) {
    return false;
  }

  @Override
  public boolean isVirtual(ICharmId charmId) {
    return false;
  }

  @Override
  public boolean isFullfilled(ICharmId virtualCharmId, ICharacter character) {
    return false;
  }
}