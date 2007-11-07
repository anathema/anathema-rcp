package net.sf.anathema.character.core.plugin.internal;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.basics.eclipse.ui.PartListenerManager;

import org.eclipse.ui.IPartListener;
import org.osgi.framework.BundleContext;

public class CharacterCorePlugin extends AbstractAnathemaUIPlugin {

  public static final String ID = "net.sf.anathema.character.core"; //$NON-NLS-1$
  private static final String CHARACTER_RESOURCES_EXTENSION_POINT = "resources"; //$NON-NLS-1$
  private static CharacterCorePlugin instance;
  private IPartListener partListener = new ModelRevertingCloseListener();
  private PartListenerManager partListenerManager = new PartListenerManager(partListener);

  public static AbstractAnathemaUIPlugin getDefaultInstance() {
    return instance;
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    new ResourcesLoader().load(new EclipseExtensionPoint(ID, CHARACTER_RESOURCES_EXTENSION_POINT).getExtensions());
    partListenerManager.activate(getWorkbench());
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    partListenerManager.deactivate(getWorkbench());
    super.stop(context);
  }

  @Override
  protected final void createInstance() {
    instance = this;
  }

  @Override
  protected final void deleteInstance() {
    instance = null;
  }

  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return getDefaultInstance();
  }
}