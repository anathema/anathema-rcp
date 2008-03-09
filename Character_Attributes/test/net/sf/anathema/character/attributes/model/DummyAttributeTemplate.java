package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class DummyAttributeTemplate extends AttributeGroupTemplate implements IModelTemplate, ITraitCollectionTemplate {

  private final int favorizationCount;

  public DummyAttributeTemplate(int favorizationCount) {
    this.favorizationCount = favorizationCount;
  }

  public int getFavorizationCount() {
    return favorizationCount;
  }

  @Override
  public ITraitTemplate getTraitTemplate(String traitId) {
    EssenceSensitiveTraitTemplate traitTemplate = new EssenceSensitiveTraitTemplate();
    traitTemplate.setMiniumalValue(1);
    return traitTemplate;
  }
}