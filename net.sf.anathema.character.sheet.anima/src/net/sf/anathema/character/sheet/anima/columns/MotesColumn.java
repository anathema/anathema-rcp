package net.sf.anathema.character.sheet.anima.columns;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.anima.ColumnDescriptor;
import net.sf.anathema.character.sheet.anima.IAnimaColumn;

public class MotesColumn implements IAnimaColumn {

  private static final String[] ranges = new String[] { "1-3", "4-7", "8-10", "11-15", "16+" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

  public String getContent(int level, ICharacter character) {
    return ranges[level];
  }

  @Override
  public ColumnDescriptor getDescriptor() {
    return new ColumnDescriptor(0.15f, "Motes");
  }
}