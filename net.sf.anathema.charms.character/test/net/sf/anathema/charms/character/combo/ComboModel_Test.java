package net.sf.anathema.charms.character.combo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class ComboModel_Test {

  private final static String DESCRIPTION = "Ich bin der große böse Wolf";
  private ComboModel model;
  private Combo combo;

  @Before
  public void createModelWithCombo() throws Exception {
    model = new ComboModel();
    combo = new Combo();
    combo.description = DESCRIPTION;
    model.addCombo(combo);
  }

  @Test
  public void saveStateContainsComboEquivalent() throws Exception {
    List<Combo> saveState = model.getSaveState();
    assertThat(saveState.size(), is(1));
    assertThat(saveState.get(0), is(combo));
  }

  @Test
  public void combosCanBeChangedWithoutChangingSaveState() throws Exception {
    List<Combo> saveState = model.getSaveState();
    combo.description = "Anders";
    assertThat(saveState.get(0).description, is(DESCRIPTION));
  }
}