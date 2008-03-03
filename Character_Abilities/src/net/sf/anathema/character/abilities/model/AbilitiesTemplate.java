package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AbilitiesTemplate extends AbilitiesGroupConfiguration implements IModelTemplate, ITraitCollectionTemplate {

  private final int favorizationCount;

  public AbilitiesTemplate(int favorizationCount, ICharacterTemplate characterTemplate) {
    super(characterTemplate);
    this.favorizationCount = favorizationCount;
  }

  public int getFavorizationCount() {
    return favorizationCount;
  }
}