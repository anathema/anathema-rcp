package net.sf.anathema.charms.view;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.data.ICharmDataProvider;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.jface.viewers.LabelProvider;

public class CharmLabelProvider extends LabelProvider {

  private final ICharmDataProvider dataProvider;

  public CharmLabelProvider(ICharmDataProvider dataProvider) {
    this.dataProvider = dataProvider;
  }

  @Override
  public String getText(Object element) {
    if (element instanceof CharmPrerequisite) {
      return null;
    }
    return dataProvider.getDisplayName((ICharmId)element);
  }
}