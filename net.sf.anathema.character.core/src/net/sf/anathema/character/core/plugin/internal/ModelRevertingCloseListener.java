package net.sf.anathema.character.core.plugin.internal;

import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.editor.IPersistableItemEditor;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.core.model.ModelCache;

import org.eclipse.ui.IWorkbenchPart;

public final class ModelRevertingCloseListener extends PartAdapter {

  @Override
  public void partClosed(IWorkbenchPart part) {
    if (!(part instanceof IPersistableItemEditor)) {
      return;
    }
    IPersistableItemEditor editorPart = (IPersistableItemEditor) part;
    if (!editorPart.isDirty()) {
      return;
    }
    IPersistableEditorInput< ? > input = editorPart.getPersistableEditorInput();
    if (!(input instanceof AbstractCharacterModelEditorInput)) {
      return;
    }
    AbstractCharacterModelEditorInput< ? > modelEditorInput = (AbstractCharacterModelEditorInput< ? >) input;
    ModelCache modelCache = ModelCache.getInstance();
    IModel model = modelEditorInput.getItem();
    modelCache.revert(model);
  }
}