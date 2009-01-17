package net.sf.anathema.character.freebies.abilities.problem;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelChangeListener;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.abilities.FavoredFreebiesHandler;
import net.sf.anathema.character.freebies.abilities.UnrestrictedFreebiesHandler;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.freebies.problem.FreebiesProblemDto;
import net.sf.anathema.character.freebies.problem.FreebiesProblemFactory;
import net.sf.anathema.character.freebies.problem.HasUnspentFreebies;

public class AbilityFreebiesProblemFactory {

  private final ICreditManager creditManager = new CreditManager();
  private final IModelCollection modelCollection = ModelCache.getInstance();

  public IModelChangeListener createForUnrestrictedFreebies() {
    IFreebiesHandler freebiesHandler = new UnrestrictedFreebiesHandler(modelCollection, creditManager);
    FreebiesProblemDto dto = createDto(freebiesHandler);
    dto.description = "There are still unrestricted ability dots available.";
    dto.errorMessage = "Error creating unrestricted ability dots problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.abilities.marker.unrestricted"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  public IModelChangeListener createForCheapFreebies() {
    IFreebiesHandler freebiesHandler = new FavoredFreebiesHandler(modelCollection);
    FreebiesProblemDto dto = createDto(freebiesHandler);
    dto.description = "There are still cheap ability dots available.";
    dto.errorMessage = "Error creating cheap ability dots problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.abilities.marker.cheap"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  private FreebiesProblemDto createDto(IFreebiesHandler freebiesHandler) {
    FreebiesProblemDto dto = new FreebiesProblemDto();
    dto.hasProblem = new HasUnspentFreebies(creditManager, freebiesHandler);
    dto.editorOpener = "net.sf.anathema.character.abilities.editor";
    dto.modelName = "Abilities";
    dto.modelId = IAbilitiesPluginConstants.MODEL_ID;
    return dto;
  }
}