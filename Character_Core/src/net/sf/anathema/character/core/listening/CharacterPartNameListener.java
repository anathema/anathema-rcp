package net.sf.anathema.character.core.listening;

import net.sf.anathema.basics.item.editor.IPersistableItemEditor;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.swt.widgets.Display;

public final class CharacterPartNameListener implements IResourceChangeListener {

  private final IPersistableItemEditor editorPart;
  private final IFolder characterFolder;
  private final Display display;

  public CharacterPartNameListener(IPersistableItemEditor editorPart, IFolder characterFolder, Display display) {
    this.editorPart = editorPart;
    this.characterFolder = characterFolder;
    this.display = display;
  }

  @Override
  public void resourceChanged(IResourceChangeEvent event) {
    if (findCharacterFolder(event.getDelta())) {
      display.asyncExec(new Runnable() {
        @Override
        public void run() {
          editorPart.setPartName(editorPart.getPersistableEditorInput().getName());
        }
      });
    }
  }

  private boolean findCharacterFolder(IResourceDelta... deltas) {
    for (IResourceDelta delta : deltas) {
      if (characterFolder.equals(delta.getResource())) {
        return true;
      }
      if (findCharacterFolder(delta.getAffectedChildren())) {
        return true;
      }
    }
    return false;
  }
}