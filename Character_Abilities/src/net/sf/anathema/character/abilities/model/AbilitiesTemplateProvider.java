package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;


public class AbilitiesTemplateProvider {

  public ITraitCollectionTemplate getAttributeTemplate(String characterTemplateId) {
    return new AbilitiesTemplate(0);
  }
}