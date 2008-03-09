package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.model.ITraitTemplateFactory;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public final class DummyTemplateFactory implements ITraitTemplateFactory {
  private final ITraitTemplate traitTemplate;

  public DummyTemplateFactory() {
    this(new EssenceSensitiveTraitTemplate());
  }

  public DummyTemplateFactory(ITraitTemplate traitTemplate) {
    this.traitTemplate = traitTemplate;
  }

  @Override
  public ITraitTemplate getTraitTemplate(String traitId) {
    return traitTemplate;
  }
}