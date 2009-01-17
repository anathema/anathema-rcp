package net.sf.anathema.character.freebies.abilities.problem;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelChangeListener;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.change.ModelResourceHandler;
import net.sf.anathema.character.freebies.abilities.AbilitiesFreebiesPlugin;
import net.sf.anathema.character.freebies.abilities.UnrestrictedFreebiesHandler;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.freebies.problem.ProblemResourceMarker;
import net.sf.anathema.character.freebies.problem.HasUnspentFreebies;
import net.sf.anathema.character.freebies.problem.IResourceMarker;
import net.sf.anathema.character.freebies.problem.ProblemMarkerDto;
import net.sf.anathema.character.freebies.problem.ProblemModel;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class AbilityFreebiesProblemFactory implements IModelChangeListener {

  private final IPredicate<IModelIdentifier> hasUnspentFreebies;
  private final String markerType;
  private final String description;

  public static IModelChangeListener CreateForUnrestrictedFreebies() {
    ICreditManager creditManager = new CreditManager();
    IModelCollection modelCollection = ModelCache.getInstance();
    IFreebiesHandler freebiesHandler = new UnrestrictedFreebiesHandler(modelCollection, creditManager);
    HasUnspentFreebies unspentFreebiesPredicate = new HasUnspentFreebies(creditManager, freebiesHandler);
    return new AbilityFreebiesProblemFactory(
        unspentFreebiesPredicate,
        "net.sf.anathema.character.freebies.abilities.marker.unrestricted",
        "There are still unrestricted ability dots available.");
  }

  public AbilityFreebiesProblemFactory(
      IPredicate<IModelIdentifier> hasUnspentFreebies,
      String markerType,
      String description) {
    this.hasUnspentFreebies = hasUnspentFreebies;
    this.markerType = markerType;
    this.description = description;
  }

  @Override
  public void modelChanged(IModelIdentifier modelIdentifier) {
    try {
      adjustProblemMarking(modelIdentifier);
    }
    catch (Throwable t) {
      new Logger(AbilitiesFreebiesPlugin.ID).error("Error creating abilities problem marker.", t);
    }
  }

  private void adjustProblemMarking(IModelIdentifier modelIdentifier) throws CoreException {
    boolean isModel = modelIdentifier.getModelId().equals(IAbilitiesPluginConstants.MODEL_ID);
    if (!isModel) {
      return;
    }
    adjustResourceMarking(modelIdentifier);
  }

  private void adjustResourceMarking(IModelIdentifier modelIdentifier) throws CoreException {
    IResource resource = new ModelResourceHandler().getResource(modelIdentifier);
    if (!resource.exists()) {
      return;
    }
    ProblemModel problemModel = new ProblemModel(modelIdentifier, hasUnspentFreebies);
    IResourceMarker marker = createProblemMarker(modelIdentifier, resource);
    problemModel.adjustMarking(marker);
  }

  private ProblemResourceMarker createProblemMarker(IModelIdentifier modelIdentifier, IResource resource) {
    AbilityProblemMarkerFactory problemFactory = new AbilityProblemMarkerFactory(modelIdentifier);
    ProblemMarkerDto problemDto = problemFactory.create(description, markerType);
    return new ProblemResourceMarker(resource, problemDto);
  }
}