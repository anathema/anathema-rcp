package net.sf.anathema.character.core.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.CharacterCorePlugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class ModelCache implements IModelProvider {

  private static final IModelProvider instance = new ModelCache();
  private Map<ModelIdentifier, Object> modelsByIdentifier = new HashMap<ModelIdentifier, Object>();

  public static IModelProvider getInstance() {
    return instance;
  }

  public void addModel(ModelIdentifier identifier, Object model) {
    modelsByIdentifier.put(identifier, model);
  }

  public Object getModel(ModelIdentifier identifier) {
    Object model = modelsByIdentifier.get(identifier);
    if (model == null) {
      model = createModel(identifier);
      addModel(identifier, model);
    }
    return model;
  }

  private Object createModel(ModelIdentifier identifier) {
    for (IPluginExtension extension : new EclipseExtensionProvider().getExtensions("net.sf.anathema.character.models")) { //$NON-NLS-1$
      for (IExtensionElement extensionElement : extension.getElements()) {
        if (extensionElement.getAttribute("id").equals(identifier.getId())) { //$NON-NLS-1$
          try {
            IModelFactory factory = extensionElement.getAttributeAsObject("modelFactory", //$NON-NLS-1$
                IModelFactory.class);
            return factory.create(identifier.getFolder());
          }
          catch (ExtensionException e) {
            CharacterCorePlugin.getDefaultInstance().log(IStatus.ERROR, Messages.ModelCache_ModelLoadError, e);
          }
        }
      }
    }
    throw new IllegalArgumentException(NLS.bind(Messages.ModelCache_ModelNotFound_Message, identifier.getId()));
  }
}