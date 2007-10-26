package net.sf.anathema.basics.repository.input;

import java.net.URL;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;

import org.eclipse.core.resources.IFile;

public interface IFileItemEditorInput<D extends IItem> extends IPersistableEditorInput<D> {

  public IFile getFile();
  
  public URL getImageUrl();
}