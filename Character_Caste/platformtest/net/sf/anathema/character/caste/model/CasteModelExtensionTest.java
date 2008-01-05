package net.sf.anathema.character.caste.model;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

import org.junit.Test;

public class CasteModelExtensionTest {

  @Test
  public void casteModelIsRegistered() throws Exception {
    assertTrue("Caste model must be registered with id " + ICasteModel.ID, modelIsRegistered(ICasteModel.ID)); //$NON-NLS-1$
  }

  private boolean modelIsRegistered(String modelId) {
    EclipseExtensionPoint point = new EclipseExtensionPoint("net.sf.anathema.character.core", "models"); //$NON-NLS-1$ //$NON-NLS-2$
    for (IPluginExtension extension : point.getExtensions()) {
      for (IExtensionElement modelElement : extension.getElements()) {
        if (!modelId.equals(modelElement.getAttribute("id"))) { //$NON-NLS-1$
          continue;
        }
        return true;
      }
    }
    return false;
  }
}