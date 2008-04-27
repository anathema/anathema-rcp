package net.sf.anathema.character.trait.model;

import net.sf.anathema.character.trait.template.ITraitTemplate;

public final class StaticExportTemplateFactory implements ITraitTemplateFactory {
  @Override
  public ITraitTemplate getTraitTemplate(String traitId) {
    return new ITraitTemplate() {

      @Override
      public int getMinimalValue() {
        return 0;
      }
    };
  }
}