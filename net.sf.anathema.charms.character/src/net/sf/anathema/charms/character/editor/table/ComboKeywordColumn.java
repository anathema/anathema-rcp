package net.sf.anathema.charms.character.editor.table;

import net.sf.anathema.charms.character.combo.ComboCharm;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.lang.ConcatenateString;

import org.eclipse.swt.graphics.Image;

public class ComboKeywordColumn implements ITableColumn {

  private final ICharmDataMap dataMap;

  public ComboKeywordColumn(ICharmDataMap dataMap) {
    this.dataMap = dataMap;
  }

  @Override
  public String getHeader() {
    return "Keyword";
  }

  @Override
  public Image getImage(Object element) {
    return null;
  }

  @Override
  public String getText(Object element) {
    ComboCharm comboCharm = createComboCharm(element);
    ConcatenateString comboKeywords = ConcatenateString.CommaSeparated();
    for (String keyword : comboCharm.getComboKeywords()) {
      comboKeywords.concatenate(keyword);
    }
    return comboKeywords.create();
  }

  private ComboCharm createComboCharm(Object element) {
    ICharmId charmId = (ICharmId) element;
    CharmDto data = dataMap.getData(charmId);
    return new ComboCharm(data);
  }

  @Override
  public int getWidth() {
    return 100;
  }
}