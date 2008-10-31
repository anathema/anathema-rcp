package net.sf.anathema.basics.item.editor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.resource.ImageDisposable;
import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

public abstract class AbstractItemEditorControl extends AggregatedDisposable implements IEditorControl {

  private static final class DirtyChangeDisposable implements IDisposable {
    private final IPersistableEditorInput< ? > itemInput;
    private final IChangeListener dirtyChangeListener;

    private DirtyChangeDisposable(IPersistableEditorInput< ? > itemInput, IChangeListener dirtyChangeListener) {
      this.itemInput = itemInput;
      this.dirtyChangeListener = dirtyChangeListener;
    }

    @Override
    public void dispose() {
      itemInput.getItem().removeDirtyListener(dirtyChangeListener);
    }
  }

  private final IPersistableItemEditor editor;

  public AbstractItemEditorControl(IPersistableItemEditor editor) {
    this.editor = editor;
  }

  @Override
  public final boolean isDirty() {
    return editor.getPersistableEditorInput().getItem().isDirty();
  }
  
  protected final IPersistableItemEditor getEditor() {
    return editor;
  }

  @Override
  public void init(final IEditorSite editorSite, IEditorInput input) {
    final IChangeListener dirtyChangeListener = new IChangeListener() {
      public void stateChanged() {
        editorSite.getShell().getDisplay().asyncExec(new FireDirtyRunnable(editor));
      }
    };
    final IPersistableEditorInput< ? > itemInput = editor.getPersistableEditorInput();
    itemInput.getItem().addDirtyListener(dirtyChangeListener);
    editor.setPartName(editor.getPersistableEditorInput().getName());
    addDisposable(new DirtyChangeDisposable(itemInput, dirtyChangeListener));
    Image titleImage = itemInput.getImageDescriptor().createImage();
    editor.setTitleImage(titleImage);
    addDisposable(new ImageDisposable(titleImage));
  }
}