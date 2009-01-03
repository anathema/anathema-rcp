package net.sf.anathema.charms.tree;

public class CharmIdLookup {
  private final ITreeProvider treeProvider;
  private final TreeLookup treeLookup;

  public CharmIdLookup(ITreeProvider treeProvider) {
    this.treeProvider = treeProvider;
    this.treeLookup = new TreeLookup(treeProvider);
  }

  public ICharmId getCharmId(String completeId) {
    String treeId = treeLookup.getTreeId(completeId);
    TreeDto dto = treeProvider.getData(treeId);
    return new CharmId(completeId.replaceAll(dto.primaryTrait, "{0}"), dto.primaryTrait); //$NON-NLS-1$
  }
}
