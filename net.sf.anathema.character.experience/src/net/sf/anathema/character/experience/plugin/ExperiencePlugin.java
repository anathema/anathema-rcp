package net.sf.anathema.character.experience.plugin;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.basics.eclipse.ui.PartListenerManager;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;

import org.eclipse.ui.IPartListener;
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
  private final PartListenerManager windowListener = new PartListenerManager(listener);

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    windowListener.activate(getWorkbench());
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    windowListener.deactivate(getWorkbench());
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