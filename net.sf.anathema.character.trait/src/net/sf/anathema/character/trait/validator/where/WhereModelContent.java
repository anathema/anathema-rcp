package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.model.content.IContentChecker;
import net.sf.anathema.character.core.model.content.IModelContentCheck;

public class WhereModelContent implements IWhere {

  private final String definition;
  private final IContentChecker contentChecker;

  public WhereModelContent(String definition, IContentChecker contentChecker) {
    this.definition = definition;
    this.contentChecker = contentChecker;
  }

  @Override
  public boolean evaluate(ValidationDto validationObject) {
    final IModelContentCheck contentCheck = contentChecker.getCheck(definition);
    if (contentCheck == null) {
      return false;
    }
    return contentCheck.evaluate(validationObject.container, contentChecker.getContentValue(definition));
  }
}