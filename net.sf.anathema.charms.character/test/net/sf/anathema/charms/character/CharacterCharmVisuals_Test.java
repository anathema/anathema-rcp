package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CharacterCharmVisuals_Test {

  private static final ICharmId SELECTED_CHARM = new CharmId("selectedCharm", null);
  private DummyCharmSelectionControl charmSelectionControl;
  private ICharmModel charmModel;
  private CharacterCharmVisuals visuals;

  @Before
  public void createConnnectedVisuals() throws Exception {
    charmModel = new CharmModel();
    visuals = new CharacterCharmVisuals(charmModel, new DummyExperience(), new DummyCharmPreferences());
    charmSelectionControl = new DummyCharmSelectionControl();
    visuals.connect(charmSelectionControl);
  }

  @Test
  public void learnsCharmOnSelection() throws Exception {
    charmSelectionControl.control.forAllDo(new CharmSelectionNotificationClosure(SELECTED_CHARM));
    assertThat(charmModel.isCreationLearned(SELECTED_CHARM), is(true));
  }

  @Test
  public void removesListenerOnDispose() throws Exception {
    visuals.dispose();
    charmSelectionControl.control.forAllDo(new CharmSelectionNotificationClosure(SELECTED_CHARM));
    assertThat(charmModel.isCreationLearned(SELECTED_CHARM), is(false));
  }
}