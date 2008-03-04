package net.sf.anathema.character.freebies.abilities;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.AbstractFavoredTraitCountHandler;

public class FavoredCountHandler extends AbstractFavoredTraitCountHandler {

  public FavoredCountHandler() {
    super(ModelCache.getInstance());
  }

  @Override
  public String getCreditId() {
    return "net.sf.anathema.character.abilities.count.favored"; //$NON-NLS-1$
  }

  @Override
  protected String getModelId() {
    return IAbilitiesPluginConstants.MODEL_ID;
  }
}