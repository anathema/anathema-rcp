package net.sf.anathema.charms.character.freebies;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.charms.character.freebies.plugin.ICharmFreebiesConstants;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;

public class UnrestrictedCharmHandler extends AbstractExecutableExtension implements IFreebiesHandler {

  private final IModelCollection modelCollection;
  private final ICreditManager creditManager;

  public UnrestrictedCharmHandler() {
    modelCollection = ModelCache.getInstance();
    creditManager = new CreditManager();
  }

  @Override
  public String getCreditId() {
    return ICharmFreebiesConstants.UNRESTRICTED_CREDIT_ID;
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    int cheapFreebies = countCheapFreebies(id);
    ICount charmCount = createCharmCount(id);
    return new UnrestrictedFreebiesCount(charmCount, cheapFreebies, credit).count();
  }

  private ICount createCharmCount(ICharacterId id) {
    ICharmModel charmModel = CharmModel.getFrom(modelCollection, id);
    return new UnrestrictedCharmCount(charmModel);
  }

  private int countCheapFreebies(ICharacterId id) {
    CheapCharmFreebiesHandler cheapHandler = new CheapCharmFreebiesHandler(modelCollection);
    int cheapCredit = creditManager.getCredit(id, cheapHandler.getCreditId());
    return cheapHandler.getPoints(id, cheapCredit);
  }
}