package net.sf.anathema.character.caste.persistence;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.caste.model.ICaste;

final class CasteToIdTransformer implements ITransformer<ICaste, String> {
  @Override
  public String transform(ICaste caste) {
    return caste.getId();
  }
}