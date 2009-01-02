package net.sf.anathema.charms.character.tree;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.charms.tree.ITreeDtoMap;
import net.sf.anathema.charms.tree.TreeDto;

public final class DataTransformer implements ITransformer<String, TreeDto> {
  private final ITreeDtoMap dataProvider;

  public DataTransformer(ITreeDtoMap dataProvider) {
    this.dataProvider = dataProvider;
  }

  @Override
  public TreeDto transform(String treeId) {
    return dataProvider.getData(treeId);
  }
}