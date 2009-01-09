package net.sf.anathema.character.sheet.pdf;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class FieldIdPredicate implements IPredicate<IExtensionElement> {
  private static final String ATTRIB_FIELD_ID = "fieldId"; //$NON-NLS-1$
  private final String encoderName;

  public FieldIdPredicate(String encoderName) {
    this.encoderName = encoderName;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getAttribute(ATTRIB_FIELD_ID).equals(encoderName);
  }
}