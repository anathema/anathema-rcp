package charactertype.solar.acceptance;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.caste.ICaste;

public final class CasteToPrintNameTransformer implements ITransformer<ICaste, String> {
  @Override
  public String transform(ICaste caste) {
    return caste.getPrintName();
  }
}