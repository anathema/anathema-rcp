package net.sf.anathema.character.core.resource;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.basics.repository.problems.IResourceEditorOpener;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.repository.internal.DisplayNameProvider;
import net.sf.anathema.character.core.repository.internal.IModelDisplayConfiguration;
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

public class CharacterModelEditorOpener extends AbstractExecutableExtension implements IResourceEditorOpener {

  public void openEditor(IWorkbenchPage page, IResource modelResource) throws PartInitException {
    IModelDisplayConfiguration configuration = new ModelExtensionPoint().getDisplayConfiguration(modelResource);
    final IContainer characterFolder = modelResource.getParent();
    openEditor(page, characterFolder, configuration);
  }

  public void openEditor(IWorkbenchPage page, final IContainer characterFolder, IModelDisplayConfiguration configuration)
      throws PartInitException {
    try {
      IEditorInput input = createEditorInput(characterFolder, configuration);
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

  public IEditorInput createEditorInput(final IContainer characterFolder, IModelDisplayConfiguration configuration)
      throws PersistenceException,
      CoreException,
      ExtensionException {
    DisplayNameProvider displayNameProvider = new DisplayNameProvider(
        configuration.getDisplayName(),
        new IDisplayNameProvider() {
          @Override
          public String getDisplayName() {
            return new CharacterPrintNameProvider().getPrintName(characterFolder, characterFolder.getName());
          }
        });
    return configuration.createEditorInput(characterFolder, configuration.getImageDescriptor(), displayNameProvider);
  }

  private void ensureResourceExists(IEditorInput input, IEditorPart openEditor) {
    // TODO Eine andere Lösung muss her, sobald Abhängigkeiten zwischen Model exisiteren.
    IResource resource = (IResource) input.getAdapter(IResource.class);
    if (!resource.exists()) {
      openEditor.doSave(new NullProgressMonitor());
    }
  }
}