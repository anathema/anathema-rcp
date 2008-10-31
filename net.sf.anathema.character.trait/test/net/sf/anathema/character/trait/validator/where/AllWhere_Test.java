package net.sf.anathema.character.trait.validator.where;

import static org.junit.Assert.*;

import java.util.Arrays;

import net.sf.anathema.character.trait.validator.extension.StaticWhere;

import org.junit.Test;

public class AllWhere_Test {

  @Test
  public void evaluatedToTrueIfAllSubwheresAreTrue() throws Exception {
    AllWhere where = new AllWhere(toList(new StaticWhere(true), new StaticWhere(true)));
    assertTrue(where.evaluate(null, null, null, null));
  }

  @Test
  public void evaluatedToFalseIfAllOneSubwheresAreFalse() throws Exception {
    AllWhere where = new AllWhere(toList(new StaticWhere(false), new StaticWhere(true)));
    assertFalse(where.evaluate(null, null, null, null));
  }

  private Iterable<IWhere> toList(IWhere...wheres) {
    return Arrays.asList(wheres);
  }
}