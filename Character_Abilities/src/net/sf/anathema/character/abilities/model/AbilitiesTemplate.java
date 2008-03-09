package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class AbilitiesTemplate extends AbilitiesGroupTemplate implements IModelTemplate, ITraitCollectionTemplate {

  private final int favorizationCount;
  private final AbilityTemplateFactory templateFactory = new AbilityTemplateFactory();

  public AbilitiesTemplate(int favorizationCount, ICharacterTemplate characterTemplate) {
    super(characterTemplate);
    this.favorizationCount = favorizationCount;
  }

  public int getFavorizationCount() {
    return favorizationCount;
  }

  @Override
  public ITraitTemplate getTraitTemplate() {
    return templateFactory.getTraitTemplate();
  }
}