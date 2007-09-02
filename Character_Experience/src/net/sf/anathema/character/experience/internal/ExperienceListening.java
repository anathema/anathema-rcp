package net.sf.anathema.character.experience.internal;

import net.sf.anathema.character.experience.IExperience;

import org.eclipse.ui.services.IServiceLocator;

public class ExperienceListening {

  private CommandRefreshChangeListener listener;
  private IExperience experience;

  public void reset() {
    if (experience != null) {
      this.experience.removeChangeListener(listener);
    }
    this.experience = null;
    this.listener = null;
  }

  public void init(IServiceLocator locator, IExperience watchedExperience) {
    this.experience = watchedExperience;
    this.listener = new CommandRefreshChangeListener(locator);
    experience.addChangeListener(listener);
  }
}