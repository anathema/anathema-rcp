package net.sf.anathema.character.trait.display;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

import org.eclipse.core.runtime.IStatus;

public class DisplayFactoryLookup {

  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String ATTRIB_MODELID = "modelId"; //$NON-NLS-1$
  private static final String EXTENSION_POINT = "displaygroupfactory"; //$NON-NLS-1$
  private final EclipseExtensionPoint point;

  public DisplayFactoryLookup() {
    point = new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, EXTENSION_POINT);
  }

  public IDisplayGroupFactory getFor(final String mainModel) {
    IExtensionElement element = point.getFirst(new IPredicate<IExtensionElement>() {
      @Override
      public boolean evaluate(IExtensionElement candidate) {
        String model = candidate.getAttribute(ATTRIB_MODELID);
        return model.equals(mainModel);
      }
    });
    try {
      return element.getAttributeAsObject(ATTRIB_CLASS, IDisplayGroupFactory.class);
    }
    catch (ExtensionException e) {
      CharacterTraitPlugin.getDefaultInstance().log(
          IStatus.ERROR,
          "Failed to instantiate display group factory for " + mainModel,
          e);
      throw new RuntimeException(e);
    }
  }
}