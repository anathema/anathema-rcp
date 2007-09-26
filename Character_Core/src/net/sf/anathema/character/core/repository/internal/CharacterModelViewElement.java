package net.sf.anathema.character.core.repository.internal;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterModelViewElement implements IViewElement {

  private final IViewElement parent;
  private final IFolder folder;
  private final IFolder characterFolder;
  private final IModelDisplayConfiguration configuration;
  private final DefaultAdaptable adaptable = new DefaultAdaptable();

  public CharacterModelViewElement(
      IViewElement parent,
      IFolder characterFolder,
      IModelDisplayConfiguration configuration) {
    this.parent = parent;
    this.folder = characterFolder;
    this.characterFolder = characterFolder;
    this.configuration = configuration;
    initAdaptable();
  }

  private void initAdaptable() {
    adaptable.add(IResource.class, new IProvider<IResource>() {
      @Override
      public IResource get() {
        return configuration.getModelFile(characterFolder);
      }
    });
    adaptable.add(IEditorInputProvider.class, new IProvider<IEditorInputProvider>() {
      @Override
      public IEditorInputProvider get() {
        return new IEditorInputProvider() {
          @Override
          public IEditorInput getEditorInput() throws PersistenceException, CoreException, ExtensionException {
            return CharacterModelViewElement.this.getEditorInput();
          }
        };
      }
    });
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
    return folder.equals(other.folder) && configuration.equals(other.configuration);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return adaptable.getAdapter(adapter);
  }

  @Override
  public String getDisplayName() {
    return configuration.getDisplayName();
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    try {
      IEditorInput input = getEditorInput();
      String editorId = configuration.getEditorId();
      IEditorPart openEditor = page.openEditor(input, editorId);
      ensureResourceExists(input, openEditor);
    }
    catch (Exception e) {
      throw new PartInitException(new Status(
          IStatus.ERROR,
          CharacterCorePlugin.ID,
          BasicRepositoryMessages.RepositoryBasics_CreateEditorInputFailedMessage,
          e));
    }
  }

  private void ensureResourceExists(IEditorInput input, IEditorPart openEditor) {
    IResource resource = (IResource) input.getAdapter(IResource.class);
    if (!resource.exists()) {
      openEditor.doSave(new NullProgressMonitor());
    }
  }

  private IEditorInput getEditorInput() throws PersistenceException, CoreException, ExtensionException {
    IEditorInput input = configuration.createEditorInput(
        characterFolder,
        getImageDescriptor(),
        new DisplayNameProvider(getDisplayName(), getParent()));
    return input;
  }
}