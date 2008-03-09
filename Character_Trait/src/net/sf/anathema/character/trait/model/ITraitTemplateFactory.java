package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.trait.template.ITraitTemplate;

public interface ITraitTemplateFactory {

  public ITraitTemplate getTraitTemplate(String traitId);
}