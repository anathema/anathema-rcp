package net.sf.anathema.charms.character.editor;

import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public final class TreeIdPersistableDecoration implements IPersistableElement {
  private final IPersistableElement original;
  private final String treeId;

  public TreeIdPersistableDecoration(IPersistableElement original, String treeId) {
    this.original = original;
    this.treeId = treeId;
  }

  @Override
  public String getFactoryId() {
    return CharmsEditorInput.PERSISTABLE_FACTORY_ID;
  }

  @Override
  public void saveState(IMemento memento) {
    original.saveState(memento);
    memento.putString(CharmsEditorInput.MEMENTO_TREE_ID, treeId);
  }
}