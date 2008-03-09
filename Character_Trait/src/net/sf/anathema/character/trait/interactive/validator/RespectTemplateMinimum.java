package net.sf.anathema.character.trait.interactive.validator;

import net.sf.anathema.character.trait.template.ITraitTemplate;

public class RespectTemplateMinimum implements IValidator {

  private final ITraitTemplate traitTemplate;

  public RespectTemplateMinimum(ITraitTemplate traitTemplate) {
    this.traitTemplate = traitTemplate;
  }

  @Override
  public int getValidValue(int value) {
    return Math.max(value, traitTemplate.getMinimalValue());
  }
}