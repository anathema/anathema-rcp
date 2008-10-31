package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;

public interface IModelCache extends IModelCollection {

  void clearAllModels(ICharacterId id);
}