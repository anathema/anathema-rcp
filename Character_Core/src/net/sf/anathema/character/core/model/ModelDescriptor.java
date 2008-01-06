package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class ModelDescriptor extends AbstractExecutableExtension implements IModelDescriptor {
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$

  public static IModelDescriptor create(IExtensionElement modelElement) {
    try {
      return new ModelDescriptor(modelElement);
    }
    catch (ExtensionException e) {
      CharacterCorePlugin.getDefaultInstance().log(
          IStatus.ERROR,
          NLS.bind(
              Messages.ModelExtensionPoint_ModelDescriptionLoadErrorMessage,
              modelElement.getAttribute(ATTRIB_ID)),
          e);
      return new NullModelDescriptor();
    }
  }

  private final IModelDescriptor modelDescriptor;

  private ModelDescriptor(IExtensionElement modelElement) throws ExtensionException {
    IModelDescriptor specialDescriptor = modelElement.getAttributeAsObject("modelDescriptor", IModelDescriptor.class);
    this.modelDescriptor = specialDescriptor == null
        ? new SimpleModelDescriptor(modelElement.getAttribute(ATTRIB_ID))
        : specialDescriptor;
  }

  public boolean isSupportedBy(ICharacterTemplate template) {
    return modelDescriptor.isSupportedBy(template);
  }
}