package net.sf.anathema.character.trait.validator;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModel;

@SuppressWarnings("nls")
public class DummyCharacter implements ICharacter {

  public final Map<String, IModel> modelsById = new HashMap<String, IModel>();

  @Override
  public String getDisplayName() {
    return null;
  }

  @Override
  public ICharacterTemplate getTemplate() {
    return new ICharacterTemplate() {

      @Override
      public boolean supportsModel(final String modelId) {
        return true;
      }

      @Override
      public String getName() {
        return "DummyTemplate";
      }

      @Override
      public String getId() {
        return "DummyTemplate.id";
      }

      @Override
      public String getCharacterTypeId() {
        return "CharacterType";
      }
    };
  }

  @Override
  public IModel getModel(final String modelId) {
    return modelsById.get(modelId);
  }

  public void addModel(String modelId, IModel model) {
    modelsById.put(modelId, model);
  }

  @Override
  public ICharacterType getCharacterType() {
    return null;
  }

  @Override
  public ICharacterId getId() {
    return null;
  }
}