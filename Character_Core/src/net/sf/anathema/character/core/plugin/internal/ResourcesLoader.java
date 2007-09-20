package net.sf.anathema.character.core.plugin.internal;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public class ResourcesLoader {

  public void load(IPluginExtension... extensions) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        String referenceId = element.getAttribute("referenceId"); //$NON-NLS-1$
        URL resource = element.getResourceAttribute("resourceUrl"); //$NON-NLS-1$
        Image image = ImageDescriptor.createFromURL(resource).createImage();
        ICharacterCorePluginConstants.IMAGE_REGISTRY.put(referenceId, image);
      }
    }
  }
}