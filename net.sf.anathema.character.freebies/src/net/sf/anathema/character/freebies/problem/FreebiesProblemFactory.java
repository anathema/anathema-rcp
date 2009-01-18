package net.sf.anathema.character.freebies.problem;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.FileContentHandle;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelChangeListener;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.change.ModelResourceHandler;
import net.sf.anathema.character.freebies.plugin.ICharacterFreebiesPluginConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;

public class FreebiesProblemFactory<M extends IModel> implements IModelChangeListener {

  private final FreebiesProblemTemplate<M> dto;

  public FreebiesProblemFactory(FreebiesProblemTemplate<M> dto) {
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

  private void adjustProblemMarking(IModelIdentifier modelIdentifier) throws Exception {
    boolean isSupportedModel = modelIdentifier.getModelId().equals(dto.modelId);
    if (!isSupportedModel) {
      return;
    }
    adjustResourceMarking(modelIdentifier);
  }

  private void adjustResourceMarking(IModelIdentifier modelIdentifier) throws Exception {
    IResource resource = new ModelResourceHandler().getResource(modelIdentifier);
    if (!resource.exists()) {
      createModelFile(modelIdentifier, resource);
    }
    ProblemModel problemModel = new ProblemModel(modelIdentifier, dto.hasProblem);
    IResourceMarker marker = createProblemMarker(modelIdentifier, resource);
    problemModel.adjustMarking(marker);
  }

  private void createModelFile(IModelIdentifier modelIdentifier, IResource resource) throws Exception {
    IContentHandle content = new FileContentHandle((IFile) resource);
    M model = (M) ModelCache.getInstance().getModel(modelIdentifier);
    new ItemFileWriter().save(content, dto.persister, model, new NullProgressMonitor());
  }

  private ProblemResourceMarker createProblemMarker(IModelIdentifier modelIdentifier, IResource resource) {
    ProblemTemplateFactory problemFactory = new ProblemTemplateFactory(modelIdentifier);
    ProblemMarkerTemplate problemDto = problemFactory.create(dto);
    return new ProblemResourceMarker(resource, problemDto);
  }

  @Override
  public void modelCreated(IModelIdentifier identifier) {
    modelChanged(identifier);
  }
}