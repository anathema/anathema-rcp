package net.sf.anathema.charms.character.sheet.generic;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.model.MainTraitModelProvider;
import net.sf.anathema.charms.tree.CharmId;

public class GenericDisplayId extends CharmId {

  public GenericDisplayId(ICharacter character, String generic) {
    super(generic, new MainTraitModelProvider().getFor(character.getCharacterType().getId()) + ".forgenerics");
  }
}