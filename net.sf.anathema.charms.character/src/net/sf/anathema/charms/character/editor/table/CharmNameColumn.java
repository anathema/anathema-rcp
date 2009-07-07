package net.sf.anathema.charms.character.editor.table;

import net.sf.anathema.charms.data.INameMap;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.swt.graphics.Image;

public class CharmNameColumn implements ITableColumn {

  private final INameMap nameMap;
  private final String header;

  public CharmNameColumn(INameMap nameMap, String header) {
    this.nameMap = nameMap;
    this.header = header;
  }

  @Override
  public String getHeader() {
    return header;
  }

  @Override
  public Image getImage(Object element) {
    return null;
  }

  @Override
  public String getText(Object element) {
    ICharmId charmId = (ICharmId) element;
    return nameMap.getNameFor(charmId);
  }

  @Override
  public int getWidth() {
    return 300;
  }
}