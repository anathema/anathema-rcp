package net.sf.anathema.charms.character;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.IConfigurableViewElement;
import net.sf.anathema.character.core.model.IViewElementConfigurator;
import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;
import net.sf.anathema.charms.tree.ICharmTreeProvider;

public class TreeSelectionConfigurator extends AbstractExecutableExtension implements IViewElementConfigurator {

  private final ICharmTreeProvider charmTrees;

  public TreeSelectionConfigurator() {
    this(new CharmTreeExtensionPoint());
  }

  public TreeSelectionConfigurator(ICharmTreeProvider charmTrees) {
    this.charmTrees = charmTrees;
  }

  @Override
  public void configure(IConfigurableViewElement viewElement) {
    for (String treeId : charmTrees.getTreeList()) {
      viewElement.addChild(new CharmTreeViewElement(viewElement, treeId));
    }
  }
}
