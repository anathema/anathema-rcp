package net.sf.anathema.character.core.repository.internal;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;

public interface IModelDisplayConfiguration extends IModelConfiguration {

  public String getDisplayName();

  public IEditorInput createEditorInput(
      IFolder characterFolder,
      ImageDescriptor descriptor,
      IDisplayNameProvider provider) throws PersistenceException, CoreException, ExtensionException;

  public String getEditorId();

  public ImageDescriptor getImageDescriptor();
}