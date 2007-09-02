package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.ui.IUpdatable;

import org.eclipse.ui.IEditorInput;

public final class CurrentModelUpdatable implements IUpdatable, IDisposable {
  private IChangeListener modelChangeListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      updatable.update();
    }
  };
  private IModel currentModel;
  private final IUpdatable updatable;
  private final IPartContainer partContainer;
  private final IModelProvider modelProvider;

  public CurrentModelUpdatable(IUpdatable updatable, IPartContainer partContainer, IModelProvider modelProvider) {
    this.updatable = updatable;
    this.partContainer = partContainer;
    this.modelProvider = modelProvider;
  }

  @Override
  public void update() {
    IEditorInput editorInput = partContainer.getEditorInput();
    if (editorInput == null) {
      modelSelected(null);
      return;
    }
    IModelIdentifier modelIdentifier = (IModelIdentifier) editorInput.getAdapter(IModelIdentifier.class);
    if (modelIdentifier == null) {
      modelSelected(null);
      return;
    }
    IModel model = modelProvider.getModel(modelIdentifier);
    modelSelected(model);
  }

  private void modelSelected(IModel model) {
    if (currentModel != null) {
      currentModel.removeChangeListener(modelChangeListener);
    }
    currentModel = model;
    if (currentModel != null) {
      currentModel.addChangeListener(modelChangeListener);
    }
  }

  @Override
  public void dispose() {
    modelSelected(null);
  }
}