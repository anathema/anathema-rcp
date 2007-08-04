package net.sf.anathema.character.experience.internal;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.experience.internal.ExperiencePersister;
import net.sf.anathema.character.experience.internal.Messages;
import net.sf.anathema.character.experience.plugin.IExperiencePluginConstants;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ToggleExperienceActionDelegate implements IObjectActionDelegate {

  private static final Logger logger = new Logger(IExperiencePluginConstants.PLUGIN_ID);
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
    IContentHandle content = new ModelExtensionPoint().getModelContent(modelIdentifier);
    try {
      //TODO Progressmonitor?
      new ItemFileWriter().save(content, persister, model, new NullProgressMonitor());
    }
    catch (Exception e) {
      logger.log(IStatus.ERROR, Messages.ToggleExperienceActionDelegate_ErrorSavingModel, e);
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
    IViewElement element = (IViewElement) structuredSelection.getFirstElement();
    this.folder = (IFolder) element.getAdapter(IFolder.class);
    ModelIdentifier identifier = new ModelIdentifier(folder, IExperience.MODEL_ID);
    this.model = (IExperience) ModelCache.getInstance().getModel(identifier);
    action.setChecked(model.isExperienced());
  }
}