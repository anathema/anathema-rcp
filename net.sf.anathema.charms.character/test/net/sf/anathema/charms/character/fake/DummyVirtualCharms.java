package net.sf.anathema.charms.character.fake;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.charms.character.special.IVirtualCharms;
import net.sf.anathema.charms.tree.ICharmId;

public class DummyVirtualCharms implements IVirtualCharms {

  public final List<String> virtualPaterns = new ArrayList<String>();

  @Override
  public boolean isVirtual(String pattern) {
    return virtualPaterns.contains(pattern);
  }

  @Override
  public boolean isVirtual(ICharmId charmId) {
    return isVirtual(charmId.getIdPattern());
  }

  @Override
  public boolean isFullfilled(ICharmId virtualCharmId, ICharacter character) {
    return false;
  }
}