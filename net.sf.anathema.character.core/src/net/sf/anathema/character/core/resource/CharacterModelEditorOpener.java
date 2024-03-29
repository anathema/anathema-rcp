package net.sf.anathema.character.core.resource;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.ui.IResourceEditorOpener;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterModelEditorOpener extends UnconfiguredExecutableExtension implements IResourceEditorOpener {

  public static final String ID = "net.sf.anathema.character.modelopener"; //$NON-NLS-1$

  public void openEditor(IWorkbenchPage page, IResource modelResource) throws PartInitException {
    IModelDisplayConfiguration configuration = new ModelExtensionPoint().getDisplayConfiguration(modelResource);
    final IContainer characterFolder = modelResource.getParent();
    openEditor(page, characterFolder, configuration);
  }

  public void openEditor(IWorkbenchPage page, final IContainer characterFolder, IModelDisplayConfiguration configuration)
      throws PartInitException {
    IEditorInput input = null;
    try {
      input = createEditorInput(characterFolder, configuration);
    }
    catch (Exception e) {
      throw new PartInitException(new Status(
          IStatus.ERROR,
          CharacterCorePlugin.ID,
          BasicRepositoryMessages.RepositoryBasics_CreateEditorInputFailedMessage,
          e));
    }
    openEditor(page, configuration, input);
  }

  public void openEditor(IWorkbenchPage page, IModelDisplayConfiguration configuration, IEditorInput input)
      throws PartInitException {
    String editorId = configuration.getEditorId();
    IEditorPart openEditor = page.openEditor(input, editorId);
    ensureResourceExists(input, openEditor);
  }

  public IEditorInput createEditorInput(final IContainer characterFolder, IModelDisplayConfiguration configuration)
      throws PersistenceException,
      CoreException,
      ExtensionException {
    ModelDisplayNameProvider displayNameProvider = new ModelDisplayNameProvider(
        configuration.getDisplayName(),
        new CharacterDisplayNameProvider(characterFolder));
    return configuration.createEditorInput(characterFolder, displayNameProvider);
  }

  private void ensureResourceExists(IEditorInput input, IEditorPart openEditor) {
    // TODO Eine andere Lösung muss her, sobald Abhängigkeiten zwischen Models exisiteren.
    IResource resource = (IResource) input.getAdapter(IResource.class);
    if (!resource.exists()) {
      openEditor.doSave(new NullProgressMonitor());
    }
  }
}