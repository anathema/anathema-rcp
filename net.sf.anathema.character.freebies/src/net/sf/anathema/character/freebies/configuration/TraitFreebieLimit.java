package net.sf.anathema.character.freebies.configuration;

import net.sf.anathema.character.core.character.ICharacterId;

public class TraitFreebieLimit implements ITraitFreebieLimit {

  @Override
  public int getFor(ICharacterId id) {
    return 3;
  }
}