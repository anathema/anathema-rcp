package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AbilitiesTemplateProvider {

  public ITraitCollectionTemplate getAttributeTemplate(ICharacterTemplate template) {
    return new AbilitiesTemplate(0, template);
  }
}