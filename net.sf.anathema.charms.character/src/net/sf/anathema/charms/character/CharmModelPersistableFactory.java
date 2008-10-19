package net.sf.anathema.charms.character;

import net.sf.anathema.character.core.editors.ModelPersistableFactory;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IMemento;

public class CharmModelPersistableFactory extends ModelPersistableFactory {

  @Override
  public IAdaptable createElement(IMemento memento) {
    IAdaptable editorInput = super.createElement(memento);
    if (editorInput instanceof CharmsEditorInput) {
      ((CharmsEditorInput) editorInput).setTreeId(memento.getString(CharmsEditorInput.MEMENTO_TREE_ID));
    }
    return editorInput;
  }
}