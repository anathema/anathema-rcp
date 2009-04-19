package net.sf.anathema.character.freebies.configuration;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.IBasicTrait;

public abstract class AbstractLimitedFreebiesHandler extends UnconfiguredExecutableExtension implements
    IFreebiesHandler {

  private final ITraitFreebieLimit freebieLimit;
  private final int baseValue;

  public AbstractLimitedFreebiesHandler(ITraitFreebieLimit traitFreebieLimit, int baseValue) {
    this.freebieLimit = traitFreebieLimit;
    this.baseValue = baseValue;
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    int limit = freebieLimit.getFor(id);
    int cost = calculateCreditUse(id, limit);
    return Math.min(credit, cost);
  }

  protected abstract Iterable<IBasicTrait> getTraits(ICharacterId id);

  private int calculateCreditUse(ICharacterId id, int limit) {
    int cost = 0;
    for (IBasicTrait trait : getTraits(id)) {
      int usedCredit = calculateCreditUse(trait, limit);
      cost += usedCredit;
    }
    return cost;
  }

  private int calculateCreditUse(IBasicTrait trait, int limit) {
    int virtueValue = trait.getCreationModel().getValue();
    int creditableValue = Math.min(limit, virtueValue);
    return Math.max(0, creditableValue - baseValue);
  }
}