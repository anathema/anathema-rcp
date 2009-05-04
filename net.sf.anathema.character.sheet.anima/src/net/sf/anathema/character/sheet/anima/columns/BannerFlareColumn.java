package net.sf.anathema.character.sheet.anima.columns;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.anima.IAnimaColumn;
import net.sf.anathema.character.sheet.anima.util.ColumnDescriptor;

public class BannerFlareColumn implements IAnimaColumn {

  @Override
  public String getContent(int level, ICharacter character) {
    // ICharacterType type = character.getTemplate().getTemplateType().getCharacterType();
    //    String descriptionPrefix = "Sheet.AnimaTable.Description." + type; //$NON-NLS-1$
    return "";
  }

  @Override
  public ColumnDescriptor getDescriptor() {
    return new ColumnDescriptor(0.6f, "Banner Flare");
  }
}