package net.sf.anathema.character.core.model.internal;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.IModelSupporter;

public class DefaultModelSupporter implements IModelSupporter {

  private final String modelId;

  public DefaultModelSupporter(String modelId) {
    this.modelId = modelId;
  }
  
  @Override
  public boolean isSupportedBy(ICharacterTemplate template) {
    return template.supportsModel(modelId);
  }
}