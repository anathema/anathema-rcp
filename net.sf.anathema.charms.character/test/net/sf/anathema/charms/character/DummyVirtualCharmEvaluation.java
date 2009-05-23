package net.sf.anathema.charms.character;

import net.sf.anathema.charms.character.model.IVirtualCharmEvaluation;
import net.sf.anathema.charms.tree.ICharmId;

public class DummyVirtualCharmEvaluation implements IVirtualCharmEvaluation {

  @Override
  public boolean isVirtual(String pattern) {
    return false;
  }

  @Override
  public boolean isVirtual(ICharmId charmId) {
    return false;
  }
}