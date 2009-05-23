package net.sf.anathema.charms.character;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.charms.character.model.IVirtualCharms;
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
  public boolean isLearnedVirtualCharm(ICharmId charmId, ICharacter character) {
    return false;
  }
}