package net.sf.anathema.charms.character;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.IConfigurableViewElement;
import net.sf.anathema.character.core.model.IViewElementConfigurator;
import net.sf.anathema.charms.character.tree.SortedTreeDataCollection;
import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;
import net.sf.anathema.charms.tree.TreeDto;

public class TreeSelectionConfigurator extends AbstractExecutableExtension implements IViewElementConfigurator {

  private final Iterable<TreeDto> trees;

  public TreeSelectionConfigurator() {
    this(SortedTreeDataCollection.From(new CharmTreeExtensionPoint()));
  }

  public TreeSelectionConfigurator(Iterable<TreeDto> trees) {
    this.trees = trees;
  }

  @Override
  public void configure(IConfigurableViewElement viewElement) {
    for (TreeDto treeData : trees) {
      viewElement.addChild(new CharmTreeViewElement(viewElement, treeData));
    }
  }
}