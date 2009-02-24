package net.sf.anathema.character.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

public class TraitAssert {

  public static void assertCannotLower(IInteractiveTrait trait) {
    int value = trait.getValue();
    modifyValue(trait, -1);
    assertThat(trait.getValue(), is(value));
  }

  public static void assertCanRaise(IInteractiveTrait trait) {
    int targetValue = modifyValue(trait, 1);
    assertThat(trait.getValue(), is(targetValue));
  }

  public static void increaseByOne(IInteractiveTrait trait) {
    modifyValue(trait, +1);
  }

  private static int modifyValue(IInteractiveTrait trait, int increment) {
    int value = trait.getValue();
    int targetValue = value + increment;
    trait.setValue(targetValue);
    return targetValue;
  }
}