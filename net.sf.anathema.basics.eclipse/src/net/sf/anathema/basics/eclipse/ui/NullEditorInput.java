package net.sf.anathema.basics.eclipse.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

@SuppressWarnings("restriction")
public class NullEditorInput implements IEditorInput {

  private final org.eclipse.ui.internal.part.NullEditorInput input = new org.eclipse.ui.internal.part.NullEditorInput();

  @Override
  public boolean exists() {
    return input.exists();
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return input.getImageDescriptor();
  }

  @Override
  public String getName() {
    return input.getName();
  }

  @Override
  public IPersistableElement getPersistable() {
    return input.getPersistable();
  }

  @Override
  public String getToolTipText() {
    return input.getToolTipText();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return input.getAdapter(adapter);
  }
}