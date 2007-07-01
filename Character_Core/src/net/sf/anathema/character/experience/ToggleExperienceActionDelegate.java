package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.model.internal.ModelCache;
import net.sf.anathema.character.core.repository.internal.CharacterViewElement;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ToggleExperienceActionDelegate implements IObjectActionDelegate {

  private IExperience model;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    // nothing to do
  }

  @Override
  public void run(IAction action) {
    model.setExperienced(action.isChecked());
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    if (selection.isEmpty() || !(selection instanceof StructuredSelection)) {
      model = null;
      return;
    }
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    CharacterViewElement element = (CharacterViewElement) structuredSelection.getFirstElement();
    IFolder folder = (IFolder) element.getAdapter(IFolder.class);
    ModelIdentifier identifier = new ModelIdentifier(folder, IExperience.MODEL_ID);
    this.model = (IExperience) ModelCache.getInstance().getModel(identifier);
    action.setChecked(model.isExperienced());
  }
}