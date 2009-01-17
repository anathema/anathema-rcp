package net.sf.anathema.character.freebies.abilities.problem;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.freebies.problem.ProblemMarkerDto;

public class AbilityProblemMarkerFactory {

  private final IDisplayNameProvider characterNameProvider;

  public AbilityProblemMarkerFactory(IModelIdentifier modelIdentifier) {
    this(CharacterDisplayNameProvider.CreateFrom(modelIdentifier));
  }

  public AbilityProblemMarkerFactory(IDisplayNameProvider characterNameProvider) {
    this.characterNameProvider = characterNameProvider;
  }

  public ProblemMarkerDto create(String description, String type) {
    IDisplayNameProvider modelNameProvider = new ModelDisplayNameProvider("Abilties", characterNameProvider);
    ProblemMarkerDto dto = new ProblemMarkerDto();
    dto.description = description;
    dto.editorOpener = "net.sf.anathema.character.abilities.editor";
    dto.path = modelNameProvider.getDisplayName();
    dto.type = type;
    return dto;
  }
}