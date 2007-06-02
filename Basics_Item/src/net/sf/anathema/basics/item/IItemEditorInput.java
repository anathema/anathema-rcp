package net.sf.anathema.basics.item;

import java.io.IOException;

import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;

public interface IItemEditorInput extends IEditorInput {

  public IItem<IBasicItemData> save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException;

  public IItem<IBasicItemData> loadItem() throws PersistenceException, CoreException;
}