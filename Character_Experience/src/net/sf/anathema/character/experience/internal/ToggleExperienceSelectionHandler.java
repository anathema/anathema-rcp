package net.sf.anathema.character.experience.internal;

import java.util.Map;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

public class ToggleExperienceSelectionHandler extends AbstractToggleExperienceHandler {
  @Override
  protected ModelIdentifier getExperienceIdentifier(ExecutionEvent event) throws ExecutionException {
    ISelection selection = HandlerUtil.getCurrentSelectionChecked(event);
    ModelIdentifier identifier = getExperienceIdentifier(selection);
    if (identifier == null) {
      throw new ExecutionException("This handler should not be active when the current selection is empty."); //$NON-NLS-1$
    }
    return identifier;
  }

  private ModelIdentifier getExperienceIdentifier(ISelection selection) {
    if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
      return null;
    }
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    IViewElement element = (IViewElement) structuredSelection.getFirstElement();
    IFolder folder = (IFolder) element.getAdapter(IFolder.class);
    return new ModelIdentifier(folder, IExperience.MODEL_ID);
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