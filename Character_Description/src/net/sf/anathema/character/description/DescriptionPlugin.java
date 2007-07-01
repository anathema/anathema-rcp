package net.sf.anathema.character.description;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

public class DescriptionPlugin extends AbstractAnathemaUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.description"; //$NON-NLS-1$
  private static DescriptionPlugin instance;

  public static AbstractAnathemaUIPlugin getDefaultInstance() {
    return instance;
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