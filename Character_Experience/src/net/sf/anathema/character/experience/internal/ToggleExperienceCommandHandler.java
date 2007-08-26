package net.sf.anathema.character.experience.internal;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.experience.plugin.IExperiencePluginConstants;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

public class ToggleExperienceCommandHandler extends AbstractHandler implements IElementUpdater {
  private static final Logger logger = new Logger(IExperiencePluginConstants.PLUGIN_ID);
  private final ExperiencePersister persister = new ExperiencePersister();
  private IExperience experience;

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    IEditorPart editor = HandlerUtil.getActiveEditorChecked(event);
    IModelIdentifier currentIdentifier = (IModelIdentifier) editor.getEditorInput().getAdapter(IModelIdentifier.class);
    if (currentIdentifier == null) {
      return null;
    }
    ModelIdentifier experienceIdentifier = new ModelIdentifier(currentIdentifier.getCharacterId(), IExperience.MODEL_ID);
    this.experience = (IExperience) ModelCache.getInstance().getModel(experienceIdentifier);
    experience.setExperienced(!experience.isExperienced());
    IContentHandle content = new ModelExtensionPoint().getModelContent(experienceIdentifier);
    try {
      // TODO Progressmonitor?
      new ItemFileWriter().save(content, persister, experience, new NullProgressMonitor());
    }
    catch (Exception e) {
      logger.error(Messages.ToggleExperienceActionDelegate_ErrorSavingModel, e);
      throw new ExecutionException(Messages.ToggleExperienceActionDelegate_ErrorSavingModel, e);
    }
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    ICommandService commandService = (ICommandService) window.getService(ICommandService.class);
    commandService.refreshElements(event.getCommand().getId(), new HashMap());
    return null;
  }

  @Override
  public void updateElement(UIElement element, Map parameters) {
    element.setChecked(experience.isExperienced());
  }
}