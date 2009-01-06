package net.sf.anathema.charms.character.model;

import java.util.Collection;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.charms.extension.CharmProvidingExtensionPoint;

public class GenericCharmCollector {

  public Collection<String> collect(ICharacter character) {
    String typeId = character.getCharacterType().getId();
    return CharmProvidingExtensionPoint.CreateTreeProvider().getGenericCharms(typeId);
  }
}