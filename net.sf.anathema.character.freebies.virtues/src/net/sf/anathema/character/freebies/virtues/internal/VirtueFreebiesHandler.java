package net.sf.anathema.character.freebies.virtues.internal;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.freebies.virtues.VirtueFreebiesConstants;
import net.sf.anathema.character.spiritualtraits.virtues.Virtues;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class VirtueFreebiesHandler extends UnconfiguredExecutableExtension implements IFreebiesHandler {

  private final IModelCollection models;
  private final IVirtueFreebieLimit virtueFreebieLimit;

  public VirtueFreebiesHandler() {
    this(ModelCache.getInstance());
  }

  public VirtueFreebiesHandler(IModelCollection models) {
    this(models, new VirtueFreebieLimit());
  }

  public VirtueFreebiesHandler(IModelCollection models, IVirtueFreebieLimit virtueFreebieLimit) {
    this.models = models;
    this.virtueFreebieLimit = virtueFreebieLimit;
  }

  @Override
  public String getCreditId() {
    return VirtueFreebiesConstants.FREEBIE_CREDIT;
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    ITraitCollectionModel traits = (ITraitCollectionModel) models.getModel(null);
    IBasicTrait[] allTraits = traits.getAllTraits();
    if (allTraits.length == 0) {
      return 0;
    }
    Virtues virtues = new Virtues(traits);
    int limit = virtueFreebieLimit.getFor(id);
    int cost = calculateCreditUsedByVirtues(virtues, limit);
    return Math.min(credit, cost);
  }

  private int calculateCreditUsedByVirtues(Virtues virtues, int limit) {
    int cost = 0;
    for (IBasicTrait virtue : virtues.getTraits()) {
      int usedCredit = calculateCreditUse(virtue, limit);
      cost += usedCredit;
    }
    return cost;
  }

  private int calculateCreditUse(IBasicTrait virtue, int limit) {
    int virtueValue = virtue.getCreationModel().getValue();
    int creditableValue = Math.min(limit, virtueValue);
    int usedCredit = Math.max(0, creditableValue - 1);
    return usedCredit;
  }
}