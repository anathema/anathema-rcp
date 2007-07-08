package net.sf.anathema.character.experience;

import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.character.core.CharacterCorePlugin;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.model.internal.ModelCache;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;
import net.sf.anathema.character.core.repository.internal.CharacterViewElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ToggleExperienceActionDelegate implements IObjectActionDelegate {

  private final ExperiencePersister persister = new ExperiencePersister();
  private IExperience model;
  private IFolder folder;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    // nothing to do
  }

  @Override
  public void run(IAction action) {
    model.setExperienced(action.isChecked());
    IModelIdentifier modelIdentifier = new ModelIdentifier(folder, IExperience.MODEL_ID);
    IFile file = new ModelExtensionPoint().getModelFile(modelIdentifier);
    try {
      //TODO Progressmonitor?
      new ItemFileWriter().saveToFile(file, persister, model, new NullProgressMonitor());
    }
    catch (Exception e) {
      CharacterCorePlugin.getDefaultInstance().log(IStatus.ERROR, Messages.ToggleExperienceActionDelegate_ErrorSavingModel, e);
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    if (selection.isEmpty() || !(selection instanceof StructuredSelection)) {
      model = null;
      folder = null;
      return;
    }
    StructuredSelection structuredSelection = (StructuredSelection) selection;
    CharacterViewElement element = (CharacterViewElement) structuredSelection.getFirstElement();
    this.folder = (IFolder) element.getAdapter(IFolder.class);
    ModelIdentifier identifier = new ModelIdentifier(folder, IExperience.MODEL_ID);
    this.model = (IExperience) ModelCache.getInstance().getModel(identifier);
    action.setChecked(model.isExperienced());
  }
}