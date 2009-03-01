package net.sf.anathema.character.spiritualtraits.model;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModel;

public class DummyCharacter implements ICharacter {

  @Override
  public String getDisplayName() {
    // TODO Auto-generated method stub
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ICharacterType getCharacterType() {
    // TODO Auto-generated method stub
    return null;
  }
}