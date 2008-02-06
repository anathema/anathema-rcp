/**
 * 
 */
package net.sf.anathema.character.sheet.pdf;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class FieldIdPredicate implements IPredicate<IExtensionElement> {
  private final String encoderName;

  public FieldIdPredicate(String encoderName) {
    this.encoderName = encoderName;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getAttribute(RegisteredContentEncoderProvider.ATTRIB_FIELD_ID).equals(encoderName);
  }
}