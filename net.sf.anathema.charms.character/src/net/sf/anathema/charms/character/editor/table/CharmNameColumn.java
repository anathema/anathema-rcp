package net.sf.anathema.charms.character.editor.table;

import net.sf.anathema.charms.data.INameMap;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.swt.graphics.Image;

public class CharmNameColumn implements ITableColumn {

  private final INameMap nameMap;

  public CharmNameColumn(INameMap nameMap) {
    this.nameMap = nameMap;
  }

  @Override
  public String getHeader() {
    return "Charm";
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