package net.sf.anathema.character.experience.internal;

import java.util.Map;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.eclipse.ui.PartContainer;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.character.core.model.IModelIdentifier;
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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

public class ToggleExperienceCommandHandler extends AbstractHandler implements IElementUpdater {
  private final ExperiencePersister persister = new ExperiencePersister();
  private CommandRefreshChangeListener listener;
  private IExperience experience;

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IEditorPart editor = HandlerUtil.getActiveEditorChecked(event);
    IModelIdentifier currentIdentifier = (IModelIdentifier) editor.getEditorInput().getAdapter(IModelIdentifier.class);
    if (currentIdentifier == null) {
      return null;
    }
    ModelIdentifier experienceIdentifier = new ModelIdentifier(currentIdentifier.getCharacterId(), IExperience.MODEL_ID);
    IExperience model = (IExperience) ModelCache.getInstance().getModel(experienceIdentifier);
    model.setExperienced(!model.isExperienced());
    IContentHandle content = new ModelExtensionPoint().getModelContent(experienceIdentifier);
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
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    ICommandService commandService = (ICommandService) window.getService(ICommandService.class);
    commandService.refreshElements(event.getCommand().getId(), null);
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void updateElement(final UIElement element, Map parameters) {
    resetListening();
    IWorkbenchWindow window = (IWorkbenchWindow) parameters.get("org.eclipse.ui.IWorkbenchWindow"); //$NON-NLS-1$
    PartContainer partContainer = new PartContainer(window);
    IEditorInput input = partContainer.getEditorInput();
    if (input == null) {
      return;
    }
    IModelIdentifier modelIdentifier = (IModelIdentifier) input.getAdapter(IModelIdentifier.class);
    if (modelIdentifier == null) {
      return;
    }
    this.listener = new CommandRefreshChangeListener(element);
    this.experience = (IExperience) ModelCache.getInstance().getModel(
        new ModelIdentifier(modelIdentifier.getCharacterId(), IExperience.MODEL_ID));
    experience.addChangeListener(listener);
    element.setChecked(experience.isExperienced());
  }

  private void resetListening() {
    if (experience != null) {
      this.experience.removeChangeListener(listener);
    }
    this.experience = null;
    this.listener = null;
  }
}