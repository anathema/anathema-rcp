package net.sf.anathema.basics.repository.input;

import net.sf.anathema.basics.item.IItemEditorInput;

import org.eclipse.core.resources.IFile;

public interface IFileItemEditorInput extends IItemEditorInput {

  public IFile getFile();
}