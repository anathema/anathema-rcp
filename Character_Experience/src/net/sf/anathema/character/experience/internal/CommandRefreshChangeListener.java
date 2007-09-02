package net.sf.anathema.character.experience.internal;

import net.sf.anathema.character.experience.plugin.ExperiencePlugin;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.services.IServiceLocator;

public final class CommandRefreshChangeListener implements IChangeListener {
  private final IServiceLocator locator;

  public CommandRefreshChangeListener(IServiceLocator locator) {
    this.locator = locator;
  }

  @Override
  public void changeOccured() {
    ICommandService service = (ICommandService) locator.getService(ICommandService.class);
    service.refreshElements(ExperiencePlugin.EXPERIENCE_TOGGLE_COMMAND, null);
  }
}