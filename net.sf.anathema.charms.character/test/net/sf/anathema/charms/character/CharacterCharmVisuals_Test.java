package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;

import org.junit.Before;
import org.junit.Test;

public class CharacterCharmVisuals_Test {

  private DummyCharmSelectionControl charmSelectionControl;
  private ICharmModel charmModel;
  private CharacterCharmVisuals visuals;

  @Before
  public void createConnnectedVisuals() throws Exception {
    charmModel = new CharmModel();
    visuals = new CharacterCharmVisuals(charmModel, new DummyExperience());
    charmSelectionControl = new DummyCharmSelectionControl();
    visuals.connect(charmSelectionControl);
  }

  @Test
  public void learnesCharmOnSelection() throws Exception {
    charmSelectionControl.control.forAllDo(new CharmSelectionNotificationClosure("selectedCharm")); //$NON-NLS-1$
    assertThat(charmModel.isCreationLearned("selectedCharm"), is(true)); //$NON-NLS-1$
  }

  @Test
  public void removesListenerOnDispose() throws Exception {
    visuals.dispose();
    charmSelectionControl.control.forAllDo(new CharmSelectionNotificationClosure("selectedCharm")); //$NON-NLS-1$
    assertThat(charmModel.isCreationLearned("selectedCharm"), is(false)); //$NON-NLS-1$
  }
}