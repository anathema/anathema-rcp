package net.sf.anathema.character.caste.model;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.caste.ICaste;

final class CasteToPrintNameTransformer implements ITransformer<ICaste, String> {
  @Override
  public String transform(ICaste caste) {
    return caste.getPrintName();
  }
}