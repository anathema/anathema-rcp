package net.sf.anathema.character.core.properties.concrete;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.properties.IProperty;
import net.sf.anathema.character.core.properties.IPropertyMap;

public class PropertiesExtensionPoint implements IPropertyMap {
  private static final String EXTENSION_POINT = "properties"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private final EclipseExtensionPoint extensionPoint;

  public PropertiesExtensionPoint() {
    this.extensionPoint = new EclipseExtensionPoint(CharacterCorePlugin.ID, EXTENSION_POINT);
  }

  @Override
  public IProperty getProperty(String propertyId) {
    IPredicate<IExtensionElement> predicate = AttributePredicate.FromNameAndValue(ATTRIB_ID, propertyId);
    return new ClassConveyerBelt<IProperty>(extensionPoint, IProperty.class, predicate).getFirstObject();
  }
}