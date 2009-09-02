package net.sf.anathema.charms.character.editor.combo;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.charms.data.INameMap;
import net.sf.anathema.charms.tree.ICharmId;

public class ToNameTransformer implements ITransformer<ICharmId, String> {

  private final INameMap charmNameMap;

  public ToNameTransformer(INameMap charmNameMap) {
    this.charmNameMap = charmNameMap;
  }

  @Override
  public String transform(ICharmId charmId) {
    return charmNameMap.getNameFor(charmId);
  }
}
