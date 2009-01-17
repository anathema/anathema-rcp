package net.sf.anathema.character.freebies.problem;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.core.runtime.CoreException;

public class ProblemModel {

  private final IModelIdentifier modelIdentifier;
  private final IPredicate<IModelIdentifier> hasProblem;

  public ProblemModel(IModelIdentifier modelIdentifier, IPredicate<IModelIdentifier> hasProblem) {
    this.modelIdentifier = modelIdentifier;
    this.hasProblem = hasProblem;
  }

  public void adjustMarking(IResourceMarker problemMarker) throws CoreException {
    boolean markerExists = problemMarker.exists();
    boolean needsMarking = hasProblem.evaluate(modelIdentifier);
    if (needsMarking == markerExists) {
      return;
    }
    if (hasProblem.evaluate(modelIdentifier)) {
      problemMarker.create();
    }
    else {
      problemMarker.delete();
    }
  }
}