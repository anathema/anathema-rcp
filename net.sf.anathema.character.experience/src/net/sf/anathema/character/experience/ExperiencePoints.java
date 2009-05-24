package net.sf.anathema.character.experience;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.experience.points.ExperienceEntry;
import net.sf.anathema.character.experience.points.ExperiencePointsMemento;

public class ExperiencePoints extends AbstractModel implements IExperiencePoints {

  private final List<ExperienceEntry> entries = new ArrayList<ExperienceEntry>();

  @Override
  public void add(ExperienceEntry entry) {
    entries.add(entry);
    propagateChange();
  }

  @Override
  public void update(ExperienceEntry entry, ExperienceEntry updateEntry) {
    int entryIndex = entries.indexOf(entry);
    entries.remove(entry);
    entries.add(entryIndex, updateEntry);
    propagateChange();
  }

  @Override
  public void delete(ExperienceEntry entry) {
    entries.remove(entry);
    propagateChange();
  }

  private void propagateChange() {
    setDirty(true);
    fireChangedEvent();
  }

  @Override
  public ExperienceEntry[] getEntries() {
    return entries.toArray(new ExperienceEntry[entries.size()]);
  }

  @Override
  protected void loadFromFromSaveState(Object saveState) {
    ExperiencePointsMemento memento = (ExperiencePointsMemento) saveState;
    entries.clear();
    entries.addAll(memento.entries);
  }

  @Override
  public ExperiencePointsMemento getSaveState() {
    ExperiencePointsMemento memento = new ExperiencePointsMemento();
    memento.entries = new ArrayList<ExperienceEntry>(entries);
    return memento;
  }
}