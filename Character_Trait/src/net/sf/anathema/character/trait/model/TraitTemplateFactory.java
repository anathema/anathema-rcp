package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class TraitTemplateFactory implements ITraitTemplateFactory {

  private final int minimalValue;

  public TraitTemplateFactory(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  @Override
  public ITraitTemplate getTraitTemplate(String traitId) {
    EssenceSensitiveTraitTemplate traitTemplate = new EssenceSensitiveTraitTemplate();
    traitTemplate.setMiniumalValue(minimalValue);
    return traitTemplate;
  }
}