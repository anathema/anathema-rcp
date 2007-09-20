package net.sf.anathema.character.core.plugin.internal;

import org.osgi.framework.BundleContext;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

public class CharacterCorePlugin extends AbstractAnathemaUIPlugin {

  private static CharacterCorePlugin instance;
  
  public static AbstractAnathemaUIPlugin getDefaultInstance() {
    return instance;
  }
  
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    new ResourcesLoader().load(new EclipseExtensionProvider().getExtensions("net.sf.anathema.character.resources")); //$NON-NLS-1$
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