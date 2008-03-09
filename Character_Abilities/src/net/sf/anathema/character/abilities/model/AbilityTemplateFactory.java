package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.trait.model.ITraitTemplateFactory;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class AbilityTemplateFactory implements ITraitTemplateFactory {

  public ITraitTemplate getTraitTemplate() {
    EssenceSensitiveTraitTemplate traitTemplate = new EssenceSensitiveTraitTemplate();
    traitTemplate.setMiniumalValue(0);
    return traitTemplate;
  }
}