package net.sf.anathema.character.freebies.abilities.problem;

import java.util.List;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelChangeListener;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.abilities.FavoredCountHandler;
import net.sf.anathema.character.freebies.abilities.FavoredFreebiesHandler;
import net.sf.anathema.character.freebies.abilities.UnrestrictedFreebiesHandler;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.freebies.problem.FreebiesProblemFactory;
import net.sf.anathema.character.freebies.problem.FreebiesProblemTemplate;
import net.sf.anathema.character.freebies.problem.HasUnspentFreebies;

public class AbilityFreebiesProblemFactory {

  private final ICreditManager creditManager = new CreditManager();
  private final IModelCollection modelCollection = ModelCache.getInstance();

  public void addAll(List<IModelChangeListener> modelListeners) {
    modelListeners.add(createForUnrestrictedFreebies());
    modelListeners.add(createForCheapFreebies());
    modelListeners.add(createFavoredCount());
  }

  public IModelChangeListener createForUnrestrictedFreebies() {
    IFreebiesHandler freebiesHandler = new UnrestrictedFreebiesHandler(modelCollection, creditManager);
    FreebiesProblemTemplate dto = createDto(freebiesHandler);
    dto.description = "There are still unrestricted ability dots available.";
    dto.errorMessage = "Error creating unrestricted ability dots problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.abilities.marker.unrestricted"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  public IModelChangeListener createForCheapFreebies() {
    IFreebiesHandler freebiesHandler = new FavoredFreebiesHandler(modelCollection);
    FreebiesProblemTemplate dto = createDto(freebiesHandler);
    dto.description = "There are still cheap ability dots available.";
    dto.errorMessage = "Error creating cheap ability dots problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.abilities.marker.cheap"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  public IModelChangeListener createFavoredCount() {
    IFreebiesHandler freebiesHandler = new FavoredCountHandler();
    FreebiesProblemTemplate dto = createDto(freebiesHandler);
    dto.description = "There are still favored abilities unchosen.";
    dto.errorMessage = "Error creating favored ability count problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.abilities.marker.favoredcount"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  private FreebiesProblemTemplate createDto(IFreebiesHandler freebiesHandler) {
    FreebiesProblemTemplate dto = new FreebiesProblemTemplate();
    dto.hasProblem = new HasUnspentFreebies(creditManager, freebiesHandler);
    dto.editorOpener = "net.sf.anathema.character.modelopener"; //$NON-NLS-1$
    dto.modelName = "Abilities";
    dto.modelId = IAbilitiesPluginConstants.MODEL_ID;
    return dto;
  }
}