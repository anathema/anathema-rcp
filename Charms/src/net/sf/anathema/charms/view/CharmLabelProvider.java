package net.sf.anathema.charms.view;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.data.ICharmDataProvider;

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
    String charmId = element.toString();
    return dataProvider.getDisplayName(charmId);
  }
}