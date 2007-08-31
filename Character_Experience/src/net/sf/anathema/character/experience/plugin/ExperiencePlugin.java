package net.sf.anathema.character.experience.plugin;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;

import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.osgi.framework.BundleContext;

public class ExperiencePlugin extends AbstractAnathemaUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.experience"; //$NON-NLS-1$
  public static final String EXPERIENCE_TOGGLE_COMMAND = "net.sf.anathema.character.experience.toggle"; //$NON-NLS-1$
  private static ExperiencePlugin instance;
  private final Runnable runnable = new Runnable() {
    @Override
    public void run() {
      ICommandService commandService = (ICommandService) PlatformUI.getWorkbench()
          .getActiveWorkbenchWindow()
          .getService(ICommandService.class);
      commandService.refreshElements(EXPERIENCE_TOGGLE_COMMAND, null);
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

  @Override
  protected void createInstance() {
    instance = this;
  }

  @Override
  protected void deleteInstance() {
    instance = null;
  }

  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return getDefaultInstance();
  }

  public static ExperiencePlugin getDefaultInstance() {
    return instance;
  }

}
