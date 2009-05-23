package net.sf.anathema.character.spiritualtraits.editor;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class SpiritualTraitEditorInputConfiguration_Test {

  private SpiritualTraitsEditorInputConfiguration configuration;

  @Before
  public void createConfiguration() throws Exception {
    this.configuration = new SpiritualTraitsEditorInputConfiguration();
  }

  @Test
  public void hasWillpowerMaximumOf10() throws Exception {
    assertThatTraitHasMaximum("Willpower", 10);
  }

  @Test
  public void hasCompassionMaximumOf5() throws Exception {
    assertThatTraitHasMaximum("Compassion", 5);
  }

  @Test
  public void hasValorMaximumOf5() throws Exception {
    assertThatTraitHasMaximum("Valor", 5);
  }

  @Test
  public void hasEssenceMaximumOf5() throws Exception {
    assertThatTraitHasMaximum("Essence", 10);
  }

  private void assertThatTraitHasMaximum(String id, int maximum) {
    assertThat(configuration.getTraitMaximum(new Identificate(id)), is(maximum));
  }
}