package net.sf.anathema.charms.character;

import net.sf.anathema.character.core.editors.ModelPersistableFactory;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IMemento;

public class CharmModelPersistableFactory extends ModelPersistableFactory {

  @Override
  public IAdaptable createElement(IMemento memento) {
    IAdaptable adaptable = super.createElement(memento);
    if (adaptable instanceof CharmsEditorInput) {
      ((CharmsEditorInput) adaptable).setTreeId(getTreeIdFrom(memento));
    }
    return adaptable;
  }

  private String getTreeIdFrom(IMemento memento) {
    return memento.getString(CharmsEditorInput.MEMENTO_TREE_ID);
  }
}