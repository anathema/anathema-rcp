package net.sf.anathema.basics.item.editor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.resource.ImageDisposable;
import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

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
  private Form form;

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

  @Override
  public final void createPartControl(Composite parent) {
    FormToolkit toolkit = new FormToolkit(parent.getDisplay());
    Composite body = createFormBody(parent, toolkit);
    createPartControl(toolkit, body);
  }

  protected abstract void createPartControl(FormToolkit toolkit, Composite body);

  protected final Composite createFormBody(Composite parent, FormToolkit toolkit) {
    form = toolkit.createForm(parent);
    toolkit.decorateFormHeading(form);
    form.setText(getEditor().getPersistableEditorInput().getName());
    Composite body = form.getBody();
    body.setLayout(new GridLayout(1, false));
    return body;
  }

  protected Form getForm() {
    return form;
  }
}