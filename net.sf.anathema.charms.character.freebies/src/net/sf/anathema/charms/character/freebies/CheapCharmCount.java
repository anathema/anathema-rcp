package net.sf.anathema.charms.character.freebies;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.points.CheapCharmPredicate;

public class CheapCharmCount implements ICount {

  private final IPredicate<String> cheapCharmPredicate;
  private final ICharmModel charms;

  public static ICount From(IModelCollection modelCollection, ICharacterId id) {
    IPredicate<String> cheapPredicate = CheapCharmPredicate.From(modelCollection, id);
    ICharmModel charmModel = CharmModel.getFrom(modelCollection, id);
    return new CheapCharmCount(charmModel, cheapPredicate);
  }

  public CheapCharmCount(ICharmModel charms, IPredicate<String> cheapCharmPredicate) {
    this.charms = charms;
    this.cheapCharmPredicate = cheapCharmPredicate;
  }

  public int count() {
    int count = 0;
    for (String charmId : charms.getCreationLearnedCharms()) {
      if (cheapCharmPredicate.evaluate(charmId)) {
        count++;
      }
    }
    return count;
  }
}