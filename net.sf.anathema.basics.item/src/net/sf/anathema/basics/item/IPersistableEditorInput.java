package net.sf.anathema.basics.item;

import java.io.IOException;

import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;

public interface IPersistableEditorInput<D extends IItem> extends IEditorInput {

  public D save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException;

  public D getItem();
}