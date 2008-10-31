package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public class WhereTraitId implements IWhere {

  private final String expectedId;

  public WhereTraitId(String expectedId) {
    this.expectedId = expectedId;
  }

  @Override
  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    return expectedId.equals(trait.getTraitType().getId());
  }
}