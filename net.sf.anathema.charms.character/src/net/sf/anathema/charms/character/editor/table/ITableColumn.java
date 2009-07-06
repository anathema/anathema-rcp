package net.sf.anathema.charms.character.editor.table;

import org.eclipse.swt.graphics.Image;

public interface ITableColumn {

  public int getWidth();

  public String getHeader();

  public Image getImage(Object element);

  public String getText(Object element);
}