package net.sf.anathema.character.derivedtraits;

import net.sf.anathema.character.trait.display.IDisplayTrait;

public class CharacterUtilties {

  public static int getRoundUpDv(IDisplayTrait... traits) {
    int sum = getTotalValue(traits);
    return (int) Math.ceil(sum * 0.5);
  }

  public static int getRoundDownDv(IDisplayTrait... traits) {
    int sum = getTotalValue(traits);
    return sum / 2;
  }

  public static int getTotalValue(IDisplayTrait... traits) {
    int sum = 0;
    for (IDisplayTrait trait : traits) {
      sum += trait.getValue();
    }
    return sum;
  }
}