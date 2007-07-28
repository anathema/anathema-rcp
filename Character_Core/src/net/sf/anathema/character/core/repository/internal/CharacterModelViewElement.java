package net.sf.anathema.character.core.repository.internal;

import java.io.IOException;

import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.CharacterCorePlugin;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterModelViewElement implements IViewElement {

  private final IViewElement parent;
  private final IFolder folder;
  private final IFolder characterFolder;
  private final IModelDisplayConfiguration configuration;

  public CharacterModelViewElement(
      IViewElement parent,
      IFolder characterFolder,
      IModelDisplayConfiguration configuration) {
    this.parent = parent;
    this.folder = characterFolder;
    this.characterFolder = characterFolder;
    this.configuration = configuration;
  }

  @Override
  public final IViewElement[] getChildren() {
    return new IViewElement[0];
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return configuration.getImageDescriptor();
  }

  @Override
  public final IViewElement getParent() {
    return parent;
  }

  @Override
  public final boolean hasChildren() {
    return false;
  }

  @Override
  public final boolean equals(Object object) {
    if (object == null || object.getClass() != getClass()) {
      return false;
    }
    CharacterModelViewElement other = (CharacterModelViewElement) object;
    return folder.equals(other.folder);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    if (adapter == IResource.class) {
      return configuration.getModelFile(characterFolder);
    }
    return null;
  }

  @Override
  public String getDisplayName() {
    return configuration.getDisplayName();
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    try {
      IEditorInput input = configuration.createEditorInput(
          characterFolder,
          getImageDescriptor(),
          new DisplayNameProvider(getDisplayName(), getParent()));
      page.openEditor(input, configuration.getEditorId());
    }
    catch (Exception e) {
      throw new PartInitException(new Status(
          IStatus.ERROR,
          CharacterCorePlugin.PLUGIN_ID,
          BasicRepositoryMessages.RepositoryBasics_CreateEditorInputFailedMessage,
          e));
    }
  }

  @Override
  public boolean canBeDeleted() {
    return false;
  }

  @Override
  public void delete(IWorkbenchPage page) throws CoreException, IOException {
    throw new UnsupportedOperationException("Cannot be deleted"); //$NON-NLS-1$
  }
}