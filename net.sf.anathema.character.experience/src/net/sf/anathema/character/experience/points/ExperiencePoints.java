package net.sf.anathema.character.experience.points;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.experience.IExperiencePoints;
import net.sf.anathema.lib.control.change.ChangeControl;

public class ExperiencePoints extends AbstractModel implements IExperiencePoints {

  private final ChangeControl changeControl = new ChangeControl();
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
    changeControl.fireChangedEvent();
  }

  @Override
  public ExperienceEntry[] getEntries() {
    return entries.toArray(new ExperienceEntry[entries.size()]);
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }
}