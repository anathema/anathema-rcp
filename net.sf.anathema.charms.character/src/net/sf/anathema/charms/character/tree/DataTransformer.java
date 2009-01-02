package net.sf.anathema.charms.character.tree;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.charms.tree.ITreeDataMap;
import net.sf.anathema.charms.tree.TreeDto;

public final class DataTransformer implements ITransformer<String, TreeDto> {
  private final ITreeDataMap dataProvider;

  public DataTransformer(ITreeDataMap dataProvider) {
    this.dataProvider = dataProvider;
  }

  @Override
  public TreeDto transform(String treeId) {
    return dataProvider.getData(treeId);
  }
}