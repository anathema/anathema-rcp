package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.content.IContentChecker;
import net.sf.anathema.character.core.model.content.IModelContentCheck;
import net.sf.anathema.character.trait.IBasicTrait;

public class WhereModelContent implements IWhere {

  private final String definition;
  private final IContentChecker contentChecker;

  public WhereModelContent(String definition, IContentChecker contentChecker) {
    this.definition = definition;
    this.contentChecker = contentChecker;
  }

  @Override
  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    final IModelContentCheck contentCheck = contentChecker.getCheck(definition);
    if (contentCheck == null) {
      return false;
    }
    return contentCheck.evaluate(container, contentChecker.getContentValue(definition));
  }
}