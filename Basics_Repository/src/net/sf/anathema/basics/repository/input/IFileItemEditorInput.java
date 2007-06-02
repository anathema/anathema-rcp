package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.data.IItemData;

import org.eclipse.core.resources.IFile;

public interface IFileItemEditorInput<D extends IItemData> extends IPersistableEditorInput<D> {

  public IFile getFile();
}