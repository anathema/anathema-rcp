package net.sf.anathema.basics.item.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ErrorMessageEditorInput implements IEditorInput {

  public ErrorMessageEditorInput(String message) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public boolean exists() {
    return true;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getName() {
    return "Mein Name";
  }

  @Override
  public IPersistableElement getPersistable() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getToolTipText() {
    return "Hallo Tooltip";
  }

  @Override
  public Object getAdapter(Class adapter) {
    // TODO Auto-generated method stub
    return null;
  }
}