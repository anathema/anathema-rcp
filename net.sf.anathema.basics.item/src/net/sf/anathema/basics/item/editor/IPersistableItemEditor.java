package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.IPersistableEditorInput;

import org.eclipse.swt.graphics.Image;

public interface IPersistableItemEditor {

  public void setPartName(String name);

  public void firePropertyChange(final int propertyId);

  public IPersistableEditorInput< ? > getPersistableEditorInput();

  public void setTitleImage(Image image);
}