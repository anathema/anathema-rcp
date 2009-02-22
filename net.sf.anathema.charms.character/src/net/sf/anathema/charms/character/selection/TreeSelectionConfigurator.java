package net.sf.anathema.charms.character.selection;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.IConfigurableViewElement;
import net.sf.anathema.character.core.model.IViewElementConfigurator;
import net.sf.anathema.charms.character.tree.SortedTreeDataCollection;
import net.sf.anathema.charms.extension.CharmProvidingExtensionPoint;
import net.sf.anathema.charms.tree.TreeDto;

public class TreeSelectionConfigurator extends UnconfiguredExecutableExtension implements IViewElementConfigurator {

  private final Iterable<TreeDto> trees;
  private final IChooseTreePredicateFactory chooserFactory;

  public TreeSelectionConfigurator() {
    this(SortedTreeDataCollection.From(CharmProvidingExtensionPoint.CreateTreeProvider()), new ChooseTreePredicateFactory());
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