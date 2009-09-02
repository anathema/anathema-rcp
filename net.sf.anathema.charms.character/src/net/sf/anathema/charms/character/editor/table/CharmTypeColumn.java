package net.sf.anathema.charms.character.editor.table;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.swt.graphics.Image;

public class CharmTypeColumn implements ITableColumn {

  private final ICharmDataMap dataMap;

  public CharmTypeColumn(ICharmDataMap dataMap) {
    this.dataMap = dataMap;
  }

  @Override
  public String getHeader() {
    return "Type";
  }

  @Override
  public Image getImage(Object element) {
    return null;
  }

  @Override
  public String getText(Object element) {
    ICharmId charmId = (ICharmId) element;
    CharmDto data = dataMap.getData(charmId);
    return data.type;
  }

  @Override
  public int getWidth() {
    return 90;
  }
}