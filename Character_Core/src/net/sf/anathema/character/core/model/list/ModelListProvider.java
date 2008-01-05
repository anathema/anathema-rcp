package net.sf.anathema.character.core.model.list;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class ModelListProvider implements IModelListProvider {

  private static final String ATTRIB_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final String TAG_MODEL_REFERENCE = "modelReference"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private final Map<String, IModelList> modelListsById = new HashMap<String, IModelList>();

  public ModelListProvider() {
    this(new EclipseExtensionPoint(CharacterCorePlugin.ID, "modellists").getExtensions()); //$NON-NLS-1$
  }

  public ModelListProvider(IPluginExtension... extensions) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        addModelList(element);
      }
    }
  }

  private void addModelList(IExtensionElement element) {
    ModelList modelList = new ModelList();
    String modelListId = element.getAttribute(ATTRIB_ID);
    for (IExtensionElement child : element.getElements()) {
      if (TAG_MODEL_REFERENCE.equals(child.getName())) {
        modelList.addModelId(child.getAttribute(ATTRIB_MODEL_ID));
      }
    }
    addModelList(modelListId, modelList);
  }

  public void addModelList(String modelListId, IModelList modelList) {
    modelListsById.put(modelListId, modelList);
  }

  @Override
  public IModelList getModelList(String modelListId) {
    IModelList modelList = modelListsById.get(modelListId);
    if (modelList == null) {
      return new ModelList();
    }
    return modelList;
  }
}