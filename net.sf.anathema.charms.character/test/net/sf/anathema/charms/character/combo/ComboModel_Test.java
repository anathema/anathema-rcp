package net.sf.anathema.charms.character.combo;

import static net.sf.anathema.test.disy.ChangeListenerObjectMother.*;
import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.character.combo.ComboModel;
import net.sf.anathema.charms.character.combo.ComboModelMemento;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class ComboModel_Test {

  private ComboModel comboModel;
  private Combo combo;
  private ComboModelMemento memento;

  @Before
  public void createModel() throws Exception {
    this.comboModel = new ComboModel();
    combo = new Combo();
    memento = new ComboModelMemento();
  }

  @Test
  public void notifiesListenerOnAdditionOfCreationLearnedCombo() throws Exception {
    IChangeListener changeListener = CreateNotifiedChangeListener();
    comboModel.addChangeListener(changeListener);
    comboModel.addCreationLearned(new Combo());
    verify(changeListener);
  }

  @Test
  public void notifiesListenerOnAdditionOfExperiencedLearnedCombo() throws Exception {
    IChangeListener changeListener = CreateNotifiedChangeListener();
    comboModel.addChangeListener(changeListener);
    comboModel.addExperienceLearned(new Combo());
    verify(changeListener);
  }

  @Test
  public void addsCreationLearnedComboToSaveState() throws Exception {
    comboModel.addCreationLearned(combo);
    ComboModelMemento saveState = comboModel.getSaveState();
    assertThat(saveState.creationLearned, JUnitMatchers.hasItem(combo));
  }

  @Test
  public void addsExperienceLearnedComboToSaveState() throws Exception {
    comboModel.addExperienceLearned(combo);
    ComboModelMemento saveState = comboModel.getSaveState();
    assertThat(saveState.experienceLearned, hasItem(combo));
  }

  @Test
  public void loadingEmptySaveStateRemovesExperienceLearnedCombo() throws Exception {
    comboModel.addExperienceLearned(combo);
    comboModel.revertTo(memento);
    assertThat(comboModel.getExperienceLearned(), is(empty()));
  }

  @Test
  public void loadingEmptySaveStateRemovesCreationLearnedCombo() throws Exception {
    comboModel.addCreationLearned(combo);
    comboModel.revertTo(memento);
    assertThat(comboModel.getCreationLearned(), is(empty()));
  }

  @Test
  public void containsCreationLearnedComboFromSaveStateAfterLoading() throws Exception {
    memento.creationLearned.add(combo);
    comboModel.revertTo(memento);
    assertThat(comboModel.getCreationLearned(), hasItem(combo));
  }

  @Test
  public void containsExperienceLearnedComboFromSaveStateAfterLoading() throws Exception {
    memento.experienceLearned.add(combo);
    comboModel.revertTo(memento);
    assertThat(comboModel.getExperienceLearned(), hasItem(combo));
  }
}