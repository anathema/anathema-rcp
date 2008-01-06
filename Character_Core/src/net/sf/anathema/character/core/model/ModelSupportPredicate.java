package net.sf.anathema.character.core.model;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterTemplate;

public final class ModelSupportPredicate implements IPredicate<String> {
  private final ICharacterTemplate template;

  public ModelSupportPredicate(ICharacterTemplate template) {
    this.template = template;
  }

  @Override
  public boolean evaluate(String input) {
    return template.supportsModel(input);
  }
}