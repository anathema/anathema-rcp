package net.sf.anathema.character.core.model.list;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class ModelListProvider implements IModelListProvider {

  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final String ATTRIB_LIST_ID = "listId"; //$NON-NLS-1$
  private static final String TAG_MODEL_REFERENCE = "modelReference"; //$NON-NLS-1$
  private static final String TAG_LIST_REFERENCE = "listReference"; //$NON-NLS-1$
  private final Map<String, ModelList> modelListsById = new HashMap<String, ModelList>();

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
    String modelListId = element.getAttribute(ATTRIB_ID);
    ModelList modelList = createRegisteredModelList(modelListId);
    for (IExtensionElement child : element.getElements()) {
      if (TAG_MODEL_REFERENCE.equals(child.getName())) {
        modelList.addModelId(child.getAttribute(ATTRIB_MODEL_ID));
      }
      if (TAG_LIST_REFERENCE.equals(child.getName())) {
        modelList.addModelListId(child.getAttribute(ATTRIB_LIST_ID));
      }
    }
  }

  private ModelList createRegisteredModelList(String listId) {
    if (modelListsById.containsKey(listId)) {
      return modelListsById.get(listId);
    }
    final ModelList modelList = new ModelList(this);
    modelListsById.put(listId, modelList);
    return modelList;
  }

  @Override
  public IModelList getModelList(String modelListId) {
    IModelList modelList = modelListsById.get(modelListId);
    if (modelList == null) {
      return new NullModelList();
    }
    return modelList;
  }
}