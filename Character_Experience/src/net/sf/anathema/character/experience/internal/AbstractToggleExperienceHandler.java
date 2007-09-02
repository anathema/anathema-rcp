package net.sf.anathema.character.experience.internal;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.experience.plugin.ExperiencePlugin;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class AbstractToggleExperienceHandler extends AbstractHandler implements IElementUpdater {

  private final ExperiencePersister persister = new ExperiencePersister();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ModelIdentifier identifier = getExperienceIdentifier(event);
    toggleExperiencedState(identifier);
    refreshUserInterface(event);
    return null;
  }

  protected abstract ModelIdentifier getExperienceIdentifier(ExecutionEvent event) throws ExecutionException;

  private void toggleExperiencedState(ModelIdentifier identifier) throws ExecutionException {
    IExperience model = (IExperience) ModelCache.getInstance().getModel(identifier);
    model.setExperienced(!model.isExperienced());
    IContentHandle content = new ModelExtensionPoint().getModelContent(identifier);
    try {
      // TODO Progressmonitor?
      new ItemFileWriter().save(content, persister, model, new NullProgressMonitor());
    }
    catch (Exception e) {
      ExperiencePlugin.getDefaultInstance().log(
          IStatus.ERROR,
          Messages.ToggleExperienceActionDelegate_ErrorSavingModel,
          e);
      throw new ExecutionException(Messages.ToggleExperienceActionDelegate_ErrorSavingModel, e);
    }
  }

  private void refreshUserInterface(ExecutionEvent event) throws ExecutionException {
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    ICommandService commandService = (ICommandService) window.getService(ICommandService.class);
    commandService.refreshElements(event.getCommand().getId(), null);
  }
}