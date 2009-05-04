package net.sf.anathema.character.sheet.anima.columns;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.anima.IAnimaColumn;
import net.sf.anathema.character.sheet.anima.util.ColumnDescriptor;

public class StealthColumn implements IAnimaColumn {

  @Override
  public ColumnDescriptor getDescriptor() {
    return new ColumnDescriptor(0.25f, "Stealth");
  }

  public String getContent(int level, ICharacter character) {
    switch (level) {
      case 0:
        return getLevelOneStealth();
      case 1:
        return getLevelTwoStealth();
      case 2:
        return getLevelThreeStealth();
      default:
        return getImpossibleStealth();
    }
  }

  protected String getLevelThreeStealth() {
    return getImpossibleStealth();
  }

  protected String getLevelTwoStealth() {
    return getDifficultStealth();
  }

  private String getLevelOneStealth() {
    return getNormalStealth();
  }

  private String getDifficultStealth() {
    return "+2"; //$NON-NLS-1$
  }

  private String getImpossibleStealth() {
    return "Impossible";
  }

  protected String getNormalStealth() {
    return "Normal";
  }
}