package net.sf.anathema.character.freebies.problem;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

public class ProblemTemplateFactory {

  private final IDisplayNameProvider characterNameProvider;

  public ProblemTemplateFactory(IModelIdentifier modelIdentifier) {
    this(CharacterDisplayNameProvider.CreateFrom(modelIdentifier));
  }

  public ProblemTemplateFactory(IDisplayNameProvider characterNameProvider) {
    this.characterNameProvider = characterNameProvider;
  }

  public ProblemMarkerTemplate create(FreebiesProblemTemplate problemDto) {
    IDisplayNameProvider modelNameProvider = new ModelDisplayNameProvider(problemDto.modelName, characterNameProvider);
    ProblemMarkerTemplate dto = new ProblemMarkerTemplate();
    dto.description = problemDto.description;
    dto.editorOpener = problemDto.editorOpener;
    dto.path = modelNameProvider.getDisplayName();
    dto.type = problemDto.markerType;
    return dto;
  }
}