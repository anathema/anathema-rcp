package net.sf.anathema.character.freebies.abilities;

import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

public class BasicTraitObjectMother {

  public static BasicTrait createFavored(String id) {
    BasicTrait basicTrait = new BasicTrait(new Identificate(id));
    basicTrait.getStatusManager().setStatus(new FavoredStatus());
    return basicTrait;
  }

}
