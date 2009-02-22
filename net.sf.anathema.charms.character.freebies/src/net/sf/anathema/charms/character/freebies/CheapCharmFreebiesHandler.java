package net.sf.anathema.charms.character.freebies;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.charms.character.freebies.plugin.ICharmFreebiesConstants;

public class CheapCharmFreebiesHandler extends UnconfiguredExecutableExtension implements IFreebiesHandler {

  private final IModelCollection modelProvider;

  public CheapCharmFreebiesHandler() {
    this(ModelCache.getInstance());
  }

  public CheapCharmFreebiesHandler(IModelCollection modelProvider) {
    this.modelProvider = modelProvider;
  }

  @Override
  public String getCreditId() {
    return ICharmFreebiesConstants.CHEAP_CREDIT_ID;
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    ICount cheapCount = CheapCharmCount.From(modelProvider, id);
    return new CheapFreebiesCount(cheapCount, credit).count();
  }
}