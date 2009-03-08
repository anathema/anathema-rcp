package net.sf.anathema.character.freebies.virtues.internal;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.ICharacterTypeFinder;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.freebies.virtues.VirtueFreebiesConstants;

public class VirtueFreebieLimit implements IVirtueFreebieLimit {

  private final IPluginExtension[] extensions;
  private final ICharacterTypeFinder finder;

  public VirtueFreebieLimit() {
    this (new EclipseExtensionPoint(VirtueFreebiesConstants.PLUGIN_ID, "freebieLimit"), new CharacterTypeFinder());
  }

  public VirtueFreebieLimit(IExtensionPoint extensionPoint, ICharacterTypeFinder finder) {
    this.finder = finder;
    extensions = extensionPoint.getExtensions();
  }

  @Override
  public int getFor(ICharacterId id) {
    ICharacterType type = finder.getCharacterType(id);
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        String typeName = element.getAttribute("characterType");
        if (typeName.equals(type.getId())) {
          return element.getIntegerAttribute("limit");
        }
      }
    }
    return 3;
  }
}