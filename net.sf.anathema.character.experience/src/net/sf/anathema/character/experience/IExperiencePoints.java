package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.experience.points.ExperienceEntry;

public interface IExperiencePoints extends IModel {

  public static final String MODEL_ID = "net.sf.anathema.character.experience.points.model"; //$NON-NLS-1$

  public ExperienceEntry[] getEntries();

  public void add(ExperienceEntry entry);

  public void update(ExperienceEntry experienceEntry, ExperienceEntry updateEntry);

  public void delete(ExperienceEntry experienceEntry);
}