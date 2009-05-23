package net.sf.anathema.charms.character.evaluation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.editor.CharacterCharmVisuals;
import net.sf.anathema.charms.character.fake.DummyCharmPreferences;
import net.sf.anathema.charms.character.fake.DummyCharmSelectionControl;
import net.sf.anathema.charms.character.fake.DummyVirtualCharms;
import net.sf.anathema.charms.character.modify.CharmLearner;
import net.sf.anathema.charms.character.test.AbstractCharmCharacterTest;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class CharacterCharmVisuals_Test extends AbstractCharmCharacterTest {

  private static final ICharmId SELECTED_CHARM = new CharmId("selectedCharm", null);
  private DummyCharmSelectionControl charmSelectionControl;
  private CharacterCharmVisuals visuals;

  @Before
  public void createConnnectedVisuals() throws Exception {
    CharmLearner charmLearner = new CharmLearner(new DummyVirtualCharms(), new DummyCharmPreferences(), character);
    visuals = new CharacterCharmVisuals(charmLearner);
    charmSelectionControl = new DummyCharmSelectionControl();
    visuals.connect(charmSelectionControl);
  }

  @Test
  public void learnsCharmOnSelection() throws Exception {
    charmSelectionControl.control.forAllDo(new CharmSelectionNotificationClosure(SELECTED_CHARM));
    assertThat(getCharmModel().isCreationLearned(SELECTED_CHARM), is(true));
  }

  @Test
  public void removesListenerOnDispose() throws Exception {
    visuals.dispose();
    charmSelectionControl.control.forAllDo(new CharmSelectionNotificationClosure(SELECTED_CHARM));
    assertThat(getCharmModel().isCreationLearned(SELECTED_CHARM), is(false));
  }
}