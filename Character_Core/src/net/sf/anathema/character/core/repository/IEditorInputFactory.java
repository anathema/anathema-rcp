package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;

public interface IEditorInputFactory {

  public IEditorInput create(
      IFile modelFile,
      IFolder characterFolder,
      ImageDescriptor descriptor,
      IDisplayNameProvider nameProvider,
      IModelProvider modelProvider) throws PersistenceException, CoreException;
}