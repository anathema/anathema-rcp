package net.sf.anathema.charms.character.combo;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.model.AbstractModel;

public class ComboModel extends AbstractModel implements IComboModel {

  private final List<Combo> creationLearned = new ArrayList<Combo>();
  private final List<Combo> experienceLearned = new ArrayList<Combo>();

  public void addCreationLearned(Combo combo) {
    creationLearned.add(combo);
    fireChangedEvent();
  }

  public void addExperienceLearned(Combo combo) {
    experienceLearned.add(combo);
    fireChangedEvent();
  }

  public Iterable<Combo> getCreationLearned() {
    return creationLearned;
  }

  public Iterable<Combo> getExperienceLearned() {
    return experienceLearned;
  }

  @Override
  protected void loadFromFromSaveState(Object saveState) {
    ComboModelMemento memento = (ComboModelMemento) saveState;
    synchronize(memento.creationLearned, creationLearned);
    synchronize(memento.experienceLearned, experienceLearned);
  }

  @Override
  public ComboModelMemento getSaveState() {
    ComboModelMemento memento = new ComboModelMemento();
    synchronize(creationLearned, memento.creationLearned);
    synchronize(experienceLearned, memento.experienceLearned);
    return memento;
  }
}