package net.sf.anathema.character.freebies.problem;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class HasUnspentFreebies implements IPredicate<IModelIdentifier> {

  private final ICreditManager creditManager;
  private final IFreebiesHandler freebiesHandler;

  public HasUnspentFreebies(ICreditManager creditManager, IFreebiesHandler freebiesHandler) {
    this.creditManager = creditManager;
    this.freebiesHandler = freebiesHandler;
  }

  @Override
  public boolean evaluate(IModelIdentifier identifier) {
    ICharacterId characterId = identifier.getCharacterId();
    int credit = creditManager.getCredit(characterId, freebiesHandler.getCreditId());
    int spentPoints = freebiesHandler.getPoints(characterId, credit);
    return spentPoints < credit;
  }
}