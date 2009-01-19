package net.sf.anathema.character.core.model;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.ui.IUpdatable;

import org.eclipse.ui.IEditorInput;

public final class CurrentModelUpdatable implements IUpdatable, IDisposable {
  private final IChangeListener modelChangeListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      updatable.update();
    }
  };
  private IModel currentModel;
  private final IUpdatable updatable;
  private final IPartContainer partContainer;
  private final IModelCollection modelProvider;

  public CurrentModelUpdatable(IUpdatable updatable, IPartContainer partContainer, IModelCollection modelProvider) {
    this.updatable = updatable;
    this.partContainer = partContainer;
    this.modelProvider = modelProvider;
  }

  @Override
  public void update() {
    IEditorInput editorInput = partContainer.getEditorInput();
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