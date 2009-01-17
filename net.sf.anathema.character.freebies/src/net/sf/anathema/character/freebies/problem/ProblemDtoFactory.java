package net.sf.anathema.character.freebies.problem;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

public class ProblemDtoFactory {

  private final IDisplayNameProvider characterNameProvider;

  public ProblemDtoFactory(IModelIdentifier modelIdentifier) {
    this(CharacterDisplayNameProvider.CreateFrom(modelIdentifier));
  }

  public ProblemDtoFactory(IDisplayNameProvider characterNameProvider) {
    this.characterNameProvider = characterNameProvider;
  }

  public ProblemMarkerDto create(FreebiesProblemDto problemDto) {
    IDisplayNameProvider modelNameProvider = new ModelDisplayNameProvider(problemDto.modelName, characterNameProvider);
    ProblemMarkerDto dto = new ProblemMarkerDto();
    dto.description = problemDto.description;
    dto.editorOpener = problemDto.editorOpener;
    dto.path = modelNameProvider.getDisplayName();
    dto.type = problemDto.markerType;
    return dto;
  }
}