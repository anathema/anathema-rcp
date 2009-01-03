package net.sf.anathema.charms.character.selection;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.IConfigurableViewElement;
import net.sf.anathema.character.core.model.IViewElementConfigurator;
import net.sf.anathema.charms.character.tree.SortedTreeDataCollection;
import net.sf.anathema.charms.tree.CharmTreeProvider;
import net.sf.anathema.charms.tree.TreeDto;

public class TreeSelectionConfigurator extends AbstractExecutableExtension implements IViewElementConfigurator {

  private final Iterable<TreeDto> trees;
  private final IChooseTreePredicateFactory chooserFactory;

  public TreeSelectionConfigurator() {
    this(SortedTreeDataCollection.From(CharmTreeProvider.Create()), new ChooseTreePredicateFactory());
  }

  public TreeSelectionConfigurator(Iterable<TreeDto> trees, IChooseTreePredicateFactory chooserFactory) {
    this.trees = trees;
    this.chooserFactory = chooserFactory;
  }

  @Override
  public void configure(IConfigurableViewElement viewElement) {
    ICharacterId characterId = (ICharacterId) viewElement.getAdapter(ICharacterId.class);
    IPredicate<String> treeChooser = chooserFactory.create(characterId);
    for (TreeDto treeData : trees) {
      if (treeChooser.evaluate(treeData.id)) {
        viewElement.addChild(new TreeViewElement(viewElement, treeData));
      }
    }
  }
}