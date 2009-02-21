package net.sf.anathema.character.trait.validator.where;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.validator.extension.StaticWhere;

import org.junit.Test;

public class WhereNot_Test {

  @Test
  public void returnsTrueOnNegativeEvaluationOfDelegate() throws Exception {
    assertThat(new WhereNot(new StaticWhere(false)).evaluate(null), is(true));
  }

  @Test
  public void returnsFalseOnPositiveEvaluationOfDelegate() throws Exception {
    assertThat(new WhereNot(new StaticWhere(true)).evaluate(null), is(false));
  }
}