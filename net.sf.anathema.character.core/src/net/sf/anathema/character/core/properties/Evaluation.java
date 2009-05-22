package net.sf.anathema.character.core.properties;

import static java.text.MessageFormat.*;
import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.properties.evaluation.HasProperty;

@SuppressWarnings("nls")
public class Evaluation {

  private static final String ATTRIB_VALUE = "value";
  private static final String ATTRIB_ID = "id";
  private final EclipseExtensionPoint extensionPoint;

  public Evaluation() {
    this.extensionPoint = new EclipseExtensionPoint(CharacterCorePlugin.ID, "properties");
  }

  public IPredicate<ICharacter> create(IExtensionElement evaluationElement) {
    IExtensionElement propertyElement = evaluationElement.getElements()[0];
    return readProperty(propertyElement);
  }

  private IPredicate<ICharacter> readProperty(IExtensionElement propertyElement) {
    String propertyId = propertyElement.getAttribute(ATTRIB_ID);
    IProperty property = getProperty(propertyId);
    Ensure.ensureNotNull(format("No property definition found for id {0}.", propertyId), property);
    String propertyValue = propertyElement.getAttribute(ATTRIB_VALUE);
    return new HasProperty(property, propertyValue);
  }

  private IProperty getProperty(String propertyId) {
    IPredicate<IExtensionElement> predicate = AttributePredicate.FromNameAndValue(ATTRIB_ID, propertyId);
    return new ClassConveyerBelt<IProperty>(extensionPoint, IProperty.class, predicate).getFirstObject();
  }
}