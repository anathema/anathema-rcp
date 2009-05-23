package net.sf.anathema.character.core.fake;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModel;

public class DummyCharacter implements ICharacter {

  public final Map<String, IModel> modelsById = new HashMap<String, IModel>();
  public final DummyCharacterId charachterId = new DummyCharacterId();
  public final DummyCharacterType characterType = new DummyCharacterType();
  public final DummyCharacterTemplate characterTemplate = new DummyCharacterTemplate();

  @Override
  public String getDisplayName() {
    return null;
  }

  @Override
  public ICharacterTemplate getTemplate() {
    return characterTemplate;
  }

  @Override
  public IModel getModel(final String modelId) {
    return modelsById.get(modelId);
  }

  @Override
  public ICharacterType getCharacterType() {
    return characterType;
  }

  @Override
  public ICharacterId getId() {
    return charachterId;
  }
}