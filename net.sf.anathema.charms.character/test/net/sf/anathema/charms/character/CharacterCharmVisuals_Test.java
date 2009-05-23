package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.validator.DummyCharacter;
import net.sf.anathema.charms.character.model.CharmCharacter;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

public class CharacterCharmVisuals_Test {

  private static final ICharmId SELECTED_CHARM = new CharmId("selectedCharm", null);
  private DummyCharmSelectionControl charmSelectionControl;
  private CharacterCharmVisuals visuals;
  private DummyCharacter dummyCharacter;

  @Before
  public void createConnnectedVisuals() throws Exception {
    dummyCharacter = new DummyCharacter();
    dummyCharacter.modelsById.put(ICharmModel.MODEL_ID, new CharmModel());
    dummyCharacter.modelsById.put(IExperience.MODEL_ID, new DummyExperience());
    CharmCharacter charmCharacter = new CharmCharacter(new DummyVirtualCharms(), dummyCharacter);
    visuals = new CharacterCharmVisuals(getCharmModel(), getExperience(), new DummyCharmPreferences(), charmCharacter);
    charmSelectionControl = new DummyCharmSelectionControl();
    visuals.connect(charmSelectionControl);
  }

  private CharmModel getCharmModel() {
    return (CharmModel) dummyCharacter.getModel(ICharmModel.MODEL_ID);
  }

  private IExperience getExperience() {
    return (IExperience) dummyCharacter.getModel(IExperience.MODEL_ID);
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