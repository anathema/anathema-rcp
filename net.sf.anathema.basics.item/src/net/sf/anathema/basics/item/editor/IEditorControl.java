package net.sf.anathema.basics.item.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

public interface IEditorControl {

  public void createPartControl(Composite parent);

  public void init(IEditorSite editorSite, IEditorInput input);

  public boolean isDirty();

  public void setFocus();
}