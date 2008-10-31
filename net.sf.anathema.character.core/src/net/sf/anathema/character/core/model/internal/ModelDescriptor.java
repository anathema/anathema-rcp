package net.sf.anathema.character.core.model.internal;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.IExecutableModelSupporter;
import net.sf.anathema.character.core.model.IModelDescriptor;
import net.sf.anathema.character.core.model.IModelSupporter;
import net.sf.anathema.character.core.model.Messages;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class ModelDescriptor implements IModelDescriptor {
  private static final String ATTRIB_MODEL_SUPPORTER = "modelSupporter"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$

  public static IModelDescriptor create(IExtensionElement modelElement) {
    try {
      return new ModelDescriptor(modelElement);
    }
    catch (ExtensionException e) {
      String modelId = modelElement.getAttribute(ATTRIB_ID);
      String message = NLS.bind(Messages.ModelExtensionPoint_ModelDescriptionLoadErrorMessage, modelId);
      CharacterCorePlugin.getDefaultInstance().log(IStatus.ERROR, message, e);
      return new NullModelDescriptor();
    }
  }

  private final IModelSupporter modelDescriptor;

  private ModelDescriptor(IExtensionElement modelElement) throws ExtensionException {
    IExecutableModelSupporter specialSupporter = modelElement.getAttributeAsObject(
        ATTRIB_MODEL_SUPPORTER,
        IExecutableModelSupporter.class);
    this.modelDescriptor = specialSupporter == null
        ? new DefaultModelSupporter(modelElement.getAttribute(ATTRIB_ID))
        : specialSupporter;
  }

  public boolean isSupportedBy(ICharacterTemplate template) {
    return modelDescriptor.isSupportedBy(template);
  }
}