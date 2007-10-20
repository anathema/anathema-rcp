package net.sf.anathema.character.core.problem;

import java.text.MessageFormat;

import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.basics.repository.problems.ResourceEditorOpenerExtensionPoint;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterModelEditorOpener;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;

public class UneditedModelProblem implements IProblem {

  private final IResource modelResource;
  private String path;
  private String description;

  public UneditedModelProblem(IResource modelResource) {
    this.modelResource = modelResource;
    IContainer container = modelResource.getParent();
    String modelName = new ModelExtensionPoint().getDisplayConfiguration(modelResource).getDisplayName();
    CharacterDisplayNameProvider characterNameProvider = new CharacterDisplayNameProvider(container);
    path = new ModelDisplayNameProvider(modelName, characterNameProvider).getDisplayName();
    this.description = MessageFormat.format(Messages.UneditedModelProblem_Description, new Object[] {
        modelName,
        characterNameProvider.getDisplayName() });
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getPath() {
    return path;
  }

  @Override
  public void showSource(IWorkbenchPage page) throws CoreException {
    new ResourceEditorOpenerExtensionPoint().open(page, CharacterModelEditorOpener.ID, modelResource);
  }
}