package net.sf.anathema.character.experience.internal;

import java.util.Map;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.experience.plugin.ExperiencePlugin;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

public class ToggleExperienceSelectionHandler extends AbstractHandler implements IElementUpdater {
  private final ExperiencePersister persister = new ExperiencePersister();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
    ModelIdentifier identifier = getExperienceIdentifier(selection);
    if (identifier == null) {
      throw new ExecutionException("This handler should not be active when the current selection is empty."); //$NON-NLS-1$
    }
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
    IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
    ICommandService commandService = (ICommandService) window.getService(ICommandService.class);
    commandService.refreshElements(event.getCommand().getId(), null);
    return null;
  }

  private ModelIdentifier getExperienceIdentifier(ISelection selection) {
    if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
      return null;
    }
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    IViewElement element = (IViewElement) structuredSelection.getFirstElement();
    IFolder folder = (IFolder) element.getAdapter(IFolder.class);
    ModelIdentifier identifier = new ModelIdentifier(folder, IExperience.MODEL_ID);
    return identifier;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void updateElement(final UIElement element, Map parameters) {
    IWorkbenchWindow window = (IWorkbenchWindow) parameters.get("org.eclipse.ui.IWorkbenchWindow"); //$NON-NLS-1$
    ISelection selection = window.getSelectionService().getSelection();
    if (selection == null) {
      return;
    }
    IModelIdentifier modelIdentifier = getExperienceIdentifier(selection);
    if (modelIdentifier == null) {
      return;
    }
    IExperience experience = (IExperience) ModelCache.getInstance().getModel(
        new ModelIdentifier(modelIdentifier.getCharacterId(), IExperience.MODEL_ID));
    element.setChecked(experience.isExperienced());
  }
}