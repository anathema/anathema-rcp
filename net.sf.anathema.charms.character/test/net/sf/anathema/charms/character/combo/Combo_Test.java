package net.sf.anathema.charms.character.combo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.tree.CharmId;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class Combo_Test {

  private Combo combo;

  @Before
  public void createCombo() throws Exception {
    combo = new Combo();
    combo.name = "Hossa";
    combo.description = "Horst";
    combo.charms.add(new CharmId("pattern", "trait"));
  }

  @Test
  public void doesNewInstanceFromOtherCombo() throws Exception {
    assertThat(Combo.CreateFrom(combo), is(not(sameInstance(combo))));
  }

  @Test
  public void createsEqualInstanceFormOtherCombo() throws Exception {
    assertThat(Combo.CreateFrom(combo), is(combo));
  }
}