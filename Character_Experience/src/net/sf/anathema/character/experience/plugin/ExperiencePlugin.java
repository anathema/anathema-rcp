package net.sf.anathema.character.experience.plugin;

import java.util.HashMap;

import net.sf.anathema.basics.eclipse.ui.TopPartListener;

import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class ExperiencePlugin extends AbstractUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.experience"; //$NON-NLS-1$
  public static final String EXPERIENCE_TOGGLE_COMMAND = "net.sf.anathema.character.experience.toggle"; //$NON-NLS-1$
  private final Runnable runnable = new Runnable() {
    @Override
    public void run() {
      ICommandService commandService = (ICommandService) PlatformUI.getWorkbench()
          .getActiveWorkbenchWindow()
          .getService(ICommandService.class);
      commandService.refreshElements(EXPERIENCE_TOGGLE_COMMAND, new HashMap());
    }
  };
  private final IPartListener listener = new TopPartListener(runnable);

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    getWorkbench().getActiveWorkbenchWindow().getActivePage().addPartListener(listener);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    IWorkbenchWindow window = getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      window.getActivePage().removePartListener(listener);
    }
    super.stop(context);
  }
}
