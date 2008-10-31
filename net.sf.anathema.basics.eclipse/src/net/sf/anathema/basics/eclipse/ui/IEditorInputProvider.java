package net.sf.anathema.basics.eclipse.ui;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;

public interface IEditorInputProvider {

  public IEditorInput getEditorInput() throws PersistenceException, CoreException, ExtensionException;

}
