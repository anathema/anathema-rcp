package net.sf.anathema.charms.character.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.charms.character.special.IVirtualCharms;
import net.sf.anathema.charms.tree.ITreeProvider;

public class NonVirtualGenericCharmTree implements IGenericCharmTree {

  private final ITreeProvider treeProvider;
  private final IVirtualCharms virtualCharms;

  public NonVirtualGenericCharmTree(ITreeProvider treeProvider, IVirtualCharms virtualCharms) {
    this.treeProvider = treeProvider;
    this.virtualCharms = virtualCharms;
  }

  @Override
  public Collection<String> getGenericIdPattersFor(String characterType) {
    List<String> genericCharms = treeProvider.getGenericCharms(characterType);
    return extractRealPatterns(genericCharms);
  }

  private Collection<String> extractRealPatterns(List<String> genericCharms) {
    List<String> unvirtualCharms = new ArrayList<String>();
    for (String pattern : genericCharms) {
      if (!virtualCharms.isVirtual(pattern)) {
        unvirtualCharms.add(pattern);
      }
    }
    return unvirtualCharms;
  }
}