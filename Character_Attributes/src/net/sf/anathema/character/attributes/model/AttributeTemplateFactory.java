package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.model.ITraitTemplateFactory;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class AttributeTemplateFactory implements ITraitTemplateFactory {

  @Override
  public ITraitTemplate getTraitTemplate() {
    EssenceSensitiveTraitTemplate traitTemplate = new EssenceSensitiveTraitTemplate();
    traitTemplate.setMiniumalValue(1);
    return traitTemplate;
  }
}