package net.sf.anathema.character.spiritualtraits.sheet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import org.junit.Before;
import org.junit.Test;

public class EssenceTraitEncoder_Test {

  private static final int ESSENCE_MAXIMUM = 6;
  private int essenceValue;
  private DummyGraphicalEncoder graphicalEncoder;
  private EssenceTraitEncoder encoder;

  @Before
  public void createEncoderWithMaximum6() throws Exception {
    graphicalEncoder = new DummyGraphicalEncoder();
    encoder = new EssenceTraitEncoder(graphicalEncoder, ESSENCE_MAXIMUM) {
      @Override
      protected int getEssenceValue(ICharacter character) {
        return essenceValue;
      }
    };
  }

  @Test
  public void drawsEssenceValueFilledCircles() throws Exception {
    this.essenceValue = 4;
    encoder.encode(null, new Bounds(0, 0, 100, 100));
    assertThat(graphicalEncoder.getDistinctFilledCircleCount(), is(4));
  }

  @Test
  public void respectsEssenceMaximum() throws Exception {
    this.essenceValue = ESSENCE_MAXIMUM + 1;
    encoder.encode(null, new Bounds(0, 0, 100, 100));
    assertThat(graphicalEncoder.getDistinctFilledCircleCount(), is(ESSENCE_MAXIMUM));
  }
}