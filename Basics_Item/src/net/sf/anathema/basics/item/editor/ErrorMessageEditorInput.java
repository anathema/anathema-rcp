package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ErrorMessageEditorInput extends DefaultAdaptable implements IEditorInput {

  private final String message;

  public ErrorMessageEditorInput(String message) {
    this.message = message;
  }

  @Override
  public boolean exists() {
    return false;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return null;
  }

  @Override
  public String getName() {
    return "Error message";
  }

  @Override
  public IPersistableElement getPersistable() {
    return null;
  }

  @Override
  public String getToolTipText() {
    return "Error message";
  }
  
  public String getMessage() {
    return message;
  }
}