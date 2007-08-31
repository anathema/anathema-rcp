package net.sf.anathema.character.experience.internal;

import net.sf.anathema.character.experience.plugin.ExperiencePlugin;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.menus.UIElement;

public final class CommandRefreshChangeListener implements IChangeListener {
  private final UIElement element;

  public CommandRefreshChangeListener(UIElement element) {
    this.element = element;
  }

  @Override
  public void changeOccured() {
    ICommandService service = (ICommandService) element.getServiceLocator().getService(ICommandService.class);
    service.refreshElements(ExperiencePlugin.EXPERIENCE_TOGGLE_COMMAND, null);
  }
}