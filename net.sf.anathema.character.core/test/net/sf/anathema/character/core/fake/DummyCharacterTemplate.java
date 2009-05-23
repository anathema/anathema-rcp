package net.sf.anathema.character.core.fake;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterTemplate;

@SuppressWarnings("nls")
public final class DummyCharacterTemplate implements ICharacterTemplate {
  public String name = "DummyTemplate";
  public String id = "DummyTemplate.id";
  public String typeId = DummyCharacterType.DEFAULT_TYPE;
  public final List<String> supportedModelIds = new ArrayList<String>();
  public final List<String> unsupportedModelIds = new ArrayList<String>();
  public boolean defaultModelSupport = true;

  @Override
  public boolean supportsModel(final String modelId) {
    if (supportedModelIds.contains(modelId)) {
      return true;
    }
    if (unsupportedModelIds.contains(modelId)) {
      return false;
    }
    return defaultModelSupport;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getCharacterTypeId() {
    return typeId;
  }
}