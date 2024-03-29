package net.sf.anathema.character.core.character;

import org.eclipse.core.resources.IFolder;

public class ModelIdentifier implements IModelIdentifier {

  private final ICharacterId characterId;
  private final String modelId;

  public ModelIdentifier(IFolder folder, String modelId) {
    this(new CharacterId(folder), modelId);
  }

  public ModelIdentifier(ICharacterId characterId, String modelId) {
    this.characterId = characterId;
    this.modelId = modelId;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ModelIdentifier)) {
      return false;
    }
    ModelIdentifier other = (ModelIdentifier) obj;
    return characterId.equals(other.getCharacterId()) && modelId.equals(other.getModelId());
  }

  @Override
  public int hashCode() {
    return characterId.hashCode() + 7 * modelId.hashCode();
  }

  @Override
  public String toString() {
    return characterId.toString() + ", " + modelId; //$NON-NLS-1$
  }

  public ICharacterId getCharacterId() {
    return characterId;
  }

  public String getModelId() {
    return modelId;
  }
}