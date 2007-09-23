package net.sf.anathema.basics.eclipse.extension.fake;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.lib.collection.MultiEntryMap;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.resource.ImageDescriptor;

public class FakeExtensionElement implements IExtensionElement {
  private final Map<String, Object> attributes = new HashMap<String, Object>();
  private final MultiEntryMap<String, IExtensionElement> children = new MultiEntryMap<String, IExtensionElement>();

  @Override
  public String getAttribute(String name) {
    return String.valueOf(attributes.get(name));
  }

  @Override
  public <K extends IExecutableExtension> K getAttributeAsObject(String name, Class<K> clazz) throws ExtensionException {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public ImageDescriptor createImageDescriptorFromAttribute(String name) {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public boolean getBooleanAttribute(String name) {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public IExtensionElement getElement(String name) {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public URL getResourceAttribute(String attributeName) {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public IExtensionElement[] getElements() {
    List<IExtensionElement> elements = new ArrayList<IExtensionElement>();
    for (String key : children.keySet()) {
      elements.addAll(children.get(key));
    }
    return elements.toArray(new IExtensionElement[elements.size()]);
  }

  @Override
  public int getIntegerAttribute(String name) {
    return Integer.valueOf(attributes.get(name).toString());
  }

  @Override
  public String getName() {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  public void addAttribute(String name, Object value) {
    attributes.put(name, value);
  }

  public void addElement(String tag, IExtensionElement element) {
    children.add(tag, element);
  }

  @Override
  public boolean hasAttribute(String attributeName) {
    return true;
  }
}