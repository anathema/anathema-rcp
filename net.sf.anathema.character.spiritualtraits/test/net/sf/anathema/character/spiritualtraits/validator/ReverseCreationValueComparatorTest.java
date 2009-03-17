package net.sf.anathema.character.spiritualtraits.validator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class ReverseCreationValueComparatorTest {

  private ReverseCreationValueComparator comparator;
  private BasicTrait firstTrait;
  private BasicTrait secondTrait;

  @Before
  public void createComparator() throws Exception {
    this.comparator = new ReverseCreationValueComparator();
    this.firstTrait = new BasicTrait(new Identificate("first"));
    this.secondTrait = new BasicTrait(new Identificate("second"));
  }

  @Test
  public void comparesTwoBasicTraitsWithSameCreationValueAsZero() throws Exception {
    assertThat(comparator.compare(firstTrait, secondTrait), is(0));
  }

  @Test
  public void comparesHighValueToLowValueBelowZero() throws Exception {
    firstTrait.getCreationModel().setValue(4);
    secondTrait.getCreationModel().setValue(3);
    assertThat(comparator.compare(firstTrait, secondTrait) < 0, is(true));
  }

  @Test
  public void comparesLowValueToHighValueAboveZero() throws Exception {
    firstTrait.getCreationModel().setValue(3);
    secondTrait.getCreationModel().setValue(4);
    assertThat(comparator.compare(firstTrait, secondTrait) > 0, is(true));
  }
}