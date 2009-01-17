package net.sf.anathema.character.freebies.problem;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelChangeListener;
import net.sf.anathema.character.core.model.change.ModelResourceHandler;
import net.sf.anathema.character.freebies.plugin.ICharacterFreebiesPluginConstants;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class FreebiesProblemFactory implements IModelChangeListener {

  private final FreebiesProblemDto dto;

  public FreebiesProblemFactory(FreebiesProblemDto dto) {
    this.dto = dto;
  }

  @Override
  public void modelChanged(IModelIdentifier modelIdentifier) {
    try {
      adjustProblemMarking(modelIdentifier);
    }
    catch (Throwable t) {
      new Logger(ICharacterFreebiesPluginConstants.PLUGIN_ID).error(dto.errorMessage, t);
    }
  }

  private void adjustProblemMarking(IModelIdentifier modelIdentifier) throws CoreException {
    boolean isSupportedModel = modelIdentifier.getModelId().equals(dto.modelId);
    if (!isSupportedModel) {
      return;
    }
    adjustResourceMarking(modelIdentifier);
  }

  private void adjustResourceMarking(IModelIdentifier modelIdentifier) throws CoreException {
    IResource resource = new ModelResourceHandler().getResource(modelIdentifier);
    if (!resource.exists()) {
      return;
    }
    ProblemModel problemModel = new ProblemModel(modelIdentifier, dto.hasProblem);
    IResourceMarker marker = createProblemMarker(modelIdentifier, resource);
    problemModel.adjustMarking(marker);
  }

  private ProblemResourceMarker createProblemMarker(IModelIdentifier modelIdentifier, IResource resource) {
    ProblemDtoFactory problemFactory = new ProblemDtoFactory(modelIdentifier);
    ProblemMarkerDto problemDto = problemFactory.create(dto);
    return new ProblemResourceMarker(resource, problemDto);
  }
}