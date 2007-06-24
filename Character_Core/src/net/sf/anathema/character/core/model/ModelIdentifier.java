package net.sf.anathema.character.core.model;

import org.eclipse.core.resources.IFolder;

public class ModelIdentifier {

  private final IFolder characterFolder;
  private final String modelId;

  public ModelIdentifier(IFolder characterFolder, String modelId) {
    this.characterFolder = characterFolder;
    this.modelId = modelId;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ModelIdentifier)) {
      return false;
    }
    ModelIdentifier other = (ModelIdentifier) obj;
    return characterFolder.equals(other.characterFolder) && modelId.equals(other.modelId);
  }
  
  @Override
  public int hashCode() {
    return characterFolder.hashCode() + 7 * modelId.hashCode();
  }
}