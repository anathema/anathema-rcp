package net.sf.anathema.character.experience.internal;

import java.lang.reflect.InvocationTargetException;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.experience.plugin.ExperiencePlugin;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class AbstractToggleExperienceHandler extends AbstractHandler implements IElementUpdater {

  private final ExperiencePersister persister = new ExperiencePersister();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ModelIdentifier identifier = getExperienceIdentifier(event);
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
    toggleExperiencedState(identifier, window);
    refreshUserInterface(event);
    return null;
  }

  protected abstract ModelIdentifier getExperienceIdentifier(ExecutionEvent event) throws ExecutionException;

  private void toggleExperiencedState(ModelIdentifier identifier, IWorkbenchWindow window) throws ExecutionException {
    final IExperience model = (IExperience) ModelCache.getInstance().getModel(identifier);
    model.setExperienced(!model.isExperienced());
    final IContentHandle content = new ModelExtensionPoint().getModelContent(identifier);
    try {
      window.run(true, false, new IRunnableWithProgress() {
        @Override
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
          try {
            new ItemFileWriter().save(content, persister, model, monitor);
          }
          catch (Exception e) {
            throw new InvocationTargetException(e);
          }
        }
      });
    }
    catch (InvocationTargetException e) {
      ExperiencePlugin.getDefaultInstance().log(
          IStatus.ERROR,
          Messages.ToggleExperienceActionDelegate_ErrorSavingModel,
          e);
      throw new ExecutionException(Messages.ToggleExperienceActionDelegate_ErrorSavingModel, e);
    }
    catch (InterruptedException e) {
      ExperiencePlugin.getDefaultInstance().log(
          IStatus.ERROR,
          Messages.AbstractToggleExperienceHandler_SaveExperienceInterrupted,
          e);
      throw new ExecutionException(Messages.AbstractToggleExperienceHandler_SaveExperienceInterrupted, e);
    }
  }

  private void refreshUserInterface(ExecutionEvent event) throws ExecutionException {
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    ICommandService commandService = (ICommandService) window.getService(ICommandService.class);
    commandService.refreshElements(event.getCommand().getId(), null);
  }
}