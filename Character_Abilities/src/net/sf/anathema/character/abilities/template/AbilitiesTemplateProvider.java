package net.sf.anathema.character.abilities.template;

import net.sf.anathema.character.abilities.model.AbilitiesTemplate;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AbilitiesTemplateProvider implements ITraitCollectionTemplateProvider {

  @Override
  public ITraitCollectionTemplate getTraitTemplate(String characterTemplateId) {
    // TODO: Case 190: Richtige Unterstützung des FavoredCount
    return new AbilitiesTemplate(0, new CharacterTemplateProvider().getTemplate(characterTemplateId));
  }
}