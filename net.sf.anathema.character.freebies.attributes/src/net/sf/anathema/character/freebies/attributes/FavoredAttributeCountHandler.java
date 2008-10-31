package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.configuration.AbstractFavoredTraitCountHandler;

public class FavoredAttributeCountHandler extends AbstractFavoredTraitCountHandler {

  public FavoredAttributeCountHandler() {
    super(ModelCache.getInstance());
  }

  @Override
  public String getCreditId() {
    return "net.sf.anathema.character.attributes.count.favored"; //$NON-NLS-1$
  }

  @Override
  protected String getModelId() {
    return IAttributesPluginConstants.MODEL_ID;
  }
}